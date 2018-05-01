package com.valorachen.whisper.adapter;

import android.app.Activity;

import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.valorachen.whisper.R;
import com.valorachen.whisper.model.Article;

import java.util.List;

/**
 * Created by vivi on 2018/3/24.
 */

public class ArticleAdapter extends SuperBaseAdapter<Article.DataBean> {

    public ArticleAdapter(Activity activity, List<Article.DataBean> data) {
        super(activity, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, Article.DataBean item, final int position) {
        holder.setText(R.id.tvContent,item.getContent());
        holder.setText(R.id.tvType,item.getType_name());
        holder.setText(R.id.tvTime,item.getTime());
    }

    @Override
    protected int getItemViewLayoutId(int position, Article.DataBean item) {
        return R.layout.item_article;
    }
}