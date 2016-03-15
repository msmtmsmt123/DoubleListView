package com.zoipuus.doublelistview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.zoipuus.doublelistview.R;
import com.zoipuus.doublelistview.bean.ThemeBean;

import java.util.ArrayList;


/**
 * Created by zoipuus on 2016/3/12.
 * home theme adapter
 */
public class ThemeAdapter extends BaseAdapter {

    private final ArrayList<ThemeBean> themeBeans;
    private final int dividend;
    private final int remainder;
    private final Context context;
    private BitmapUtils bitmapUtils;

    public ThemeAdapter(Context context, ArrayList<ThemeBean> themeBeans, int dividend, int remainder) {
        this.context = context;
        this.themeBeans = themeBeans;
        this.dividend = dividend;
        this.remainder = remainder;
        setImageConfig();
    }

    private void setImageConfig() {
        bitmapUtils = new BitmapUtils(context);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_default_load_failed);
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_default_pic);

    }

    @Override
    public int getCount() {
        if (themeBeans == null) {
            return 0;
        } else {
            return (themeBeans.size() + remainder) / dividend;
        }
    }

    @Override
    public Object getItem(int position) {
        if (themeBeans == null) {
            return null;
        } else {
            return themeBeans.get(position * dividend + remainder);
        }
    }

    @Override
    public long getItemId(int position) {
        if (themeBeans == null) {
            return 0;
        } else {
            return position * dividend + remainder;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold view;
        ThemeBean themeBean = themeBeans.get(position * dividend + remainder);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
            view = new ViewHold();
            view.pic = (ImageView) convertView.findViewById(R.id.pic);
            view.ic_user = (ImageView) convertView.findViewById(R.id.ic_user);
            view.favour = (ImageButton) convertView.findViewById(R.id.favour);
            view.username = (TextView) convertView.findViewById(R.id.username);
            view.distance = (TextView) convertView.findViewById(R.id.distance);
            view.score = (TextView) convertView.findViewById(R.id.score);
            convertView.setTag(view);
        } else {
            view = (ViewHold) convertView.getTag();
        }
        view.username.setText(themeBean.nickname);
        view.distance.setText(themeBean.distance);
        view.score.setText(themeBean.averageScore);

        bitmapUtils.display(view.ic_user, themeBean.userPic);
        bitmapUtils.display(view.pic, themeBean.thumbPic_url);
        return convertView;
    }

    private class ViewHold {
        private ImageView pic;
        private ImageView ic_user;
        private ImageButton favour;
        private TextView username;
        private TextView distance;
        private TextView score;
    }
}
