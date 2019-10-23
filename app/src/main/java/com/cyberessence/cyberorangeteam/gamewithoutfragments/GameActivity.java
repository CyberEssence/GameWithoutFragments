package com.cyberessence.cyberorangeteam.gamewithoutfragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements GameFragmentActivity.onSomeEventListener{
    ImageButton dir1;
    ImageButton dir2;

    ImageButton drawing;
    ImageButton drinking;
    ImageButton eating;

    GameFragmentActivity gameFragmentActivity;
    FragmentTransaction fragCount;

    TextView question1;

    TextView ansVar1;
    TextView ansVar2;

    MediaPlayer mpDrinkWrong;
    MediaPlayer mpEatPerfect;
    MediaPlayer mpDrawWrong;

    Button buttonSelectMode;
    int previousSelectedMode = 0;

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

        gameFragmentActivity = new GameFragmentActivity();

        Intent intent = getIntent();

        someEvent(intent.getStringExtra("typeQuestion"));

        fragCount = getSupportFragmentManager().beginTransaction();
        fragCount.add(R.id.fragCount, gameFragmentActivity);
        fragCount.commit();

        hideNavigator();


        dir2 = (ImageButton) findViewById(R.id.back);

        dir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonSelectMode = (Button) findViewById(R.id.buttonSelectMode);

        buttonSelectMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                openContextMenu(v);
            }
        });
   }

    @Override
    public void someEvent(String type) {

            if(DataForQuestion.dataForQuestionsAction[0].getType().equals(type))
            gameFragmentActivity.setDataForQuestions(DataForQuestion.dataForQuestionsAction);
            else gameFragmentActivity.setDataForQuestions(DataForQuestion.dataForQuestionsFood);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem itemGame = menu.findItem(R.id.itemGame);
        MenuItem itemRandomGame = menu.findItem(R.id.itemRandomGame);

        if (previousSelectedMode==1)
        {
            itemGame.setChecked(true);
        }
        else if (previousSelectedMode==2)
        {
            itemRandomGame.setChecked(true);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.itemGame)
        {
            previousSelectedMode = 1;
            Toast.makeText(getBaseContext(),"Обычный режим",Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId()==R.id.itemRandomGame)
        {
            previousSelectedMode = 2;
            Toast.makeText(getBaseContext(),"Случайные карточки", Toast.LENGTH_LONG).show();
        }

        return super.onContextItemSelected(item);
    }
}