package com.example.amplangan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    EditText nama, no, alamat, kata_sandi, konfirmasi;
    Button mendaftar, kembali;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = (EditText) findViewById(R.id.nama);
        no = (EditText) findViewById(R.id.no);
        alamat = (EditText) findViewById(R.id.alamat);
        kata_sandi = (EditText)findViewById(R.id.kata_sandi);
        konfirmasi = (EditText)findViewById(R.id.skata_sandi);
        mendaftar = (Button) findViewById(R.id.button_mendaftar);
        kembali = (Button) findViewById(R.id.button_kembali);
        progressDialog = new ProgressDialog(register.this);
        mendaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNama = nama.getText().toString();
                String sNo = no.getText().toString();
                String sAlamat = alamat.getText().toString();
                String sKataSandi = kata_sandi.getText().toString();
                String sKonfirmasi = konfirmasi.getText().toString();

                if (sKataSandi.equals(sKonfirmasi) && !sKataSandi.equals("")){
                    ApiService.register(register.this,  sNama, sKataSandi, sNo, sAlamat, new ApiService.RegisterResponseListener() {
                        @Override
                        public void onSuccess(JSONObject response) {
                            Toast.makeText(register.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(register.this, MainActivity.class);
                            startActivity(intent);

                        }
                        @Override
                        public void onError(String message) {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "GAGAL! PASSWORD TIDAK SAMA!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(register.this, MainActivity.class);
                startActivity(resgisterIntent);
            }
        });
    }

}