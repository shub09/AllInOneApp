package com.example.user.allinoneapp.Model;

/**
 * Created by user on 2/7/2017.
 */

public class DataModel {
    String name;

    public String getUnreadMsg() {
        return unreadMsg;
    }

    public void setUnreadMsg(String unreadMsg) {
        this.unreadMsg = unreadMsg;
    }

    String unreadMsg;
    int icon;

    public DataModel(String name, int icon, String unreadMsg ) {
        this.name=name;
        this.icon=icon;
        this.unreadMsg = unreadMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
