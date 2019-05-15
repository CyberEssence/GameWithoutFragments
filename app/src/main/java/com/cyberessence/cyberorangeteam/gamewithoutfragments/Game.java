package com.cyberessence.cyberorangeteam.gamewithoutfragments;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity implements GameFragment.onSomeEventListener{
    ImageButton dir1;
    ImageButton dir2;

    ImageButton drawing;
    ImageButton drinking;
    ImageButton eating;

    GameFragment gameFragment;
    FragmentTransaction fragCount;

    TextView question1;

    TextView ansVar1;
    TextView ansVar2;

    MediaPlayer mpDrinkWrong;
    MediaPlayer mpEatPerfect;
    MediaPlayer mpDrawWrong;

    public void hideNavigator(){
        View decorView =getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void startAnimation(View view, long delay){
        ViewCompat.animate(view)
                .setDuration(2000L)
                .alphaBy(0f)
                .alpha(1f)
                .setStartDelay(delay)
                .start();
    }

    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
    protected AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameFragment = new GameFragment();

        Intent intent = getIntent();

        someEvent(intent.getStringExtra("typeQuestion"));

        fragCount = getSupportFragmentManager().beginTransaction();
        fragCount.add(R.id.fragCount,gameFragment);
        fragCount.commit();

        hideNavigator();




        dir2 = (ImageButton) findViewById(R.id.back);

        dir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        drawing = (ImageButton) findViewById(R.id.drawing);
//        final ImageView mImageView = (ImageView) findViewById(R.id.imageV2);
//
//        drawing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread() {
//                    public void run() {
//                        mpDrawWrong = MediaPlayer.create(Game.this, R.raw.wrong);
//                        mpDrawWrong.start();
//                    }
//                }.start();
//                /*Toast.makeText(Game.this, "Неправильно!", Toast.LENGTH_SHORT).show();
//                Animation animation = AnimationUtils.loadAnimation(
//                        getApplicationContext(), R.anim.translate);
//                drawing.startAnimation(animation);
//
//                Animation animation1 = AnimationUtils.loadAnimation(
//                        getApplicationContext(), R.anim.scale);
//                eating.startAnimation(animation1);
//                Toast.makeText(Game.this, "Вот правильный ответ!", Toast.LENGTH_SHORT).show();*/
//                ViewCompat.animate(mImageView).withStartAction(new Runnable(){
//                    @Override
//                    public void run()
//                    {
//                        mImageView.setVisibility(View.VISIBLE);
//                    }
//                })
//                        .alpha(1f)
//                        .setInterpolator(new AccelerateDecelerateInterpolator())
//                        .setDuration(250)
//                        .start();
//
//                Animation a = AnimationUtils.loadAnimation(Game.this, R.anim.visible);
//                mImageView.startAnimation(a);
//            }
//        });

//        drinking = (ImageButton) findViewById(R.id.drinking);
//        final ImageView mImageView2 = (ImageView) findViewById(R.id.imageV3);
//
//        drinking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//               /* ansVar1 = (TextView) findViewById(R.id.ansVar1);
//                ansVar1.setText("Неправильно!");*/
//                new Thread() {
//                    public void run() {
//                        mpDrinkWrong = MediaPlayer.create(Game.this, R.raw.wrong);
//                        mpDrinkWrong.start();
//                    }
//                }.start();
//                /*Toast.makeText(Game.this, "Неправильно!", Toast.LENGTH_SHORT).show();
//                Animation animation = AnimationUtils.loadAnimation(
//                        getApplicationContext(), R.anim.translate);
//                drinking.startAnimation(animation);
//
//                Animation animation1 = AnimationUtils.loadAnimation(
//                        getApplicationContext(), R.anim.scale);
//                eating.startAnimation(animation1);
//                Toast.makeText(Game.this, "Вот правильный ответ!", Toast.LENGTH_SHORT).show();*/
//                ViewCompat.animate(mImageView2).withStartAction(new Runnable(){
//                    @Override
//                    public void run()
//                    {
//                        mImageView2.setVisibility(View.VISIBLE);
//                    }
//                })
//                        .alpha(1f)
//                        .setInterpolator(new AccelerateDecelerateInterpolator())
//                        .setDuration(250)
//                        .start();
//
//                Animation a = AnimationUtils.loadAnimation(Game.this, R.anim.visible);
//                mImageView2.startAnimation(a);
//            }
//
//        });
//
//        eating = (ImageButton) findViewById(R.id.eating);
//        final ImageView mImageView3 = (ImageView) findViewById(R.id.imageV);
//
//
//        eating.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread() {
//                    public void run() {
//                        mpEatPerfect = MediaPlayer.create(Game.this, R.raw.perfect);
//                        mpEatPerfect.start();
//                    }
//                }.start();
//
//                ViewCompat.animate(mImageView3).withStartAction(new Runnable(){
//                    @Override
//                    public void run()
//                    {
//                        mImageView3.setVisibility(View.VISIBLE);
//                    }
//                })
//                        .alpha(1f)
//                        .setInterpolator(new AccelerateDecelerateInterpolator())
//                        .setDuration(2500)
//                        .start();
//
//                /*try{
//                    Thread.sleep(3000);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }*/
//
//                Animation a = AnimationUtils.loadAnimation(Game.this, R.anim.visible);
//                mImageView3.startAnimation(a);
//            }
//        });
   }

    @Override
    public void someEvent(String type) {

            if(DataForQuestion.dataForQuestionsAction[0].getType().equals(type))
            gameFragment.setDataForQuestions(DataForQuestion.dataForQuestionsAction);
            else gameFragment.setDataForQuestions(DataForQuestion.dataForQuestionsFood);


    }
}