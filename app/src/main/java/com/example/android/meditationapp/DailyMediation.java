package com.example.android.meditationapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DailyMediation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_mediation);

        //Assigning the NowPlaying activity to a variable to be referenced later
        final Intent nowPlayingIntent = new Intent(DailyMediation.this, NowPlaying.class);

        //ListView to be populated
        final ListView listView = (ListView) findViewById(R.id.listview);

        //Addition needed items that will be referenced
        String dailyMedNum = "Daily Meditation #";
        int displayImage = R.drawable.ic_launcher_foreground;

        //ArrayList of custom class Session that will populate listView
        final ArrayList<Session> dailys = new ArrayList<Session>();
        dailys.add(new Session("The Beginning", dailyMedNum + "1", displayImage));
        dailys.add(new Session("Deep Breaths", dailyMedNum + "2", displayImage));
        dailys.add(new Session("Chasing Cars", dailyMedNum + "3", displayImage));
        dailys.add(new Session("Outside Distractions", dailyMedNum + "4", displayImage));
        dailys.add(new Session("Inner Calm", dailyMedNum + "5", displayImage));
        dailys.add(new Session("Check In", dailyMedNum + "6", displayImage));

        class DailySessionAdapter extends SessionAdapter { //ArrayAdapter DailySessionAdapter extending previously made ArrayAdapter SessionAdapter to be able to handle separate needs for a ListView

            public DailySessionAdapter(Context context, List<Session> objects) {
                super(context, objects);
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View listItemView = convertView;
                if (listItemView == null) {
                    listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
                }

                Session currentSession = getItem(position);

                TextView listTitle = (TextView) listItemView.findViewById(R.id.list_title);
                listTitle.setText(currentSession.getFileTitle());

                TextView listSubtitle = (TextView) listItemView.findViewById(R.id.list_subtitle);
                listSubtitle.setText(currentSession.getFileSubtitle());

                //OnClickListener to send user to NowPlaying activity with the information of the list item clicked
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //Sending ArrayList as well as ArrayList position of item clicked to linked intent NowPlaying
                        final int arrayPosition = position;
                        nowPlayingIntent.putParcelableArrayListExtra("Session", dailys);
                        nowPlayingIntent.putExtra("position", arrayPosition);
                        startActivityForResult(nowPlayingIntent, 0);
                    }
                });

                return listItemView;
            }
        }

        //Creating new DailySessionAdapter, assigning it to variable adapter, and setting adapter to listView
        DailySessionAdapter adapter = new DailySessionAdapter(this, dailys);

        listView.setAdapter(adapter);

    }
}
