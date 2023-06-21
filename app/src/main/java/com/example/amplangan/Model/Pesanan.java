package com.example.amplangan.Model;

import java.io.Serializable;
import java.util.Date;

public class Pesanan implements Serializable {
    private String id;
    private String jumlah_pesanan;
    private String total_harga;
    private String name;
    private String nohp;

    public Pesanan(String id, String jumlah_pesanan, String total_harga, String name, String nohp) {
        this.id = id;
        this.jumlah_pesanan = jumlah_pesanan;
        this.total_harga = total_harga;
        this.name = name;
        this.nohp = nohp;
    }

    public String getId() {
        return id;
    }

    public String getJumlah_pesanan() {
        return jumlah_pesanan;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public String getName() {
        return name;
    }

    public String getNohp() {
        return nohp;
    }
}
