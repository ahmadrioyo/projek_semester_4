package com.example.amplangan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amplangan.Model.Stok;
import com.example.amplangan.R;
import com.example.amplangan.daftarmenu;

import java.text.SimpleDateFormat;
import java.util.List;

public class StokAdapter extends RecyclerView.Adapter<StokAdapter.VH> {

    private List<Stok> stokList;
    private Context context;

    public StokAdapter(List<Stok> stokList, Context context){
        this.stokList = stokList;
        this.context = context;
    }

    @NonNull
    @Override
    public StokAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_stok, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StokAdapter.VH holder, int position) {
        Stok stok = stokList.get(position);
        if (stok == null) {
            holder.nama_produk.setText(stokList.get(position).getNama_produk());
            holder.harga_produk.setText(stokList.get(position).getHarga_produk());
            holder.jumlah_produk.setText(stokList.get(position).getJumlah_produk());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, daftarmenu.class);
                        intent.putExtra("data", stokList.get(pos));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return stokList.size() == 0 ? 0:stokList.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        TextView nama_produk,harga_produk,jumlah_produk, desc;
        public VH(@NonNull View itemView){
            super(itemView);
            nama_produk = itemView.findViewById(R.id.produk);
            harga_produk = itemView.findViewById(R.id.harga);
            jumlah_produk = itemView.findViewById(R.id.jumlah);

            desc.setMaxLines(1);
            desc.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

}