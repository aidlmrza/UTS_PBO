/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaryaSeni;

/**
 *
 * @author maidi
 */
public class KaryaSeniGif extends KaryaSeni {
    private String durasi;

    public KaryaSeniGif(String judul, String artis, String tahun, String durasi, String gambarPath) {
        super(judul, artis, tahun, gambarPath);
        this.durasi = durasi;
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Durasi GIF: " + durasi);
        System.out.println("Path Gambar: " + getGambarPath());
    }

    public String getDurasi() {
        return durasi;
    }
}

