package com.example.android.meditationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning essential Views to variables to be referenced later
        LinearLayout dailyButton = (LinearLayout) findViewById(R.id.daily_button);
        LinearLayout topicalButton = (LinearLayout) findViewById(R.id.topical_button);
        ImageView info = (ImageView) findViewById(R.id.info);
        final TextView infoText = (TextView) findViewById(R.id.info_text);

        infoText.setVisibility(View.GONE); //Setting visibility as gone to be made visible when triggered

        //OnClickListener to take user to DailyMeditation activity
        dailyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent dailyIntent = new Intent(MainActivity.this, DailyMediation.class);
                startActivity(dailyIntent);
            }
        });

        //OnClickListener to take user to TopicalMeditation activity
        topicalButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent topicIntent = new Intent(MainActivity.this, TopicalMeditation.class);
                startActivity(topicIntent);
            }
        });

        //OnClickListener to toggle visibility of TextView infoText when ImageView info is clicked
        info.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (infoText.isShown()) {
                    infoText.setVisibility(View.GONE);
                } else {
                    infoText.setVisibility(View.VISIBLE);
                }
            }
        }));
    }
}
