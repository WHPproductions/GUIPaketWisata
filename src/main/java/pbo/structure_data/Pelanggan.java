package pbo.structure_data;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

public class Pelanggan {
    private Integer id;
    private String password;
    private String nama;
    private String noTelp;
    private ArrayList<PaketWisata> daftarPaket;

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
    public ArrayList<PaketWisata> getDaftarPaket() {
        return daftarPaket;
    }
    public void setDaftarPaket(ArrayList<PaketWisata> daftarPaket) {
        this.daftarPaket = daftarPaket;
    }

    public Pelanggan() {
        this(100, "Isi Password");
    }
    public Pelanggan(Integer id, String password) {
        this.id = id;
        this.password = password;
        this.nama = "Isi Nama";
        this.noTelp = "Isi Nomor Telepon";
        this.daftarPaket = new ArrayList<>();
    }
    public Pelanggan(Integer id, String password, String nama, String noTelp, ArrayList<PaketWisata> daftarPaket) {
        this.id = id;
        this.password = password;
        this.nama = nama;
        this.noTelp = noTelp;
        this.daftarPaket = daftarPaket;
    }

    public void addPaketWisata(PaketWisata paket) {
        daftarPaket.add(paket);
    }
    public void removePaketWisata(PaketWisata paket) {
        daftarPaket.remove(paket);
    }
    public PaketWisata getPaketWisataByID(int id) {
        for (PaketWisata paket : daftarPaket) {
            if (paket.getId() == id) {
                return paket;
            }
        }
        return null;
    }

    // Methods
    @JsonIgnore
    public String[] getColumnNames() {
        return new String[] {"ID", "Nama Paket", "Destinasi", "Harga", "Durasi"};
    }
}
