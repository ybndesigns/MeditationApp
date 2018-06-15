package com.example.android.meditationapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class SessionAdapter extends ArrayAdapter<Session> { //Custom adapter to assist custom class Session
    private static final String LOG_TAG = SessionAdapter.class.getSimpleName();

    public SessionAdapter(Context context, List<Session> objects) {
        super(context, 0, objects);
    }

}
