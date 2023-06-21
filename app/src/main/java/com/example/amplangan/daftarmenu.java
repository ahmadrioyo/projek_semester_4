package com.example.amplangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amplangan.Model.Stok;

public class daftarmenu extends AppCompatActivity {
    ImageView menu;
    TextView produk, harga, jumlah;
    Stok stok;
    String nama_produk = "";
    String harga_produk = "";
    String jumlah_produk = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarmenu);

        produk = findViewById(R.id.produk);
        harga  = findViewById(R.id.harga);
        jumlah = findViewById(R.id.jumlah);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            stok = (Stok) intent.getSerializableExtra("data");
            nama_produk = stok.getNama_produk();
            harga_produk = stok.getHarga_produk();
            jumlah_produk = stok.getJumlah_produk();
        }

        produk.setText(nama_produk);
        harga.setText(harga_produk);
        jumlah.setText(jumlah_produk);

        menu = (ImageView) findViewById(R.id.menub);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(daftarmenu.this, home.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });
    }
}