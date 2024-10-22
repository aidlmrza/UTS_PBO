/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package KaryaSeni;

/**
 *
 * @author maidi
 */

abstract public class KaryaSeni {
    private String judul;
    private String artis;
    private String tahun;
    private String gambarPath;

    KaryaSeni(String judul, String artis, String tahun, String gambarPath) {
        this.judul = judul;
        this.artis = artis;
        this.tahun = tahun;
        this.gambarPath = gambarPath;
    }

    abstract public void tampilkanDetail();

    public String getJudul() {
        return judul;
    }

    public String getArtis() {
        return artis;
    }

    public String getTahun() {
        return tahun;
    }

    public String getGambarPath() {
        return gambarPath;
    }
}

