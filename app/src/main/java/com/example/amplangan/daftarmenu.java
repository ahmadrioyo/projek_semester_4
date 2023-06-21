package com.example.amplangan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amplangan.Model.Stok;
import com.example.amplangan.Adapter.StokAdapter;
import com.example.amplangan.ApiService;
import com.example.amplangan.Model.Stok;

import java.util.ArrayList;
import java.util.List;

public class daftarmenu extends AppCompatActivity {
//    ImageView menu;
//    TextView produk, harga, jumlah;
//    Stok stok;
//    String nama_produk = "";
//    String harga_produk = "";
//    String jumlah_produk = "";

    RecyclerView recyclerView;
    List<Stok> stoks = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    CustomAdapterStok customAdapterStok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarmenu);
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(daftarmenu.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ApiService.Stok(daftarmenu.this, new ApiService.ProductResponseListener() {
            @Override
            public void onSuccess(List<Stok> stokList) {
                customAdapterStok = new CustomAdapterStok(stokList, daftarmenu.this);
                recyclerView.setAdapter(customAdapterStok);
                stoks = stokList;
            }

            @Override
            public void onError(String message) {
                Log.e("gagl get data" , message);
            }
        });
//        produk = findViewById(R.id.produk);
//        harga  = findViewById(R.id.harga);
//        jumlah = findViewById(R.id.jumlah);

    }
    public static class CustomAdapterStok extends RecyclerView.Adapter<CustomAdapterStok.ViewHolder> {
        private List<Stok> stokList;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapterStok(List<Stok> stokList, Context context) {
            this.stokList = stokList;
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.list_item_stok, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.nama.setText(stokList.get(position).getNama_produk());
            holder.jumlah.setText(stokList.get(position).getJumlah_produk());
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
//            String formattedDate = dateFormat.format(productList.get(position).getCreatedAt());
//            holder.date.setText(formattedDate);
            holder.harga.setText(stokList.get(position).getHarga_produk());
//            holder.desc.setText(productList.get(position).getDescription());
//            Glide.with(context).load(AuthServices.getImageProduct() + productList.get(position).getImage()).placeholder(R.drawable.placeholder_image).error(R.drawable.error_img).into(holder.img);
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = holder.getAdapterPosition();
//                    if (pos != RecyclerView.NO_POSITION) {
//                        Intent intent = new Intent(context, .class);
//                        intent.putExtra("data", stokList.get(pos));
//                        context.startActivity(intent);
//                    }
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return stokList.size();
        }
//
//        public void setProductList(List<Stok> productList) {
//            this.stokList = productList;
//        }
//
//        public List<Stok> getProductList() {
//            return stokList;
//        }


        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView nama,harga,jumlah;

            public ViewHolder(View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.img);
                nama = itemView.findViewById(R.id.produk);
                harga = itemView.findViewById(R.id.harga);
                jumlah = itemView.findViewById(R.id.jumlah);

            }
        }
    }
}