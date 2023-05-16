package com.example.amplangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button masuk;
    Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masuk = (Button) findViewById(R.id.button_masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(MainActivity.this, register.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
            }
        });

        daftar = (Button) findViewById(R.id.button_daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(MainActivity.this, register.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
            }
        });

    }
}