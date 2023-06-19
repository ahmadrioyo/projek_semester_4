package com.example.amplangan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText nama, sandi;
    Button masuk, daftar;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = findViewById(R.id.nama);
        sandi = findViewById(R.id.sandi);
        progressDialog = new ProgressDialog(MainActivity.this);

        masuk = (Button) findViewById(R.id.button_masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNama = nama.getText().toString();
                String sKataSandi = sandi.getText().toString();
                ApiService.login(getApplicationContext(), sNama, sKataSandi, new ApiService.LoginResponseListener() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, home.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onError(String message) {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        daftar = (Button) findViewById(R.id.button_daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resgisterIntent = new Intent(MainActivity.this, register.class);
                startActivity(resgisterIntent);
                overridePendingTransition(0,0);
            }
        });
    }

}