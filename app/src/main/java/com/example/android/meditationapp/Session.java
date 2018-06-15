package com.example.android.meditationapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Session implements Parcelable { //Custom class to hold information to fill list and grid
    private String mFileTitle;

    private String mFileSubtitle;

    private int mFileImage;

    public Session(String fileTitle, String fileSubtitle, int fileImage) {
        mFileTitle = fileTitle;
        mFileSubtitle = fileSubtitle;
        mFileImage = fileImage;

    }

    protected Session(Parcel in) {
        mFileTitle = in.readString();
        mFileSubtitle = in.readString();
        mFileImage = in.readInt();
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    public String getFileTitle() {
        return mFileTitle;
    }

    public String getFileSubtitle() {
        return mFileSubtitle;
    }

    public int getFileImage() {
        return mFileImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFileTitle);
        dest.writeString(mFileSubtitle);
        dest.writeInt(mFileImage);
    }
}
