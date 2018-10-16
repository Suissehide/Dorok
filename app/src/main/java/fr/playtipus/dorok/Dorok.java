package fr.playtipus.dorok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageButton;

public class Dorok extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dorok);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //starting game activity
        startActivity(new Intent(this, GameActivity.class));
    }

}
