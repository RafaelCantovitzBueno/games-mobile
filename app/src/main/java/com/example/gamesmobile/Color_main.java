package com.example.gamesmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Color_main extends AppCompatActivity {

    ImageView iv_button, iv_arrow;
    TextView tv_levels;
    ProgressBar progressBar;

    Handler handler;
    Runnable runnable;

    Random r;

    private final static  int STATE_AZUL = 1;
    private final static  int STATE_VERMELHO = 4;
    private final static  int STATE_AMARELO = 3;
    private final static  int STATE_VERDE = 2;

    private MediaPlayer mediaPlayer;

    int buttonState = STATE_AZUL;
    int arrowState = STATE_AZUL;

    int currentTime = 4000;
    int startTime = 4000;

    int currentPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.musicasetas);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        iv_button = findViewById(R.id.iv_button);
        iv_arrow = findViewById(R.id.iv_arrow);
        tv_levels = findViewById(R.id.tv_levels);
        progressBar = findViewById(R.id.progressBar);


        progressBar.setMax(startTime);
        progressBar.setProgress(startTime);

        tv_levels.setText("Points: " + currentPoints);

        r = new Random();
        arrowState = r.nextInt(4) + 1;
        setArrowImage(arrowState);

        Button bt_voltar = (Button) findViewById(R.id.bt_voltar);
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        iv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonImage(setButtonPosition(buttonState));
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentTime = currentTime - 100;
                progressBar.setProgress(currentTime);
                if (currentTime > 0){
                    handler.postDelayed(runnable, 100);
                } else {
                    if (buttonState == arrowState) {
                        currentPoints = currentPoints + 1;
                        tv_levels.setText("points: " + currentPoints);

                        startTime = startTime - 100;
                        if (startTime < 1000){
                            startTime = 2000;
                        }
                        progressBar.setMax(startTime);
                        currentTime = startTime;
                        progressBar.setProgress(currentTime);

                        arrowState = r.nextInt(4) + 1;
                        setArrowImage(arrowState);

                        handler.postDelayed(runnable, 100);
                    } else {
                        iv_button.setEnabled(false);
                        Toast.makeText(Color_main.this, "Fim de Jogo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        handler.postDelayed(runnable, 100);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    private void setArrowImage(int state){
        switch (state) {
            case STATE_AZUL:
                iv_arrow.setImageResource(R.drawable.azul);
                arrowState = STATE_AZUL;
                break;
            case STATE_VERDE:
                iv_arrow.setImageResource(R.drawable.verde);
                arrowState = STATE_VERDE;
                break;
            case STATE_AMARELO:
                iv_arrow.setImageResource(R.drawable.amarelo);
                arrowState = STATE_AMARELO;
                break;
            case STATE_VERMELHO:
                iv_arrow.setImageResource(R.drawable.vermelho);
                arrowState = STATE_VERMELHO;
                break;
        }
    }

    private void setRotation(final ImageView image, final int drawable) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(100);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }


            @Override
            public void onAnimationEnd(Animation animation) {
                iv_button.setImageResource(drawable);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(rotateAnimation);
    }
    private int setButtonPosition(int position){
        position = position + 1;
        if(position == 5){
            position = 1;
        }
        return position;
    }

    private void setButtonImage(int state){
        switch (state) {
            case STATE_AZUL:
                setRotation(iv_button, R.drawable.azulemcima);
                buttonState = STATE_AZUL;
                break;
            case STATE_VERDE:
                setRotation(iv_button, R.drawable.verdeemcima);
                buttonState = STATE_VERDE;
                break;
            case STATE_AMARELO:
                setRotation(iv_button, R.drawable.amareloemcima);
                buttonState = STATE_AMARELO;
                break;
            case STATE_VERMELHO:
                setRotation(iv_button, R.drawable.vermelhoemcima);
                buttonState = STATE_VERMELHO;
                break;
        }
    }
}