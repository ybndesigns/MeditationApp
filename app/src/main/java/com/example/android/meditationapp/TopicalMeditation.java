package com.example.android.meditationapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TopicalMeditation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topical_meditation);

        final GridView gridView = (GridView) findViewById(R.id.gridview); //GridView to be populated
        final Intent nowPlayingIntent = new Intent(TopicalMeditation.this, NowPlaying.class); //Assigning the NowPlaying activity to a variable to be referenced later
        String topMed = "Topical Meditations";

        //ArrayList of custom class Session that will populate gridView
        final ArrayList<Session> topics = new ArrayList<Session>();
        topics.add(new Session("Stress", topMed, R.drawable.ic_027_obsession));
        topics.add(new Session("Insomnia", topMed, R.drawable.ic_030_insomnia));
        topics.add(new Session("Depression", topMed, R.drawable.ic_037_depression));
        topics.add(new Session("Panic Attack", topMed, R.drawable.ic_015_panic));
        topics.add(new Session("Motivation", topMed, R.drawable.ic_033_goal));
        topics.add(new Session("Creativity", topMed, R.drawable.ic_035_creativity));

        class TopicSessionAdapter extends SessionAdapter { //ArrayAdapter TopicSessionAdapter extending previously made ArrayAdapter SessionAdapter to be able to handle separate needs for a GridView

            public TopicSessionAdapter(Context context, List<Session> objects) {
                super(context, objects);
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View gridItemView = convertView;
                if (gridItemView == null) {
                    gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
                }

                Session currentSession = getItem(position);

                TextView gridTitle = (TextView) gridItemView.findViewById(R.id.grid_text);
                gridTitle.setText(currentSession.getFileTitle());

                ImageView gridImage = (ImageView) gridItemView.findViewById(R.id.grid_image);
                gridImage.setImageDrawable(getResources().getDrawable(currentSession.getFileImage()));

                //OnClickListener to send user to NowPlaying activity with the information of the grid item clicked
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //Sending ArrayList as well as ArrayList position of item clicked to linked intent NowPlaying
                        final int arrayPosition = position;
                        nowPlayingIntent.putParcelableArrayListExtra("Session", topics);
                        nowPlayingIntent.putExtra("position", arrayPosition);
                        startActivityForResult(nowPlayingIntent, 0);
                    }
                });

                return gridItemView;
            }
        }

        //Creating new TopicSessionAdapter, assigning it to variable adapter, and setting adapter to gridView
        TopicSessionAdapter adapter = new TopicSessionAdapter(this,topics);

        gridView.setAdapter(adapter);

    }
}
