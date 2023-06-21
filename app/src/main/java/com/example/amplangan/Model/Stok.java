package com.example.amplangan.Model;

import java.io.Serializable;
import java.util.Date;

public class Stok implements Serializable {
    private String id;
    private String id_users;
    private String nama_produk;
    private String harga_produk;
    private String jumlah_produk;
    private Date createdAt;
    private Date updateAt;

    public Stok(String id, String id_users, String nama_produk, String harga_produk, String jumlah_produk, Date createdAt, Date updateAt){
        this.id = id;
        this.id_users = id_users;
        this.nama_produk = nama_produk;
        this.harga_produk = harga_produk;
        this.jumlah_produk = jumlah_produk;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public String getId() {
        return id;
    }

    public String getId_users() {
        return id_users;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getHarga_produk() {
        return harga_produk;
    }

    public String getJumlah_produk() {
        return jumlah_produk;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
