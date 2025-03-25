package com.example.gamesmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ImageView jogoColor, jogoCobra, jogoMemoria, jogoCopo, jogoJokenpo;
    Button nomes;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.musicaintro);

        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        exibirMensagemDeBoasVindas();

        jogoColor = findViewById(R.id.color_game);
        jogoCobra = findViewById(R.id.snake_game);
        jogoMemoria = findViewById(R.id.memory_game);
        jogoCopo = findViewById(R.id.cup_game);
        jogoJokenpo = findViewById(R.id.jokenpo_game);
        nomes = findViewById(R.id.creditos);

        jogoColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent jogocolor = new Intent(getApplicationContext(), Color_Open.class);
                startActivity(jogocolor);
            }
        });


        jogoCobra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent jogocobra = new Intent(getApplicationContext(), Snake_Open.class);
                startActivity(jogocobra);
            }
        });

        jogoMemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent jogomemoria = new Intent(getApplicationContext(), Memory_Open.class);
                startActivity(jogomemoria);
            }
        });

        jogoCopo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent jogocopo = new Intent(getApplicationContext(), Cup_Open.class);
                startActivity(jogocopo);
            }
        });

        jogoJokenpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent jogojokenpo = new Intent(getApplicationContext(), Jokenpo_Open.class);
                startActivity(jogojokenpo);
            }
        });

        nomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nomes = new Intent(getApplicationContext(), Creditos.class);
                startActivity(nomes);
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
    private void exibirMensagemDeBoasVindas() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bem-vindo");
        builder.setMessage("Olá! Bem-vindo à Games Mobile! A ideia do nosso trabalho é intreter o usuário com diversos mini-jogos, como o famoso jogo da cobrinha, jogo da memória, jokenpo, dentre outros... Para melhor desempenho, recomendados o uso do Android 11 para tal aplicação.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}