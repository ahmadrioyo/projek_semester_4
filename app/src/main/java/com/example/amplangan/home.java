package com.example.amplangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class home extends AppCompatActivity {
    ImageView profil;

    LinearLayout riwayatpesanan, daftarmenu, tentangkami;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profil = (ImageView) findViewById(R.id.profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(home.this, profil.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        riwayatpesanan = findViewById(R.id.riwayatpesanan);
        riwayatpesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(home.this, riwayatpesanan.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        daftarmenu = findViewById(R.id.daftarmenu);
        daftarmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(home.this, daftarmenu.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        tentangkami = findViewById(R.id.tentangkami);
        tentangkami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(home.this, tentangkami.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });
    }
}