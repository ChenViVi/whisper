package com.chenyuwei.basematerial.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.superrecycleview.superlibrary.recycleview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */
public abstract class BaseRecyclerViewFragment<Item,Adapter extends SuperBaseAdapter> extends BaseFragment implements SuperRecyclerView.LoadingListener{

    protected ArrayList<Item> data = new ArrayList<>();
    protected RelativeLayout llContent;
    //protected View emptyView;
    private Adapter adapter;
    protected SuperRecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView tvTitle;

    @Override
    protected int onBindView() {
        return R.layout.base_fragment_recycle_view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = setAdapter();
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        llContent = (RelativeLayout) rootView.findViewById(R.id.llContent);
        recyclerView = (SuperRecyclerView) findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        enableToolBar(false);
        recyclerView.setLayoutManager(setLayoutManager());
        recyclerView.setAdapter(adapter);
        setPullLoadEnable(false);
        setPullRefreshEnable(false);
        recyclerView.setLoadingListener(this);
        clearItems();
        adapter.setOnItemClickListener(new SuperBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                Item i = (Item) item;
                BaseRecyclerViewFragment.this.onItemClick(view,  position,i);
            }
        });
    }

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected void enableToolBar(boolean enable){
        if (enable){
            toolbar.setVisibility(View.VISIBLE);
        }
        else {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected void setTitle(String title){
        enableToolBar(true);
        tvTitle.setText(title);
    }

    protected void setTitle(int titleId){
        setTitle(getResources().getString(titleId));
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    protected void setPullLoadEnable(boolean enable){
        recyclerView.setLoadMoreEnabled(enable);
    }

    protected void setPullRefreshEnable(boolean enable){
        recyclerView.setRefreshEnabled(enable);
    }

    protected void stopRefresh(){
        recyclerView.completeRefresh();
    }

    protected void stopLoadMore(){
        recyclerView.completeLoadMore();
    }

    protected void notifyDataSetChanged(){
        adapter.notifyDataSetChanged();
    }

    protected void clearItems(){
        if (data.size() > 0){
            data.clear();
            adapter.notifyDataSetChanged();
        }
    }

    protected abstract Adapter setAdapter();

    protected void onItemClick(View view,int position, Item item){

    }
}
