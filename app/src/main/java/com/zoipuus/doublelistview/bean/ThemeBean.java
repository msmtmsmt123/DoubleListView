package com.zoipuus.doublelistview.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zoipuus on 2016/3/12.
 * home theme item
 */
public class ThemeBean {
    /**
     * theme id
     */
    public int tid;
    /**
     * user id
     */
    public int uid;
    public int isScored;
    public String distance;
    public ArrayList<Tag> tags;
    public String nickname;
    public String thumbPic_url;
    public String userPic;
    /**
     * average score
     */
    public String averageScore;
    public String editTime;

    public ThemeBean(int tid, int uid, int isScored, String userPic, String distance, String editTime, String nickname, String averageScore) {
        this.tid = tid;
        this.uid = uid;
        this.userPic = userPic;
        this.editTime = editTime;
        this.isScored = isScored;
        this.distance = distance;
        this.nickname = nickname;
        this.averageScore = averageScore;
    }

    public void setThumbPic(JSONObject object) {
        if (object != null) {
            thumbPic_url = object.optString("url");
            JSONArray mArray = object.optJSONArray("tags");
            if (mArray != null) {
                tags = new ArrayList<>();
                for (int i = 0; i < mArray.length(); i++) {
                    object = mArray.optJSONObject(i);
                    if (object != null) {
                        Tag tag = new Tag(
                                object.optInt("tagId"),
                                object.optString("tagName"));
                        tags.add(tag);
                    }
                }
            }
        }
    }

    private class Tag {
        public int tagId;
        public String tagName;

        public Tag(int tagId, String tagName) {
            this.tagId = tagId;
            this.tagName = tagName;
        }
    }
}
