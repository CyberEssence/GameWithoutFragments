package com.cyberessence.cyberorangeteam.gamewithoutfragments;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;


public class SecondPageActivity extends AppCompatActivity {

    ImageButton dir1;
    ImageButton dir2;

    ImageButton jumping;
    ImageButton sleeping;
    ImageButton walking;

    TextView question2;

    TextView ansVar3;
    TextView ansVar4;

    MediaPlayer mpJumpWrong;
    MediaPlayer mpSleepWrong;
    MediaPlayer mpWalkPerfect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        hideNavigator();

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        MediaPlayer click = MediaPlayer.create(this, R.raw.walking);
        click.start();

        dir2 = (ImageButton) findViewById(R.id.dir2);

        dir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondPageActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        jumping = (ImageButton) findViewById(R.id.jumping);
        final ImageView mImageView = (ImageView) findViewById(R.id.mImageV2);

        jumping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        mpJumpWrong = MediaPlayer.create(SecondPageActivity.this, R.raw.wrong);
                        mpJumpWrong.start();
                    }
                }.start();

                ViewCompat.animate(mImageView).withStartAction(new Runnable(){
                    @Override
                    public void run()
                    {
                        mImageView.setVisibility(View.VISIBLE);
                    }
                })
                        .alpha(1f)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(250)
                        .start();

                Animation a = AnimationUtils.loadAnimation(SecondPageActivity.this, R.anim.visible);
                mImageView.startAnimation(a);
            }
        });

        sleeping = (ImageButton) findViewById(R.id.sleeping);
        final ImageView mImageView2 = (ImageView) findViewById(R.id.mImageV3);

        sleeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    public void run() {
                        mpSleepWrong = MediaPlayer.create(SecondPageActivity.this, R.raw.wrong);
                        mpSleepWrong.start();
                    }
                }.start();
                ViewCompat.animate(mImageView2).withStartAction(new Runnable(){
                    @Override
                    public void run()
                    {
                        mImageView2.setVisibility(View.VISIBLE);
                    }
                })
                        .alpha(1f)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(250)
                        .start();

                Animation a = AnimationUtils.loadAnimation(SecondPageActivity.this, R.anim.visible);
                mImageView2.startAnimation(a);
            }
        });

        walking = (ImageButton) findViewById(R.id.walking);
        final ImageView mImageView3 = (ImageView) findViewById(R.id.mImageV);


        walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        mpWalkPerfect = MediaPlayer.create(SecondPageActivity.this, R.raw.perfect);
                        mpWalkPerfect.start();
                    }
                }.start();

                ViewCompat.animate(mImageView3).withStartAction(new Runnable(){
                    @Override
                    public void run()
                    {
                        mImageView3.setVisibility(View.VISIBLE);
                    }
                })
                        .alpha(1f)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(250)
                        .start();



                Animation a = AnimationUtils.loadAnimation(SecondPageActivity.this, R.anim.visible);
                mImageView3.startAnimation(a);
            }
        });
    }

    public void hideNavigator(){
        View decorView =getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);
    }

}