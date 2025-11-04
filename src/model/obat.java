/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import configDB.koneksi;

/**
 * Model Obat
 * CRUD + Fitur Cari
 * @author ACER
 */
public class obat {
    private int id_obat;
    private String nama_obat;
    private String jenis;
    private int stok;
    private double harga;

    // Getter
    public int getId_obat() { return id_obat; }
    public String getNama_obat() { return nama_obat; }
    public String getJenis() { return jenis; }
    public int getStok() { return stok; }
    public double getHarga() { return harga; }

    // -----------------------------
    // Ambil semua data obat
    // -----------------------------
    public static ArrayList<obat> getAll() {
        ArrayList<obat> list = new ArrayList<>();
        try {
            Connection conn = koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM obat");

            while (rs.next()) {
                obat o = new obat();
                o.id_obat = rs.getInt("id_obat");
                o.nama_obat = rs.getString("nama_obat");
                o.jenis = rs.getString("jenis");
                o.stok = rs.getInt("stok");
                o.harga = rs.getDouble("harga");
                list.add(o);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error tampil data: " + e.getMessage());
        }
        return list;
    }

    // -----------------------------
    // Cari data obat berdasarkan nama atau jenis
    // -----------------------------
    public static ArrayList<obat> cari(String keyword) {
        ArrayList<obat> list = new ArrayList<>();
        try {
            Connection conn = koneksi.getConnection();
            String sql = "SELECT * FROM obat WHERE nama_obat LIKE ? OR jenis LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                obat o = new obat();
                o.id_obat = rs.getInt("id_obat");
                o.nama_obat = rs.getString("nama_obat");
                o.jenis = rs.getString("jenis");
                o.stok = rs.getInt("stok");
                o.harga = rs.getDouble("harga");
                list.add(o);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error cari data: " + e.getMessage());
        }
        return list;
    }

    // -----------------------------
    // Tambah data obat
    // -----------------------------
    public static void tambah(String nama, String jenis, int stok, double harga) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "INSERT INTO obat (nama_obat, jenis, stok, harga) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, jenis);
            pst.setInt(3, stok);
            pst.setDouble(4, harga);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data obat berhasil ditambahkan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error tambah obat: " + e.getMessage());
        }
    }

    // -----------------------------
    // Ubah data obat
    // -----------------------------
    public static void ubah(int id, String nama, String jenis, int stok, double harga) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "UPDATE obat SET nama_obat=?, jenis=?, stok=?, harga=? WHERE id_obat=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, jenis);
            pst.setInt(3, stok);
            pst.setDouble(4, harga);
            pst.setInt(5, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data obat berhasil diubah!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error ubah obat: " + e.getMessage());
        }
    }

    // -----------------------------
    // Hapus data obat
    // -----------------------------
    public static void hapus(int id) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "DELETE FROM obat WHERE id_obat=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data obat berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error hapus obat: " + e.getMessage());
        }
    }
}
