package com.example.amplangan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class profil extends AppCompatActivity {
    ImageView cross;
    ImageView cek;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        cross = (ImageView) findViewById(R.id.crossx);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(profil.this, home.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        cek = (ImageView) findViewById(R.id.cekl);
        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(profil.this, home.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        simpan = (Button) findViewById(R.id.simpan1);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(profil.this, home.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });

    }
}