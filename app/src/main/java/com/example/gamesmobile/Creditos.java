package com.example.gamesmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;

public class Creditos extends AppCompatActivity {

    Button jogos;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        mediaPlayer = MediaPlayer.create(this, R.raw.musicacreditos);
        mediaPlayer.start();

        jogos = findViewById(R.id.jogos);

        jogos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent jogo = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(jogo);
            }
        });
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
}