package com.example.amplangan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class profil extends AppCompatActivity {
    ImageView cross;
    ImageView cek;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        simpan = (Button) findViewById(R.id.simpan);
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