package com.example.amplangan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amplangan.ApiService;
import com.example.amplangan.Model.Stok;
import com.example.amplangan.R;
import com.example.amplangan.daftarmenu;

import java.text.SimpleDateFormat;
import java.util.List;

public class StokAdapter extends RecyclerView.Adapter<StokAdapter.VH> {

    private List<Stok> stokList;
    private Context context;

    public StokAdapter(List<Stok> productList, Context context){
        this.stokList = productList;
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
        Stok product = stokList.get(position);
        if (product == null) {
            holder.price.setText(stokList.get(position).getNama_produk());
            holder.jumlah.setText(stokList.get(position).getHarga_produk());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String formattedDate = dateFormat.format(stokList.get(position).getCreatedAt());
            holder.date.setText(formattedDate);
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
        return productList.size() == 0 ? 0:productList.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        ImageView logo,img;
        TextView price,jumlah,date,name,desc;
        public VH(@NonNull View itemView){
            super(itemView);
            logo = itemView.findViewById(R.id.logo);
            img = itemView.findViewById(R.id.pimage);
            price = itemView.findViewById(R.id.pprice);
            jumlah = itemView.findViewById(R.id.pjumlah);
            date = itemView.findViewById(R.id.ptanggal);
            name = itemView.findViewById(R.id.pname);
            desc = itemView.findViewById(R.id.pdesc);

            desc.setMaxLines(1);
            desc.setEllipsize(TextUtils.TruncateAt.END);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                logo.setBackgroundResource(R.drawable.bg_logo);
            }
        }
    }

}