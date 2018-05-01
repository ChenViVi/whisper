package com.valorachen.whisper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.valorachen.whisper.activity.ArticleActivity;
import com.valorachen.whisper.adapter.TypeAdapter;
import com.valorachen.whisper.http.RequestMaker;
import com.valorachen.whisper.http.ServiceFactory;
import com.valorachen.whisper.model.Type;

/**
 * Created by vivi on 2018/4/30.
 */

public class TypeFragment extends BaseRecyclerViewFragment<Type.DataBean,TypeAdapter> {

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
        new RequestMaker<Type>(activity, ServiceFactory.getRequestService().type_get()){

            @Override
            protected void onSuccess(final Type type) {
                data.clear();
                data.addAll(type.getData());
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
    protected void onItemClick(View view, int position, Type.DataBean type) {
        super.onItemClick(view, position, type);
        Intent intent = new Intent(activity, ArticleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", type);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new GridLayoutManager(activity,2);
    }

    @Override
    protected TypeAdapter setAdapter() {
        return new TypeAdapter(activity, data);
    }
}
