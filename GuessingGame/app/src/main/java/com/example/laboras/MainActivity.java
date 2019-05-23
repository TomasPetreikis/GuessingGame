package com.example.laboras;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.animationView);
        imageView.setBackgroundResource(R.drawable.swing_animation);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) imageView.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();
    }
    public void onAboutClick(View view)
    {
        Intent intent = new Intent(this,About.class);
        startActivity(intent);
    }
    public void onSettingsClick(View view)
    {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void onPlayClick(View view)
    {
        Intent intent = new Intent(this,Play.class);
        startActivity(intent);
    }
    public void onHighscoresClick(View view)
    {
        Intent intent = new Intent(this,Highscores.class);
        startActivity(intent);
    }

}

