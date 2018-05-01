package com.valorachen.whisper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;
import com.valorachen.whisper.R;
import com.valorachen.whisper.adapter.ArticleAdapter;
import com.valorachen.whisper.http.RequestMaker;
import com.valorachen.whisper.http.ServiceFactory;
import com.valorachen.whisper.model.Article;
import com.valorachen.whisper.model.Type;

/**
 * Created by vivi on 2018/5/1.
 */

public class ArticleActivity extends BaseRecyclerViewActivity<Article.DataBean,ArticleAdapter>  {

    private Type.DataBean type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = (Type.DataBean) getIntent().getSerializableExtra("type");
        setTitle(type.getName());
        new RequestMaker<Article>(activity, ServiceFactory.getRequestService().article_get(type.getId())){

            @Override
            protected void onSuccess(final Article article) {
                data.addAll(article.getData());
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        new RequestMaker<Article>(activity, ServiceFactory.getRequestService().article_get(-1)){

            @Override
            protected void onSuccess(final Article article) {
                data.clear();
                data.addAll(article.getData());
                notifyDataSetChanged();
                stopRefresh();
            }

            @Override
            protected void onFail(int code, String msg) {
                super.onFail(code, msg);
                stopRefresh();
            }
        };
    }

    @Override
    protected void onItemClick(View view, int position, Article.DataBean article) {
        super.onItemClick(view, position, article);
        Intent intent = new Intent(activity, ReWriteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("article", article);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected ArticleAdapter setAdapter() {
        return new ArticleAdapter(activity, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(activity, TypeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", type);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
