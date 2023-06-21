package com.example.amplangan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amplangan.Model.Pesanan;
import com.example.amplangan.Model.Stok;

import java.util.ArrayList;
import java.util.List;

public class pesanan extends AppCompatActivity {
    Spinner spinner;
    EditText et_jumlah, total;
    Button btnpesan;
    String selectedId;
    String selectedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        spinner = findViewById(R.id.spinner);
        et_jumlah = findViewById(R.id.editTextAlamat);
        total = findViewById(R.id.editTextTotal);
        btnpesan = findViewById(R.id.simpan1);
        total.setEnabled(false);
        SharedPreferences preferences = pesanan.this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        String token = preferences.getString("token", "");

        List<Stok> stoks = new ArrayList<>();
        ApiService.Stok(getApplicationContext(), new ApiService.ProductResponseListener() {
            @Override
            public void onSuccess(List<Stok> stokList) {
                List<String> stokName = new ArrayList<>();
                for (Stok stok : stokList) {
                    stokName.add(stok.getNama_produk());
                }
                stoks.clear();
                stoks.addAll(stokList);
                StokSpinner stokSpinner = new StokSpinner(getApplicationContext(), stoks);
                spinner.setAdapter(stokSpinner);
                stokSpinner.notifyDataSetChanged();
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Stok selected = (Stok) parent.getItemAtPosition(position);
                        String selectedName = selected.getNama_produk();
                        selectedPrice = selected.getHarga_produk();
                        calculateJumlah();
                        // Cari objek Skin yang sesuai dengan nama yang dipilih
                        for (Stok stok : stokList) {
                            if (stok.getNama_produk().equals(selectedName)) {
                                selectedId = stok.getId();
                                selectedPrice = stok.getHarga_produk();
                                calculateJumlah();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onError(String message) {

            }
        });
        et_jumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateJumlah();
            }
        });
        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService.pesanan(pesanan.this, token, et_jumlah.getText().toString().trim(), selectedId, total.getText().toString().trim(), new ApiService.PesananResponseListener() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(pesanan.this, "Berhasil melakukan pemesanan", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(pesanan.this, home.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(pesanan.this, "Gagal melakukan pemesanan", Toast.LENGTH_LONG).show();
                        Log.e("error pesan", message);
                    }
                });
            }
        });
    }
    private void calculateJumlah() {
        String totalValue = et_jumlah.getText().toString().trim();
        if (!totalValue.isEmpty() && selectedPrice != null) {
            int totalAmount = Integer.parseInt(totalValue);
            int harga = Integer.parseInt(selectedPrice);
            int jumlah = totalAmount * harga;
            total.setText(String.valueOf(jumlah));
        } else {
            total.setText("");
        }
    }
    public class StokSpinner extends ArrayAdapter<Stok> {

        private LayoutInflater inflater;
        private List<Stok> stokList;

        public StokSpinner(Context context, List<Stok> stokList) {
            super(context, 0, stokList);
            this.stokList = stokList;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        private View getCustomView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.spinner_item, parent, false);
            }

            TextView textView = convertView.findViewById(R.id.names);
            textView.setText(stokList.get(position).getNama_produk());

            return convertView;
        }
    }
}