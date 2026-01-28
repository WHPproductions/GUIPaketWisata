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

    public PaketWisataDalamNegeri(){

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
    public PaketWisata updatePaketWisata(PaketWisataLuarNegeri paket) {
        return null;
    }

    @Override
    public PaketWisataDalamNegeri updatePaketWisata(PaketWisataDalamNegeri paket) {
            setId(paket.getId());
            setNamaPaket(paket.getNamaPaket());
            setDestinasi(paket.getDestinasi());
            setHarga(paket.getHarga());
            setDurasi(paket.getDurasi());
            setDaerah(paket.getDaerah());
            setKategori(paket.getKategori());
            setDiskon(paket.getDiskon());
            return this;
    }
}
