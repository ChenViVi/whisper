package com.valorachen.whisper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.valorachen.whisper.activity.ReWriteActivity;
import com.valorachen.whisper.adapter.ArticleAdapter;
import com.valorachen.whisper.http.RequestMaker;
import com.valorachen.whisper.http.ServiceFactory;
import com.valorachen.whisper.model.Article;

/**
 * Created by vivi on 2018/4/30.
 */

public class HomeFragment extends BaseRecyclerViewFragment<Article.DataBean,ArticleAdapter> {

    @Override
    protected void onCreateView() {
        super.onCreateView();
        setPullRefreshEnable(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
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
}
