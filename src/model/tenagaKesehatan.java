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
public class tenagaKesehatan {
    private int id_tenaga;
    private String nama;
    private String profesi;
    private String jenis_kelamin;
    private int usia;
    private String alamat;

    // Getter
    public int getId_tenaga() { return id_tenaga; }
    public String getNama() { return nama; }
    public String getProfesi() { return profesi; }
    public String getJenis_kelamin() { return jenis_kelamin; }
    public int getUsia() { return usia; }
    public String getAlamat() { return alamat; }

    // -----------------------------
    // Ambil semua data tenaga kesehatan
    // -----------------------------
    public static ArrayList<tenagaKesehatan> getAll() {
        ArrayList<tenagaKesehatan> list = new ArrayList<>();
        try {
            Connection conn = koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tenaga_kesehatan");

            while (rs.next()) {
                tenagaKesehatan t = new tenagaKesehatan();
                t.id_tenaga = rs.getInt("id_tenaga");
                t.nama = rs.getString("nama");
                t.profesi = rs.getString("profesi");
                t.jenis_kelamin = rs.getString("jenis_kelamin");
                t.usia = rs.getInt("usia");
                t.alamat = rs.getString("alamat");
                list.add(t);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error tampil data: " + e.getMessage());
        }
        return list;
    }

    // -----------------------------
    // Tambah data tenaga kesehatan
    // -----------------------------
    public static void tambah(String nama, String profesi, String jk, int usia, String alamat) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "INSERT INTO tenaga_kesehatan (nama, profesi, jenis_kelamin, usia, alamat) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, profesi);
            pst.setString(3, jk);
            pst.setInt(4, usia);
            pst.setString(5, alamat);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error tambah: " + e.getMessage());
        }
    }

    // -----------------------------
    // Ubah data tenaga kesehatan
    // -----------------------------
    public static void ubah(int id, String nama, String profesi, String jk, int usia, String alamat) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "UPDATE tenaga_kesehatan SET nama=?, profesi=?, jenis_kelamin=?, usia=?, alamat=? WHERE id_tenaga=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, profesi);
            pst.setString(3, jk);
            pst.setInt(4, usia);
            pst.setString(5, alamat);
            pst.setInt(6, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error ubah: " + e.getMessage());
        }
    }

    // -----------------------------
    // Hapus data tenaga kesehatan
    // -----------------------------
    public static void hapus(int id) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "DELETE FROM tenaga_kesehatan WHERE id_tenaga=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error hapus: " + e.getMessage());
        }
    }
}
