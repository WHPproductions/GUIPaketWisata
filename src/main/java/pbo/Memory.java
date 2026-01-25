package pbo;

import pbo.structure_data.PaketWisata;
import pbo.structure_data.PaketWisataDalamNegeri;
import pbo.structure_data.PaketWisataLuarNegeri;
import pbo.structure_data.Pelanggan;

import java.io.IOException;
import java.util.ArrayList;

public class Memory {
    private static Memory instance;
    private ArrayList<Pelanggan> pelanggans = new ArrayList<>();
    private Pelanggan selectedPelanggan;
    private PaketWisata selectedPaketWisata;

    private Memory() throws IOException {
        pelanggans = (ArrayList<Pelanggan>) JSONFileManager.readFromJSON(Pelanggan.class);
    }

    public static Memory getInstance() {
        if (instance == null) {
            try {
                instance = new Memory();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public ArrayList<Pelanggan> getPelanggans() {
        return pelanggans;
    }
    public void setPelanggans(ArrayList<Pelanggan> pelanggans) {
        this.pelanggans = pelanggans;
    }
    public Pelanggan getSelectedPelanggan() {
        return selectedPelanggan;
    }
    public void setSelectedPelanggan(Pelanggan pelanggan) {
        selectedPelanggan = pelanggan;
    }
    public PaketWisata getSelectedPaketWisata() {
        return selectedPaketWisata;
    }
    public void setSelectedPaketWisata(PaketWisata paketWisata) {
        selectedPaketWisata = paketWisata;
    }

    // CRUD Pelanggan
    public void addPelanggan(Pelanggan pelanggan) {
        pelanggans.add(pelanggan);
        updateDatabase();
    }
    public Pelanggan getPelangganById(int id) {
        for (Pelanggan pelanggan : pelanggans) {
            if (pelanggan.getId() == id) {
                return pelanggan;
            }
        }
        return null;
    }
    public Pelanggan updatePelanggan(Pelanggan newPelanggan) throws Error {
        Pelanggan oldPelanggan = getSelectedPelanggan();
        if (oldPelanggan == null) {
            throw new Error("No pelanggan selected");
        }
        for (Pelanggan pelanggan : pelanggans) {
            if (pelanggan == oldPelanggan) {
                continue;
            }
            if (pelanggan.getId().equals(newPelanggan.getId())) throw new Error("Pelanggan ID already exists");
        }
        oldPelanggan.setId(newPelanggan.getId());
        oldPelanggan.setNama(newPelanggan.getNama());
        oldPelanggan.setPassword(newPelanggan.getPassword());
        oldPelanggan.setNoTelp(newPelanggan.getNoTelp());
        oldPelanggan.setDaftarPaketDalamNegeri(newPelanggan.getDaftarPaketDalamNegeri());
        oldPelanggan.setDaftarPaketLuarNegeri(newPelanggan.getDaftarPaketLuarNegeri());
        updateDatabase();
        return oldPelanggan;
    }
    public boolean removePelanggan(Pelanggan pelanggan) {
        boolean succeed = pelanggans.remove(pelanggan);
        updateDatabase();
        return succeed;
    }

    // CRUD Paket Wisata
    public void addPaketWisataDalamNegeri(PaketWisataDalamNegeri paket) {
        selectedPelanggan.addPaketDalamNegeri(paket);
        updateDatabase();
    }
    public void addPaketWisataLuarNegeri(PaketWisataLuarNegeri paket) {
        selectedPelanggan.addPaketLuarNegeri(paket);
        updateDatabase();
    }
    public PaketWisataDalamNegeri findPaketWisataDalamNegeriByID(int id) {
        return selectedPelanggan.getPaketDalamNegeriByID(id);
    }
    public PaketWisataLuarNegeri findPaketWisataLuarNegeriByID(int id) {
        return selectedPelanggan.getPaketLuarNegeriByID(id);
    }
    public boolean updatePaketWisataDalamNegeri(PaketWisataDalamNegeri newPaket) throws Error {
        boolean succeed = selectedPelanggan.updatePaketDalamNegeri(newPaket);
        updateDatabase();
        return succeed;
    }
    public boolean updatePaketWisataLuarNegeri(PaketWisataLuarNegeri newPaket) throws Error {
        boolean succeed = selectedPelanggan.updatePaketLuarNegeri(newPaket);
        updateDatabase();
        return succeed;
    }
    public boolean removePaketWisataDalamNegeri(PaketWisataDalamNegeri paket) {
        boolean succeed = selectedPelanggan.removePaketDalamNegeri(paket);
        updateDatabase();
        return succeed;
    }
    public boolean removePaketWisataLuarNegeri(PaketWisataLuarNegeri paket) {
        boolean succeed = selectedPelanggan.removePaketLuarNegeri(paket);
        updateDatabase();
        return succeed;
    }

    // Database Management
    public void updateDatabase(){
        try {
            JSONFileManager.writeToJSON(pelanggans);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
