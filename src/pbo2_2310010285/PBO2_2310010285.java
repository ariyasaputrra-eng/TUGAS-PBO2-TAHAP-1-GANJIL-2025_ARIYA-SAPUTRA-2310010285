/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pbo2_2310010285;
import frame.FramePasien;
import frame.FrameTenagaKesehatan;
import frame.FrameObat;
import frame.FramePeriksa;
import javax.swing.*;
/**
 *
 * @author ACER
 */
public class PBO2_2310010285 {
public static void main(String[] args) {
        // Gunakan tampilan modern (optional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Gagal mengatur tampilan: " + e.getMessage());
        }

        // Tampilkan Menu Utama untuk memilih Frame
        SwingUtilities.invokeLater(() -> {
            JFrame menu = new JFrame("Sistem Informasi Klinik - PBO2_2310010285");
            menu.setSize(400, 300);
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setLayout(new java.awt.GridLayout(5, 1, 10, 10));

            JButton btnPasien = new JButton("Data Pasien");
            JButton btnTenaga = new JButton("Data Tenaga Kesehatan");
            JButton btnObat = new JButton("Data Obat");
            JButton btnPeriksa = new JButton("Data Pemeriksaan");
            JButton btnKeluar = new JButton("Keluar");

            // Aksi tombol
            btnPasien.addActionListener(e -> new FramePasien().setVisible(true));
            btnTenaga.addActionListener(e -> new FrameTenagaKesehatan().setVisible(true));
            btnObat.addActionListener(e -> new FrameObat().setVisible(true));
            btnPeriksa.addActionListener(e -> new FramePeriksa().setVisible(true));
            btnKeluar.addActionListener(e -> System.exit(0));

            // Tambahkan ke frame menu
            menu.add(btnPasien);
            menu.add(btnTenaga);
            menu.add(btnObat);
            menu.add(btnPeriksa);
            menu.add(btnKeluar);

            // Tampilkan
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        });
    }
}
