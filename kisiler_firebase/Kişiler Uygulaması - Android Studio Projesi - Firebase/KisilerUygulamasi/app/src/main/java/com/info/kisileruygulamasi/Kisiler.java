package com.info.kisileruygulamasi;

/**
 * Created by kasimadalan on 4.05.2018.
 */

public class Kisiler {
    private String kisi_id;
    private String kisi_ad;
    private String kisi_tel;

    public Kisiler() {
    }

    public Kisiler(String kisi_id, String kisi_ad, String kisi_tel) {
        this.kisi_id = kisi_id;
        this.kisi_ad = kisi_ad;
        this.kisi_tel = kisi_tel;
    }

    public String getKisi_id() {
        return kisi_id;
    }

    public void setKisi_id(String kisi_id) {
        this.kisi_id = kisi_id;
    }

    public String getKisi_ad() {
        return kisi_ad;
    }

    public void setKisi_ad(String kisi_ad) {
        this.kisi_ad = kisi_ad;
    }

    public String getKisi_tel() {
        return kisi_tel;
    }

    public void setKisi_tel(String kisi_tel) {
        this.kisi_tel = kisi_tel;
    }
}
