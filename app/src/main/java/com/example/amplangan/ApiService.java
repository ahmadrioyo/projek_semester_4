package com.example.amplangan;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.amplangan.Model.Pesanan;
import com.example.amplangan.Model.Stok;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiService {
    private static String HOST = "http://192.168.1.151:8000";
    private static String API = HOST + "/api/";
    ProgressDialog progressDialog;

    //interface listener register
    public interface RegisterResponseListener {
        void onSuccess(JSONObject response);

        void onError(String message);
    }

    //interface listener login
    public interface LoginResponseListener {
        void onSuccess(JSONObject response);

        void onError(String message);
    }

    public interface ProductResponseListener {
        void onSuccess(List<Stok> stokList);
        void onError(String message);
    }
    public interface PesananResponseListener {
        void onSuccess(String response);
        void onError(String message);
    }

    public interface ReadPesananResponseListener {
        void onSuccess(List<Pesanan> pesananList);
        void onError(String message);
    }


    //register
    public static void register(Context context, String name, String password, String no_hp, String alamat, RegisterResponseListener listener) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                API + "register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String message = jsonObject.getString("message");
                    if (message.equals("success")) {
                        JSONObject userObj = jsonObject.getJSONObject("data");
                        listener.onSuccess(userObj);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                String responseBody = new String(error.networkResponse.data, "utf-8");
                                JSONObject jsonObject = new JSONObject(responseBody);
                                String message = jsonObject.getString("message");
                                if (message.equals("Email sudah terdaftar")) {
                                    listener.onError("Email sudah terdaftar , Silahkan gunakan email yang lain");
                                }
                            } catch (JSONException | UnsupportedEncodingException e) {
                                e.printStackTrace();
                                listener.onError("Gagal register: " + e.getMessage());
                            }
                        } else {
                            listener.onError("Gagal register: network response is null");
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("password", password);
                params.put("alamat", alamat);
                params.put("no_hp", no_hp);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    //login
    public static void login(Context context, String nama, String pass, LoginResponseListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, API + "login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String message = jsonObject.getString("message");
                    if (message.equals("login successfull")){
                        JSONObject userObj = jsonObject.getJSONObject("user");
                        String token = jsonObject.getString("token");
                        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", MODE_PRIVATE).edit();
                        editor.putString("token", token);
                        editor.putBoolean("isLogin", true);
                        editor.apply();
                        listener.onSuccess(userObj);
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                int statusCode = error.networkResponse.statusCode;
                                String responseBody = new String(error.networkResponse.data, "utf-8");
                                if (statusCode == 401) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(responseBody);
                                        String message = jsonObject.getString("message");
                                        if (message.equals("incorrect Nama")) {
                                            listener.onError("Nama Anda Belum Terdaftar");
                                        } else if (message.equals("incorrect password")) {
                                            listener.onError("Password Anda Salah");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        listener.onError("Gagal Masuk: " + e.getMessage());
                                    }

                                }
                            }catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            listener.onError("Gagal Masuk: network response is null");
                            Log.e("AuthServices", "Error: " + error.getMessage());
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", nama);
                params.put("password", pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    //Stok
    public static void Stok(Context context,final ProductResponseListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API + "stok",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            if (message.equals("success")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                List<Stok> stokList = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject productObj = jsonArray.getJSONObject(i);
                                    String id = productObj.getString("id");
                                    String id_users = productObj.getString("id_users");
                                    String nama_produk = productObj.getString("nama_produk");
                                    String harga_produk = productObj.getString("harga_produk");
                                    String jumlah_produk = productObj.getString("jumlah_produk");

                                    String createAtStr = productObj.getString("created_at");
                                    String updateAtStr = productObj.getString("updated_at");

                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
                                    Date createAt;
                                    Date updateAt;
                                    try {
                                        createAt = sdf.parse(createAtStr);
                                        updateAt = sdf.parse(updateAtStr);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        continue;
                                    }

                                    Stok stok = new Stok(id, id_users, nama_produk, harga_produk, jumlah_produk, createAt, updateAt);
                                    stokList.add(stok);

                                }
                                listener.onSuccess(stokList);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                String responseBody = new String(error.networkResponse.data, "utf-8");
                                JSONObject jsonObject = new JSONObject(responseBody);
                                String message = jsonObject.getString("message");
                                listener.onError(message);
                            } catch (JSONException | UnsupportedEncodingException e) {
                                e.printStackTrace();
                                listener.onError("Gagal mendapatkan product: " + e.getMessage());
                            }
                        } else {
                            listener.onError("Gagal mendapatkan product: network response is null");
                        }
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    //masukan pesanan
    public static void pesanan(Context context, String token, String jumlah,String id, String totalharga, final PesananResponseListener listener) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, API + "pesanan",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            if (message.equals("success")) {
                                listener.onSuccess("Berhasil membuat nomor antrian");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError("Invalid JSON response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                String responseBody = new String(error.networkResponse.data, "utf-8");
//                                JSONObject jsonObject = new JSONObject(responseBody);
//                                String message = jsonObject.getString("message");
                                listener.onError("gagal memesan");
                            } catch ( UnsupportedEncodingException e) {
                                e.printStackTrace();
                                listener.onError("Failed to update photo: " + e.getMessage());
                            }
                        } else {
                            listener.onError("Failed to update: network response is null");
                        }
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("jumlah_pesanan", jumlah);
                params.put("id_produk", id);
                params.put("total_harga", totalharga);
                return params;
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);
    }
    public static void readpesanan(Context context, String token, final ReadPesananResponseListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API + "pesanan-show",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            if (message.equals("success")){
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                List<Pesanan> pesananList = new ArrayList<>();
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject dataObj = jsonArray.getJSONObject(i);
                                    JSONObject userObj = dataObj.getJSONObject("users_mobile");
                                    String id = dataObj.getString("id");
                                    String jumlah = dataObj.getString("jumlah_pesanan");
                                    String total = dataObj.getString("total_harga");
                                    String name = userObj.getString("name");
                                    String no_hp = userObj.getString("no_hp");
                                    Pesanan pesanan = new Pesanan(id, jumlah,total,name, no_hp);
                                    pesananList.add(pesanan);
                                }
                                listener.onSuccess(pesananList);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                String responseBody = new String(error.networkResponse.data, "utf-8");
                                JSONObject jsonObject = new JSONObject(responseBody);
                                String message = jsonObject.getString("message");
                                listener.onError(message);
                            } catch (JSONException | UnsupportedEncodingException e) {
                                e.printStackTrace();
                                listener.onError("Gagal mendapatkan data user: " + e.getMessage());
                            }
                        } else {
                            listener.onError("Gagal mendapatkan data user: network response is null");
                        }
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}