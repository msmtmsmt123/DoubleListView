package com.zoipuus.doublelistview.utils;

import com.zoipuus.doublelistview.bean.ThemeBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zoipuus on 2016/3/12.
 * json data serializer
 */
public class JsonSerializer {
    public static ArrayList<ThemeBean> getThemeList(String json) {
        try {
            JSONObject mObject = new JSONObject(json);
            if (mObject.optInt("status") == 200 && mObject.optInt("count") > 0) {
                JSONArray mArray = mObject.optJSONArray("data");
                if (mArray != null) {
                    ArrayList<ThemeBean> themeBeans = new ArrayList<>();
                    int length = mArray.length();
                    for (int i = 0; i < length; i++) {
                        mObject = mArray.optJSONObject(i);
                        if (mObject != null) {
                            ThemeBean themeBean = new ThemeBean(
                                    mObject.optInt("tid"),
                                    mObject.optInt("uid"),
                                    mObject.optInt("isScored"),
                                    mObject.optString("userPic"),
                                    mObject.optString("distance"),
                                    mObject.optString("editTime"),
                                    mObject.optString("nickname"),
                                    mObject.optString("averageScore")
                            );
                            themeBean.setThumbPic(mObject.optJSONObject("thumbPic"));
                            themeBeans.add(themeBean);
                        }
                    }
                    return themeBeans;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
