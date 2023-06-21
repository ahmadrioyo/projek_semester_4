package com.example.amplangan;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
<<<<<<< Updated upstream
    private static String HOST = "http://192.168.1.3:8000";
=======
    private static String HOST = "http://192.168.1.151:8000";
>>>>>>> Stashed changes
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
        void onSuccess(List<Stok> productList);
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
    public static void stok(Context context,final ProductResponseListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API + "stok",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            if (message.equals("success")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                List<Stok> productList = new ArrayList<>();
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
                                    productList.add(stok);

                                }
                                listener.onSuccess(productList);
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
}