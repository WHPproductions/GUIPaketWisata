package pbo.structure_data;

public abstract class PaketWisata {
    private int id;
    private String namaPaket;
    private String destinasi;
    private double harga;
    private int durasi;

    public PaketWisata(int id, String namaPaket, String destinasi, double harga, int durasi) {
        this.id = id;
        this.namaPaket = namaPaket;
        this.destinasi = destinasi;
        this.harga = harga;
        this.durasi = durasi;
    }

    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }
    public String getNamaPaket() {
        return namaPaket;
    }
    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }
    public String getDestinasi() {
        return destinasi;
    }
    public void setDestinasi(String destinasi) {
        this.destinasi = destinasi;
    }
    public double getHarga() { return harga; }
    public void setHarga(double harga) {
        this.harga = harga;
    }
    public int getDurasi() {
        return durasi;
    }
    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public PaketWisata updatePaketWisata(
            int id,
            String namaPaket,
            String destinasi,
            double harga,
            int durasi) {
        this.id = id;
        this.namaPaket = namaPaket;
        this.destinasi = destinasi;
        this.harga = harga;
        this.durasi = durasi;
        return this;
    }

    public abstract double hitungHarga();
}
