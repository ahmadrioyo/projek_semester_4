package com.example.amplangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class register extends AppCompatActivity {

    Button mendaftar;
    Button kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mendaftar = (Button) findViewById(R.id.button_mendaftar);
        mendaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(register.this, MainActivity.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
            }
        });

        kembali = (Button) findViewById(R.id.button_kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(register.this, MainActivity.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
            }
        });
    }
}