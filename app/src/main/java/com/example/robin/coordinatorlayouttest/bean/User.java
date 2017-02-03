package com.example.robin.coordinatorlayouttest.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者: Robin
 * 时间: 2017/2/3 16:51
 * 邮箱: guaren2005@126.com
 */

public class User implements Parcelable {

    private String username;
    private String password;
    private boolean nice;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNice() {
        return nice;
    }

    public void setNice(boolean nice) {
        this.nice = nice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeByte(this.nice ? (byte) 1 : (byte) 0);
    }

    public User() {
    }

    private User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.nice = in.readByte() != 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
