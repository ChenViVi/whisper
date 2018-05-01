package com.valorachen.whisper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.valorachen.whisper.R;
import com.valorachen.whisper.http.RequestMaker;
import com.valorachen.whisper.http.ServiceFactory;
import com.valorachen.whisper.model.Type;
import com.valorachen.whisper.model.base.BaseModel;

import java.util.ArrayList;

/**
 * Created by vivi on 2018/5/1.
 */

public class WriteActivity extends BaseActivity {

    private Spinner spinner;
    private EditText etType;
    private EditText etContent;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected int onBindView() {
        return R.layout.activity_write;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        spinner = (Spinner) findViewById(R.id.spinner);
        etType = (EditText) findViewById(R.id.etType);
        etContent = (EditText) findViewById(R.id.etContent);
        findViewById(R.id.fab);
        new RequestMaker<Type>(activity, ServiceFactory.getRequestService().type_get()){

            @Override
            protected void onSuccess(final Type type) {
                for (Type.DataBean t : type.getData()){
                    list.add(t.getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner .setAdapter(adapter);
            }
        };
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.fab){
            String type = etType.getText().toString();
            String content = etContent.getText().toString();
            if (TextUtils.isEmpty(type)) type = list.get(spinner.getSelectedItemPosition());
            if (TextUtils.isEmpty(content)) toast("写点什么吧");
            else {
                new RequestMaker<BaseModel>(activity, ServiceFactory.getRequestService().article_add(type, content)){

                    @Override
                    protected void onSuccess(final BaseModel baseModel) {
                        toast("添加成功");
                        finish();
                    }
                };
            }
        }
    }
}
