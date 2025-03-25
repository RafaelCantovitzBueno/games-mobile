package com.example.gamesmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Cup_main extends AppCompatActivity {

    ImageView left, middle, right;

    Button novojogo;

    List<Integer> copos;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cup_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.musicacup);

        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        left = (ImageView) findViewById(R.id.esquerda);
        middle = (ImageView) findViewById(R.id.meio);
        right = (ImageView) findViewById(R.id.direita);

        novojogo = (Button) findViewById(R.id.novojogo);

        copos = new ArrayList<>();
        copos.add(107); //Copo 1
        copos.add(207); //Copo 2
        copos.add(407); //Copo 3

        Collections.shuffle(copos);

        novojogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(copos);

                left.setImageResource(R.drawable.copoplasticovirado);
                middle.setImageResource(R.drawable.copoplasticovirado);
                right.setImageResource(R.drawable.copoplasticovirado);

                Animation anim_left = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left);
                Animation anim_middle = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.middle);
                Animation anim_right = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right);

                left.startAnimation(anim_left);
                middle.startAnimation(anim_middle);
                right.startAnimation(anim_right);

            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(copos.get(0) == 107){
                    left.setImageResource(R.drawable.bolavermelha);
                    Toast.makeText(Cup_main.this, "Parabéns!!", Toast.LENGTH_SHORT).show();
                }else if(copos.get(0) == 207){
                    left.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(0) == 407){
                    left.setImageResource(R.drawable.copoplastico);
                }

                if(copos.get(1) == 107){
                    middle.setImageResource(R.drawable.bolavermelha);
                }else if(copos.get(1) == 207){
                    middle.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(1) == 407){
                    middle.setImageResource(R.drawable.copoplastico);
                }

                if(copos.get(2) == 107){
                    right.setImageResource(R.drawable.bolavermelha);
                }else if(copos.get(2) == 207){
                    right.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(2) == 407){
                    right.setImageResource(R.drawable.copoplastico);
                }

            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(copos.get(0) == 107){
                    middle.setImageResource(R.drawable.bolavermelha);
                    Toast.makeText(Cup_main.this, "Parabéns!!", Toast.LENGTH_SHORT).show();
                }else if(copos.get(0) == 207){
                    middle.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(0) == 407){
                    middle.setImageResource(R.drawable.copoplastico);
                }

                if(copos.get(1) == 107){
                    left.setImageResource(R.drawable.bolavermelha);
                }else if(copos.get(1) == 207){
                    left.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(1) == 407){
                    left.setImageResource(R.drawable.copoplastico);
                }

                if(copos.get(2) == 107){
                    right.setImageResource(R.drawable.bolavermelha);
                }else if(copos.get(2) == 207){
                    right.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(2) == 407){
                    right.setImageResource(R.drawable.copoplastico);
                }

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(copos.get(0) == 107){
                    right.setImageResource(R.drawable.bolavermelha);
                    Toast.makeText(Cup_main.this, "Parabéns!!", Toast.LENGTH_SHORT).show();
                }else if(copos.get(0) == 207){
                    right.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(0) == 407){
                    right.setImageResource(R.drawable.copoplastico);
                }

                if(copos.get(1) == 107){
                    middle.setImageResource(R.drawable.bolavermelha);
                }else if(copos.get(1) == 207){
                    middle.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(1) == 407){
                    middle.setImageResource(R.drawable.copoplastico);
                }

                if(copos.get(2) == 107){
                    left.setImageResource(R.drawable.bolavermelha);
                }else if(copos.get(2) == 207){
                    left.setImageResource(R.drawable.copoplastico);
                }else if(copos.get(2) == 407){
                    left.setImageResource(R.drawable.copoplastico);
                }

            }
        });

        Button bt_voltar = (Button) findViewById(R.id.bt_voltar);

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

}