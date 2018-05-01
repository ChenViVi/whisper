package com.valorachen.whisper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.valorachen.whisper.R;
import com.valorachen.whisper.http.RequestMaker;
import com.valorachen.whisper.http.ServiceFactory;
import com.valorachen.whisper.model.Article;
import com.valorachen.whisper.model.Type;
import com.valorachen.whisper.model.base.BaseModel;

import java.util.ArrayList;

/**
 * Created by vivi on 2018/5/1.
 */

public class ReWriteActivity extends BaseActivity {

    private Spinner spinner;
    private EditText etType;
    private EditText etContent;
    ArrayList<String> list = new ArrayList<>();
    private Article.DataBean article;

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
        article = (Article.DataBean) getIntent().getSerializableExtra("article");
        etContent.setText(article.getContent());
        new RequestMaker<Type>(activity, ServiceFactory.getRequestService().type_get()){

            @Override
            protected void onSuccess(final Type type) {
                int position = 0;
                int i = 0;
                for (Type.DataBean t : type.getData()){
                    if (t.getName().equals(article.getType_name())) position = i;
                    list.add(t.getName());
                    i++;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner .setAdapter(adapter);
                spinner.setSelection(position);
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
                new RequestMaker<BaseModel>(activity, ServiceFactory.getRequestService().article_update(article.getId() ,type, content)){

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
        getMenuInflater().inflate(R.menu.menu_rewrite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            new RequestMaker<BaseModel>(activity, ServiceFactory.getRequestService().article_delete(article.getId())){

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
