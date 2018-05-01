package com.valorachen.whisper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.valorachen.whisper.R;
import com.valorachen.whisper.http.RequestMaker;
import com.valorachen.whisper.http.ServiceFactory;
import com.valorachen.whisper.model.Type;
import com.valorachen.whisper.model.base.BaseModel;

/**
 * Created by vivi on 2018/5/1.
 */

public class TypeActivity extends BaseActivity {


    private EditText etType;
    private Type.DataBean type;

    @Override
    protected int onBindView() {
        return R.layout.activity_type;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        etType = (EditText) findViewById(R.id.etType);
        findViewById(R.id.fab);
        type = (Type.DataBean) getIntent().getSerializableExtra("type");
        etType.setText(type.getName());
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.fab){
            String name = etType.getText().toString();
            if (TextUtils.isEmpty(name)) toast("写点什么吧");
            else {
                new RequestMaker<BaseModel>(activity, ServiceFactory.getRequestService().type_update(type.getId(), name)){

                    @Override
                    protected void onSuccess(final BaseModel baseModel) {
                        toast("修改成功");
                        finish();
                    }
                };
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            new RequestMaker<BaseModel>(activity, ServiceFactory.getRequestService().type_delete(type.getId())){

                @Override
                protected void onSuccess(final BaseModel baseModel) {
                    toast("删除成功");
                    finish();
                }
            };
        }
        return super.onOptionsItemSelected(item);
    }
}
