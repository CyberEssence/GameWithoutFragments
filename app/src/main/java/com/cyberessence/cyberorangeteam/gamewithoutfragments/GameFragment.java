package com.cyberessence.cyberorangeteam.gamewithoutfragments;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;



public class GameFragment extends Fragment implements View.OnClickListener {

    ImageButton img1,img2,img3,answer1,answer2,answer3;
    int currentBtnPressed;
    int currentQuestion = 0;
    static int animDur = 3000;
    DataForQuestion[] dataForQuestions;

    public void setDataForQuestions(DataForQuestion[] dataForQuestions) {
        this.dataForQuestions = dataForQuestions;
    }

    public interface onSomeEventListener {
         void someEvent(String type);
    }

    private onSomeEventListener mListener;

    public GameFragment() {
        // Required empty public constructor
    }

    MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, null);

        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);



        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);



        setNewQuestion();

        return view;
    }

    public void setNewQuestion(){
        img1.setImageResource(dataForQuestions[currentQuestion].getImgId()[0]);
        img2.setImageResource(dataForQuestions[currentQuestion].getImgId()[1]);
        img3.setImageResource(dataForQuestions[currentQuestion].getImgId()[2]);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), dataForQuestions[currentQuestion].getSoundId());
        mediaPlayer.start();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img1:
                currentBtnPressed = 1;
                makeTickOrCross(currentBtnPressed);
                break;
            case R.id.img2:
                currentBtnPressed = 2;
                makeTickOrCross(currentBtnPressed);
                break;
            case R.id.img3:
                currentBtnPressed = 3;
                makeTickOrCross(currentBtnPressed);
                break;
        }

        if (currentBtnPressed == dataForQuestions[currentQuestion].getAnswer()){
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.perfect);
            mediaPlayer.start();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    new CountDownTimer(3000,3000){
                        public void onTick(long millisUntilFinished) {
                            mediaPlayer = MediaPlayer.create(getContext(), R.raw.applause);
                            mediaPlayer.start();
                            mediaPlayer.setVolume(0.5f,0.5f);
                        }
                        public void onFinish() {
                            mediaPlayer.stop();
                            if (dataForQuestions.length != currentQuestion+1){
                                currentQuestion++;
                                setNewQuestion();
                            } else {
                                currentQuestion = 0;
                                setNewQuestion();
                            }
                        }

                    }.start();
                }
            },2000);




        }
        else {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.wrong);
            mediaPlayer.start();
        }



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onSomeEventListener) {
            mListener = (onSomeEventListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onSomeEventListener");
        }
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        releaseMP();
    }

// выделить любой случай в отдельный метод и заменить этот мтеод на него и прикрутить на клик
    public void makeTickOrCross(int currentBtnPressed){
        final Handler handler = new Handler();

        switch (currentBtnPressed){
            case 1:
                    if (currentBtnPressed ==  dataForQuestions[currentQuestion].getAnswer()){
                        answer1.setImageResource(R.drawable.tick);
                        animDur = 4000;

                    } else {
                        answer1.setImageResource(R.drawable.cross);
                        animDur = 2500;
                    }


                ViewCompat.animate(answer1).withStartAction(new Runnable(){
                    @Override
                    public void run()
                    {
                        answer1.setVisibility(View.VISIBLE);
                    }
                })
                        .alpha(1f)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(animDur)
                        .start();


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.animate(answer1).withStartAction(new Runnable(){
                            @Override
                            public void run()
                            {
                                answer1.setVisibility(View.VISIBLE);
                            }
                        })
                                .alpha(0f)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setDuration(animDur)
                                .start();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                answer1.setVisibility(View.INVISIBLE);

                            }
                        },2500);
                    }
                },animDur);

                break;
            case 2:
                if (currentBtnPressed ==  dataForQuestions[currentQuestion].getAnswer()){
                    answer2.setImageResource(R.drawable.tick);
                    animDur = 4000;

                } else {
                    answer2.setImageResource(R.drawable.cross);
                    animDur = 2500;
                }

                ViewCompat.animate(answer2).withStartAction(new Runnable(){
                    @Override
                    public void run()
                    {
                        answer2.setVisibility(View.VISIBLE);
                    }
                })
                        .alpha(1f)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(animDur)
                        .start();


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.animate(answer2).withStartAction(new Runnable(){
                            @Override
                            public void run()
                            {
                                answer2.setVisibility(View.VISIBLE);
                            }
                        })
                                .alpha(0f)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setDuration(animDur)
                                .start();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                answer2.setVisibility(View.INVISIBLE);

                            }
                        },2500);
                    }
                },animDur);


                break;
            case 3:
                if (currentBtnPressed ==  dataForQuestions[currentQuestion].getAnswer()){
                    answer3.setImageResource(R.drawable.tick);
                    animDur = 4000;

                } else {
                    answer3.setImageResource(R.drawable.cross);
                    animDur = 2500;
                }
                ViewCompat.animate(answer3).withStartAction(new Runnable(){
                    @Override
                    public void run()
                    {
                        answer3.setVisibility(View.VISIBLE);
                    }
                })
                        .alpha(1f)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(animDur)
                        .start();


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ViewCompat.animate(answer3).withStartAction(new Runnable(){
                            @Override
                            public void run()
                            {
                                answer3.setVisibility(View.VISIBLE);
                            }
                        })
                                .alpha(0f)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setDuration(animDur)
                                .start();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                answer3.setVisibility(View.INVISIBLE);

                            }
                        },2500);
                    }
                },animDur);



                break;
        }
    }
}
