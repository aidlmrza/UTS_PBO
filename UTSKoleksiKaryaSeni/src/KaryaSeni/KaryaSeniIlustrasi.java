/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaryaSeni;

/**
 *
 * @author maidi
 */
public class KaryaSeniIlustrasi extends KaryaSeni {
    private String teknik;

    public KaryaSeniIlustrasi(String judul, String artis, String tahun, String teknik, String gambarPath) {
        super(judul, artis, tahun, gambarPath);
        this.teknik = teknik;
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Teknik Ilustrasi: " + teknik);
        System.out.println("Path Gambar: " + getGambarPath());
    }

    public String getTeknik() {
        return teknik;
    }
}

