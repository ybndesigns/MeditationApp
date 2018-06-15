package com.example.android.meditationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        //Assigning all needed views to variables to be referenced later
        TextView playingtitle = (TextView) findViewById(R.id.playingtitle);
        TextView playingSubtitle = (TextView) findViewById(R.id.playingsubtitle);
        ImageView playingPicture = (ImageView) findViewById(R.id.playingpicture);
        ImageView homeButton = (ImageView) findViewById(R.id.home_button);
        ImageView backButton = (ImageView) findViewById(R.id.back_button);
        final ImageView playPauseButton = (ImageView) findViewById(R.id.play_pause);

        //Getting information passed from previous Activity
        ArrayList<Session> playingInfo = getIntent().getParcelableArrayListExtra("Session");
        int clickedSession = getIntent().getIntExtra("position", 0);

        //Assigning necessary data from passed information to variables
        String title = playingInfo.get(clickedSession).getFileTitle();
        String subtitle = playingInfo.get(clickedSession).getFileSubtitle();
        int image = playingInfo.get(clickedSession).getFileImage();

        //Setting the playPauseButton to be set initially as non-playing
        playPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_button_color));
        final boolean[] isPlaying = {false};

        //Setting passed information from previous activity to various Views
        playingtitle.setText(title);
        playingSubtitle.setText(subtitle);
        playingPicture.setImageDrawable(getResources().getDrawable(image));

        //OnClickListener for homeButton to send user to MainActivity
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NowPlaying.this, MainActivity.class));
            }
        });

        //OnClickListener for playPauseButton to be able to play and pause the sound clip
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying[0]) {
                    Toast.makeText(NowPlaying.this, "This session is now paused", Toast.LENGTH_SHORT).show();
                    playPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_button_color));
                    isPlaying[0] = false;
                } else {
                    Toast.makeText(NowPlaying.this, "This session is now playing", Toast.LENGTH_SHORT).show();
                    playPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_color));
                    isPlaying[0] = true;
                }
            }
        });

        //OnClickListener for backButton to take user to last activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
