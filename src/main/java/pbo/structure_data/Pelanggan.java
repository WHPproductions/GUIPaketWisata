package pbo.structure_data;

import com.fasterxml.jackson.annotation.*;
import pbo.Memory;

import java.util.*;

public class Pelanggan {
    private Integer id;
    private String password;
    private String nama;
    private String noTelp;
    private ArrayList<PaketWisataDalamNegeri> daftarPaketDalamNegeri;
    private ArrayList<PaketWisataLuarNegeri> daftarPaketLuarNegeri;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNoTelp() {
        return noTelp;
    }
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    public ArrayList<PaketWisataDalamNegeri> getDaftarPaketDalamNegeri() {
        return daftarPaketDalamNegeri;
    }
    public void setDaftarPaketDalamNegeri(ArrayList<PaketWisataDalamNegeri> daftarPaketDalamNegeri) {
        this.daftarPaketDalamNegeri = daftarPaketDalamNegeri;
    }
    public ArrayList<PaketWisataLuarNegeri> getDaftarPaketLuarNegeri() {
        return daftarPaketLuarNegeri;
    }
    public void setDaftarPaketLuarNegeri(ArrayList<PaketWisataLuarNegeri> daftarPaketLuarNegeri) {
        this.daftarPaketLuarNegeri = daftarPaketLuarNegeri;
    }

    public Pelanggan() {
        this(100, "Isi Password");
    }
    public Pelanggan(Integer id) {
        this.id = id;
        this.password = "Password";
        this.nama = "Isi Nama";
        this.noTelp = "Isi Nomor Telepon";
        this.daftarPaketDalamNegeri = new ArrayList<>();
        this.daftarPaketLuarNegeri = new ArrayList<>();
    }
    public Pelanggan(Integer id, String password) {
        this.id = id;
        this.password = password;
        this.nama = "Isi Nama";
        this.noTelp = "Isi Nomor Telepon";
        this.daftarPaketDalamNegeri = new ArrayList<>();
        this.daftarPaketLuarNegeri = new ArrayList<>();
    }
    public Pelanggan(Integer id, String password, String nama, String noTelp, ArrayList<PaketWisataDalamNegeri> daftarPaketDalamNegeri, ArrayList<PaketWisataLuarNegeri> daftarPaketLuarNegeri) {
        this.id = id;
        this.password = password;
        this.nama = nama;
        this.noTelp = noTelp;
        this.daftarPaketDalamNegeri = daftarPaketDalamNegeri;
        this.daftarPaketLuarNegeri = daftarPaketLuarNegeri;
    }

    // CRUD Paket Wisata
    public void addPaketDalamNegeri(PaketWisataDalamNegeri paket) {
        daftarPaketDalamNegeri.add(paket);
    }
    public void addPaketLuarNegeri(PaketWisataLuarNegeri paket) {
        daftarPaketLuarNegeri.add(paket);
    }
    public PaketWisataDalamNegeri getPaketDalamNegeriByID(int id) {
        for (PaketWisataDalamNegeri paket : daftarPaketDalamNegeri) {
            if (paket.getId() == id) {
                return paket;
            }
        }
        return null;
    }
    public PaketWisataLuarNegeri getPaketLuarNegeriByID(int id) {
        for (PaketWisataLuarNegeri paket : daftarPaketLuarNegeri) {
            if (paket.getId() == id) {
                return paket;
            }
        }
        return null;
    }
    public boolean updatePaketDalamNegeri(PaketWisataDalamNegeri newPaket) throws Error{
        PaketWisataDalamNegeri oldPaketWisataDalamNegeri = (PaketWisataDalamNegeri) Memory.getInstance().getSelectedPaketWisata();

        if (oldPaketWisataDalamNegeri == null) {
            throw new Error("No paket selected");
        }
        // See if there are duplicates
        for (PaketWisataDalamNegeri paket : getDaftarPaketDalamNegeri()) {
            if (paket.equals(oldPaketWisataDalamNegeri)) {
                continue;
            }
            if (paket.getId() == newPaket.getId()) throw new Error("Paket ID already exists");
        }

        // Update paket details in memory
        oldPaketWisataDalamNegeri.setId(newPaket.getId());
        oldPaketWisataDalamNegeri.setNamaPaket(newPaket.getNamaPaket());
        oldPaketWisataDalamNegeri.setDestinasi(newPaket.getDestinasi());
        oldPaketWisataDalamNegeri.setHarga(newPaket.getHarga());
        oldPaketWisataDalamNegeri.setDurasi(newPaket.getDurasi());
        oldPaketWisataDalamNegeri.setDaerah(newPaket.getDaerah());
        oldPaketWisataDalamNegeri.setKategori(newPaket.getKategori());
        oldPaketWisataDalamNegeri.setDiskon(newPaket.getDiskon());

        return true;
    }
    public boolean updatePaketLuarNegeri(PaketWisataLuarNegeri newPaket) throws Error{
        PaketWisataLuarNegeri oldPaketWisataLuarNegeri = (PaketWisataLuarNegeri) Memory.getInstance().getSelectedPaketWisata();

        if (oldPaketWisataLuarNegeri == null) {
            throw new Error("No paket selected");
        }
        // See if there are duplicates
        for (PaketWisataLuarNegeri paket : getDaftarPaketLuarNegeri()) {
            if (paket == oldPaketWisataLuarNegeri) {
                continue;
            }
            if (paket.getId() == newPaket.getId()) throw new Error("Paket ID already exists");
        }

        // Update paket details in memory
        oldPaketWisataLuarNegeri.setId(newPaket.getId());
        oldPaketWisataLuarNegeri.setNamaPaket(newPaket.getNamaPaket());
        oldPaketWisataLuarNegeri.setDestinasi(newPaket.getDestinasi());
        oldPaketWisataLuarNegeri.setHarga(newPaket.getHarga());
        oldPaketWisataLuarNegeri.setDurasi(newPaket.getDurasi());
        oldPaketWisataLuarNegeri.setNegara(newPaket.getNegara());
        oldPaketWisataLuarNegeri.setTourGuide(newPaket.getTourGuide());
        oldPaketWisataLuarNegeri.setBiayaVisa(newPaket.getBiayaVisa());

        return true;
    }
    public boolean removePaketDalamNegeri(PaketWisataDalamNegeri paket) {
        return daftarPaketDalamNegeri.remove(paket);
    }
    public boolean removePaketLuarNegeri(PaketWisataLuarNegeri paket) {
        return daftarPaketLuarNegeri.remove(paket);
    }

    // Methods
    @JsonIgnore
    public String[] getColumnNames() {
        return new String[] {"ID", "Nama Paket", "Destinasi", "Harga", "Durasi"};
    }
}
