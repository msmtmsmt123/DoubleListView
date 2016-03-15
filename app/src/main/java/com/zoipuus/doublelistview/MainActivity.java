package com.zoipuus.doublelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.zoipuus.doublelistview.adapters.ThemeAdapter;
import com.zoipuus.doublelistview.bean.ThemeBean;
import com.zoipuus.doublelistview.utils.JsonSerializer;
import com.zoipuus.doublelistview.utils.JsonString;
import com.zoipuus.doublelistview.utils.LogUtil;
import com.zoipuus.doublelistview.views.DoubleListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DoubleListView.OnItemClickListener {

    private ArrayList<ThemeBean> themeBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getThemeList(JsonString.json);
    }

    private void getThemeList(String json) {
        themeBeans = JsonSerializer.getThemeList(json);
        if (themeBeans != null) {
            initAdapter(themeBeans);
        }
    }

    private void initAdapter(ArrayList<ThemeBean> themeBeans) {
        ThemeAdapter leftAdapter = new ThemeAdapter(this, themeBeans, 2, 0);
        ThemeAdapter rightAdapter = new ThemeAdapter(this, themeBeans, 2, 1);

        DoubleListView doubleListView = (DoubleListView) findViewById(R.id.listBuddiesLayout);
        doubleListView.setAdapters(leftAdapter, rightAdapter);
        doubleListView.setListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtil.e("parent->" + parent.toString());
        LogUtil.e("url->" + themeBeans.get((int) id).thumbPic_url);
        LogUtil.e("position->" + position);
        LogUtil.e("id->" + id);
    }
}
