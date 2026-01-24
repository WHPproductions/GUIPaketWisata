package pbo.structure_data;

public class PaketWisataDalamNegeri extends PaketWisata{
    private String daerah;
    private String kategori;
    private double diskon;

    public String getDaerah() {
        return daerah;
    }
    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }
    public String getKategori() {
        return kategori;
    }
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public double getDiskon() {
        return diskon;
    }
    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    public PaketWisataDalamNegeri(
            int id,
            String namaPaket,
            String destinasi,
            double harga,
            int durasi,
            String daerah,
            String kategori,
            double diskon
    ){
        super(id, namaPaket, destinasi, harga, durasi);
        this.daerah = daerah;
        this.kategori = kategori;
        this.diskon = diskon;
    }

    @Override
    public double hitungHarga() {
        return getHarga() - (getHarga() * diskon);
    }
}
