package com.example.mystudy.itemDecoration;

/**
 * Created by Nature on 2017/7/11.
 */

public class GroupInfo {
    //组号
    private int mGroupID;
    //Header 的 title
    private String mTitle;
    //ItemView 在组内的位置
    private int position;
    //组员的个数
    private int mGroupLength;

    public GroupInfo(int mGroupID, String mTitle) {
        this.mGroupID = mGroupID;
        this.mTitle = mTitle;
    }

    public int getGroupID() {
        return mGroupID;
    }

    public void setGroupID(int mGroupID) {
        this.mGroupID = mGroupID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean isFirstViewInGroup() {
        return position == 0;
    }

    public boolean isLastViewInGroup() {
        return position == mGroupLength - 1 && position >= 0;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public void setGroupLength(int mGroupLength) {
        this.mGroupLength = mGroupLength;
    }
}
