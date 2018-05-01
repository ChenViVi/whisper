package com.valorachen.whisper.adapter;

import android.app.Activity;

import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.valorachen.whisper.R;
import com.valorachen.whisper.model.Type;

import java.util.List;

/**
 * Created by vivi on 2018/3/24.
 */

public class TypeAdapter extends SuperBaseAdapter<Type.DataBean> {

    public TypeAdapter(Activity activity, List<Type.DataBean> data) {
        super(activity, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, Type.DataBean item, final int position) {
        holder.setText(R.id.tvName,item.getName());
    }

    @Override
    protected int getItemViewLayoutId(int position, Type.DataBean item) {
        return R.layout.item_type;
    }
}