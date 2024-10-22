/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import KaryaSeni.KaryaSeni;
import KaryaSeni.KaryaSeniGif;
import KaryaSeni.KaryaSeniIlustrasi;

import java.util.ArrayList;
/**
 *
 * @author maidi
 */
public class User {
    private String nama;
    private String email;
    private final String userID;
    private final String userPW;
    public static ArrayList<KaryaSeni> koleksi = new ArrayList<>();

    public User(String nama, String email, String userID, String userPW) {
        this.nama = nama;
        this.email = email;
        this.userID = userID;
        this.userPW = userPW;
    }

    public void createKaryaSeni(String judul, String artis, String tahun, String teknik, String durasi, String gambarPath, boolean isGif) {
        KaryaSeni karyaBaru;
        if (isGif) {
            karyaBaru = new KaryaSeniGif(judul, artis, tahun, durasi, gambarPath);
        } else {
            karyaBaru = new KaryaSeniIlustrasi(judul, artis, tahun, teknik, gambarPath);
        }
        koleksi.add(karyaBaru);
    }
    
    public void createKaryaSeni(KaryaSeni karya) {
        koleksi.add(karya);
    }
    
    public ArrayList<KaryaSeni> getKoleksi() {
        return koleksi;
    }

    public String getNama() {
        return nama;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPW() {
        return userPW;
    }
}

