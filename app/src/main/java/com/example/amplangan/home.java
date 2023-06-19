package com.example.amplangan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class home extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    LinearLayout riwayatpesanan, daftarmenu, tentangkami;
    Button pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pesan = findViewById(R.id.pesan);

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, pesanan.class);
                startActivity(intent);
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