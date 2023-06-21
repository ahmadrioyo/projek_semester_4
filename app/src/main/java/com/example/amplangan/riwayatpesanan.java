package com.example.amplangan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amplangan.Model.Pesanan;
import com.example.amplangan.Model.Stok;

import java.util.ArrayList;
import java.util.List;

public class riwayatpesanan extends AppCompatActivity {
    ImageView backrp;
    RecyclerView recyclerView;
    List<Pesanan> pesanans = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    CustomAdapterPesan customAdapterPesan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayatpesanan);

        backrp = (ImageView) findViewById(R.id.backrp);
        backrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(riwayatpesanan.this, home.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        recyclerView = findViewById(R.id.rv_riwayat);
        linearLayoutManager = new LinearLayoutManager(riwayatpesanan.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        SharedPreferences preferences = riwayatpesanan.this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        String token = preferences.getString("token", "");
        ApiService.readpesanan(riwayatpesanan.this, token, new ApiService.ReadPesananResponseListener() {
            @Override
            public void onSuccess(List<Pesanan> pesananList) {
                pesanans = pesananList; // Update data pesanans
                customAdapterPesan = new CustomAdapterPesan(pesananList, riwayatpesanan.this);
                recyclerView.setAdapter(customAdapterPesan);
                customAdapterPesan.notifyDataSetChanged(); // Panggil notifyDataSetChanged() setelah memperbarui data
            }

            @Override
            public void onError(String message) {
                Log.e("gagal get data" , message);
            }
        });
    }
    public static class CustomAdapterPesan extends RecyclerView.Adapter<CustomAdapterPesan.ViewHolder> {
        private List<Pesanan> pesanList;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapterPesan(List<Pesanan> pesanList, Context context) {
            this.pesanList = pesanList;
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public CustomAdapterPesan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.list_item_pesanan, parent, false);
            return new CustomAdapterPesan.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomAdapterPesan.ViewHolder holder, int position) {
            holder.nama.setText(pesanList.get(position).getName());
            holder.jumlah.setText(pesanList.get(position).getJumlah_pesanan());
            holder.harga.setText(pesanList.get(position).getTotal_harga());
            holder.noHp.setText(pesanList.get(position).getNohp());
        }

        @Override
        public int getItemCount() {
            return pesanList.size();
        }



        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView nama,harga,jumlah, noHp;

            public ViewHolder(View itemView) {
                super(itemView);
                nama = itemView.findViewById(R.id.etnama);
                harga = itemView.findViewById(R.id.ettotal);
                jumlah = itemView.findViewById(R.id.etjumlah);
                noHp = itemView.findViewById(R.id.etno);

            }
        }
    }

}