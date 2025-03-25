package com.example.gamesmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
public class Jokenpo_main extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokenpo_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.musicajokenpo);

        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        Button bt_voltar = (Button) findViewById(R.id.bt_voltar);
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
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

    public void selecionadoPedra(View view) {
        this.opcaoSelecionada("pedra");
    }

    public void selecionadoPapel(View view) {
        this.opcaoSelecionada("papel");
    }

    public void selecionadoTesoura(View view) {
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String opcaoSelecionada) {

        TextView textResultado = findViewById(R.id.textResultado);
        ImageView imageResultado = findViewById(R.id.imageResultado);

        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoMaquina = opcoes[numero];

        switch (opcaoMaquina) {
            case "pedra":
                imageResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        if ((opcaoMaquina == "tesoura" && opcaoSelecionada == "papel") ||
                (opcaoMaquina == "papel" && opcaoSelecionada == "pedra") ||
                (opcaoMaquina == "pedra" && opcaoSelecionada == "tesoura")) {

            textResultado.setText("Você perdeu");
        } else if ((opcaoSelecionada == "tesoura" && opcaoMaquina == "papel") ||
                (opcaoSelecionada == "papel" && opcaoMaquina == "pedra") ||
                (opcaoSelecionada == "pedra" && opcaoMaquina == "tesoura")) {

            textResultado.setText("Você ganhou");
        } else {
            textResultado.setText("Empate");
        }


    }


}