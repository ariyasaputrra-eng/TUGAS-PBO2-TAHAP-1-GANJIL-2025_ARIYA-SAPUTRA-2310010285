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
 *
 * @author ACER
 */
public class pasien {
    private int id_pasien;
    private String nama;
    private String ttl;
    private String jenis_kelamin;
    private String alamat;

    // Getter
    public int getId_pasien() { return id_pasien; }
    public String getNama() { return nama; }
    public String getTtl() { return ttl; }
    public String getJenis_kelamin() { return jenis_kelamin; }
    public String getAlamat() { return alamat; }

    // -----------------------------
    // Ambil semua data pasien
    // -----------------------------
    public static ArrayList<pasien> getAll() {
        ArrayList<pasien> list = new ArrayList<>();
        try {
            Connection conn = koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pasien");

            while (rs.next()) {
                pasien p = new pasien();
                p.id_pasien = rs.getInt("id_pasien");
                p.nama = rs.getString("nama");
                p.ttl = rs.getString("ttl");
                p.jenis_kelamin = rs.getString("jenis_kelamin");
                p.alamat = rs.getString("alamat");
                list.add(p);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error tampil data: " + e.getMessage());
        }
        return list;
    }

    // -----------------------------
    // Tambah data pasien
    // -----------------------------
    public static void tambah(String nama, String ttl, String jk, String alamat) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "INSERT INTO pasien (nama, ttl, jenis_kelamin, alamat) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, ttl);
            pst.setString(3, jk);
            pst.setString(4, alamat);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error tambah: " + e.getMessage());
        }
    }

    // -----------------------------
    // Ubah data pasien
    // -----------------------------
    public static void ubah(int id, String nama, String ttl, String jk, String alamat) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "UPDATE pasien SET nama=?, ttl=?, jenis_kelamin=?, alamat=? WHERE id_pasien=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, ttl);
            pst.setString(3, jk);
            pst.setString(4, alamat);
            pst.setInt(5, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error ubah: " + e.getMessage());
        }
    }

    // -----------------------------
    // Hapus data pasien
    // -----------------------------
    public static void hapus(int id) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "DELETE FROM pasien WHERE id_pasien=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error hapus: " + e.getMessage());
        }
    }
}

