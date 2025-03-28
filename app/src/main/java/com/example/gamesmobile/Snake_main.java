package com.example.gamesmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;

public class Snake_main extends AppCompatActivity implements SurfaceHolder.Callback {


    private final List<Snake_Points> snakePointsList = new ArrayList<>();
    private SurfaceView surfaceView;
    private TextView scoreTV;

    private SurfaceHolder surfaceHolder;
    private String movingPosition = "right";

    private int score = 0;

    private static final int pointSize = 28;

    private static final int defaultTalePoints = 3;

    private static final int snakeColor = Color.YELLOW;

    private static final int snakeMovingSpeed = 800;

    private int positionX, positionY;

    private Timer timer;

    private Canvas canvas = null;


    private Paint pointColor = null;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_main);

        surfaceView = findViewById(R.id.surfaceView);
        scoreTV = findViewById(R.id.scoreTV);

        final AppCompatImageButton topBtn = findViewById(R.id.topBtn);
        final AppCompatImageButton leftBtn= findViewById(R.id.leftBtn);
        final AppCompatImageButton rightBtn = findViewById(R.id.rightBtn);
        final AppCompatImageButton bottomBtn = findViewById(R.id.bottomBtn);

        surfaceView.getHolder().addCallback(this);

        Button bt_voltar = (Button) findViewById(R.id.bt_voltar);

        mediaPlayer = MediaPlayer.create(this, R.raw.musicacobra);

        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        topBtn.setOnClickListener(view -> {

            if(!movingPosition.equals("bottom")){
                movingPosition = "top";
            }
        });

        leftBtn.setOnClickListener(view -> {

            if(!movingPosition.equals("right")){
                movingPosition = "left";
            }
        });

        rightBtn.setOnClickListener(view -> {

            if(!movingPosition.equals("left")){
                movingPosition = "right";
            }
        });

        bottomBtn.setOnClickListener(view -> {

            if(!movingPosition.equals("top")){
                movingPosition = "bottom";
            }
        });

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
        finish();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        this.surfaceHolder = surfaceHolder;

        init();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

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

    private void init(){

        snakePointsList.clear();

        scoreTV.setText("0");
        score = 0;

        movingPosition ="right";

        int startPositionX = (pointSize) * defaultTalePoints;

        for(int i=0;i<defaultTalePoints;i++){

            Snake_Points snakePoints = new Snake_Points(startPositionX, pointSize);
            snakePointsList.add(snakePoints);

            startPositionX = startPositionX - (pointSize * 2);
        }

        addPoints();

        moveSnake();
    }

    private void addPoints(){

        int surfaceWidth = surfaceView.getWidth() - (pointSize * 2);
        int surfaceHeight = surfaceView.getHeight() - (pointSize * 2);

        int randomXPosition = new Random().nextInt( surfaceWidth / pointSize);
        int randomYPosition = new Random().nextInt( surfaceHeight / pointSize);

        for(int i=0;i<snakePointsList.size();i++){
            if(snakePointsList.get(i).getPositionX() == randomXPosition &&
                    snakePointsList.get(i).getPositionY() == randomYPosition ){
                randomXPosition = new Random().nextInt( surfaceWidth / pointSize);
                randomYPosition = new Random().nextInt( surfaceHeight / pointSize);
                i = 0;
            }
        }
        if((randomXPosition % 2) !=0){
            randomXPosition = randomXPosition + 1;
        }

        if((randomYPosition % 2) !=0){
            randomYPosition = randomYPosition + 1;
        }

        positionX = (pointSize * randomXPosition) + pointSize;
        positionY = (pointSize * randomYPosition) + pointSize;
    }

    private void moveSnake(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                int headPositionX = snakePointsList.get(0).getPositionX();
                int headPositionY = snakePointsList.get(0).getPositionY();

                if( headPositionX == positionX && positionY == headPositionY){
                    growSnake();

                    addPoints();
                }

                switch (movingPosition){
                    case "right" :
                        snakePointsList.get(0).setPositionX(headPositionX + (pointSize* 2));
                        snakePointsList.get(0).setPositionY(headPositionY);
                        break;
                    case "left" :
                        snakePointsList.get(0).setPositionX(headPositionX - (pointSize* 2));
                        snakePointsList.get(0).setPositionY(headPositionY);
                        break;
                    case "top" :
                        snakePointsList.get(0).setPositionX(headPositionX);
                        snakePointsList.get(0).setPositionY(headPositionY - (pointSize * 2));
                        break;
                    case "bottom" :
                        snakePointsList.get(0).setPositionX(headPositionX);
                        snakePointsList.get(0).setPositionY(headPositionY  + (pointSize * 2));
                        break;
                }

                if (checkGameOver(headPositionX, headPositionY)) {

                    timer.purge();
                    timer.cancel();

                    AlertDialog.Builder builder = new AlertDialog.Builder( Snake_main.this);
                    builder.setMessage("Sua pontuação: "+ score);
                    builder.setTitle("Fim de Jogo");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Recomeçar", (dialogInterface, i) -> {

                        init();
                    });

                    runOnUiThread(builder::show);
                }
                else{

                    canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);

                        canvas.drawCircle(snakePointsList.get(0).getPositionX(), snakePointsList.get(0).getPositionY(), pointSize, createPointColor());

                        canvas.drawCircle(positionX, positionY, pointSize, createPointColor());

                        for (int i = 1; i < snakePointsList.size(); i++) {

                            int getTempPositionX = snakePointsList.get(i).getPositionX();
                            int getTempPositionY = snakePointsList.get(i).getPositionY();

                            snakePointsList.get(i).setPositionX(headPositionX);
                            snakePointsList.get(i).setPositionY(headPositionY);
                            canvas.drawCircle(snakePointsList.get(i).getPositionX(), snakePointsList.get(i).getPositionY(), pointSize, createPointColor());

                            headPositionX = getTempPositionX;
                            headPositionY = getTempPositionY;

                        }

                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        },1000 - snakeMovingSpeed, 1000 - snakeMovingSpeed);
    }

    private void growSnake(){

        Snake_Points snakePoints = new Snake_Points(0,0);
        snakePointsList.add(snakePoints);
        score++;
        runOnUiThread(() -> scoreTV.setText(String.valueOf(score)));

    }

    private boolean checkGameOver(int headPositionX, int headPositionY){
        boolean gameOver = false;

        if(snakePointsList.get(0).getPositionX() <0 || snakePointsList.get(0).getPositionY() < 0
                || snakePointsList.get(0).getPositionX() >= surfaceView.getWidth()
                || snakePointsList.get(0).getPositionY() >= surfaceView.getHeight())
        {
            gameOver = true;
        }
        else{
            for(int i=0;i<snakePointsList.size();i++){
                if(headPositionX == snakePointsList.get(i).getPositionX()
                        && headPositionY == snakePointsList.get(i).getPositionY())
                {
                    gameOver = true;
                    break;
                }
            }
        }
        return gameOver;

    }

    private Paint createPointColor(){

        if(pointColor == null){

            pointColor = new Paint();
            pointColor.setColor(snakeColor);
            pointColor.setStyle(Paint.Style.FILL);
            pointColor.setAntiAlias(true);
        }

        return pointColor;
    }

}