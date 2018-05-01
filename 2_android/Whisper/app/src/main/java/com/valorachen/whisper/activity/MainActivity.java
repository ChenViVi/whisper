package com.valorachen.whisper.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chenyuwei.basematerial.activity.BaseTabTopActivity;
import com.valorachen.whisper.MyApplication;
import com.valorachen.whisper.R;
import com.valorachen.whisper.fragment.HomeFragment;
import com.valorachen.whisper.fragment.TypeFragment;

public class MainActivity extends BaseTabTopActivity {

    @Override
    protected int onBindView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = preferences.getString("url","");
        if (TextUtils.isEmpty(url)){
            startActivity(SettingActivity.class);
            finish();
        }
        else {
            MyApplication.BASE_URL = url;
        }
        findViewById(R.id.fab);
        addFragment(new HomeFragment(), R.string.tabAll);
        addFragment(new TypeFragment(), R.string.tabType);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.fab) startActivity(WriteActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            startActivity(SettingActivity.class);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
