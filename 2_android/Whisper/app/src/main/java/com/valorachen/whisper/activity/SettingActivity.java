package com.valorachen.whisper.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.valorachen.whisper.MyApplication;
import com.valorachen.whisper.R;

/**
 * Created by vivi on 2018/5/1.
 */

public class SettingActivity extends BaseActivity {

    private EditText etUrl;

    @Override
    protected int onBindView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        etUrl = (EditText) findViewById(R.id.etUrl);
        findViewById(R.id.fab);
        String url = preferences.getString("url","");
        if (!TextUtils.isEmpty(url)){
            etUrl.setText(url);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.fab){
            String url = etUrl.getText().toString();
            if (TextUtils.isEmpty(url)) toast("写点什么吧");
            else {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("url", url);
                editor.apply();
                MyApplication.BASE_URL = url;
                startActivity(MainActivity.class);
                finish();
            }
        }
    }
}
