package com.cyberessence.cyberorangeteam.gamewithoutfragments;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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

    Button buttonSelectMode;

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
   }

    @Override
    public void someEvent(String type) {

            if(DataForQuestion.dataForQuestionsAction[0].getType().equals(type))
            gameFragment.setDataForQuestions(DataForQuestion.dataForQuestionsAction);
            else gameFragment.setDataForQuestions(DataForQuestion.dataForQuestionsFood);


    }
}