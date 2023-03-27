package com.example.otomarket.model;

import java.util.List;

public class HoaDon {
    int stt, id, idKH;
    String diachi, sdt;
    String tongtien;
    String ngaygio;
    List<Item> item;

    public HoaDon(int stt, int id, int idKH, String diachi, String sdt, String tongtien, String ngaygio, List<Item> item) {
        this.stt = stt;
        this.id = id;
        this.idKH = idKH;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tongtien = tongtien;
        this.ngaygio = ngaygio;
        this.item = item;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgaygio() {
        return ngaygio;
    }

    public void setNgaygio(String ngaygio) {
        this.ngaygio = ngaygio;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
