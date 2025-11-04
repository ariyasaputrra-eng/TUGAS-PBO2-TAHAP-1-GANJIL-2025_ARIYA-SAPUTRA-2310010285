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
public class periksa {
   private int id_periksa;
    private int id_pasien;
    private int id_tenaga;
    private String tanggal;
    private String diagnosa;
    private String tindakan;

    // Getter
    public int getId_periksa() { return id_periksa; }
    public int getId_pasien() { return id_pasien; }
    public int getId_tenaga() { return id_tenaga; }
    public String getTanggal() { return tanggal; }
    public String getDiagnosa() { return diagnosa; }
    public String getTindakan() { return tindakan; }

    // -----------------------------
    // Ambil semua data periksa
    // -----------------------------
    public static ArrayList<periksa> getAll() {
        ArrayList<periksa> list = new ArrayList<>();
        try {
            Connection conn = koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM periksa");

            while (rs.next()) {
                periksa p = new periksa();
                p.id_periksa = rs.getInt("id_periksa");
                p.id_pasien = rs.getInt("id_pasien");
                p.id_tenaga = rs.getInt("id_tenaga");
                p.tanggal = rs.getString("tanggal");
                p.diagnosa = rs.getString("diagnosa");
                p.tindakan = rs.getString("tindakan");
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
    // Tambah data periksa
    // -----------------------------
    public static void tambah(int id_pasien, int id_tenaga, String tanggal, String diagnosa, String tindakan) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "INSERT INTO periksa (id_pasien, id_tenaga, tanggal, diagnosa, tindakan) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id_pasien);
            pst.setInt(2, id_tenaga);
            pst.setString(3, tanggal);
            pst.setString(4, diagnosa);
            pst.setString(5, tindakan);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error tambah: " + e.getMessage());
        }
    }

    // -----------------------------
    // Ubah data periksa
    // -----------------------------
    public static void ubah(int id, int id_pasien, int id_tenaga, String tanggal, String diagnosa, String tindakan) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "UPDATE periksa SET id_pasien=?, id_tenaga=?, tanggal=?, diagnosa=?, tindakan=? WHERE id_periksa=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id_pasien);
            pst.setInt(2, id_tenaga);
            pst.setString(3, tanggal);
            pst.setString(4, diagnosa);
            pst.setString(5, tindakan);
            pst.setInt(6, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error ubah: " + e.getMessage());
        }
    }

    // -----------------------------
    // Hapus data periksa
    // -----------------------------
    public static void hapus(int id) {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "DELETE FROM periksa WHERE id_periksa=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error hapus: " + e.getMessage());
        }
    }
}