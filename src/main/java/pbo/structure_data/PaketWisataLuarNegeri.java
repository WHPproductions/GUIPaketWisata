package pbo.structure_data;

public class PaketWisataLuarNegeri extends PaketWisata{
    private String negara;
    private String tourGuide;
    private double biayaVisa;

    public String getNegara() {
        return negara;
    }
    public void setNegara(String negara) {
        this.negara = negara;
    }
    public String getTourGuide() {
        return tourGuide;
    }
    public void setTourGuide(String tourGuide) {
        this.tourGuide = tourGuide;
    }
    public double getBiayaVisa() {
        return biayaVisa;
    }
    public void setBiayaVisa(double biayaVisa) {
        this.biayaVisa = biayaVisa;
    }

    public PaketWisataLuarNegeri(){

    }

    public PaketWisataLuarNegeri(
            int id,
            String namaPaket,
            String destinasi,
            double harga,
            int durasi,
            String negara,
            String tourGuide,
            double biayaVisa
    ){
        super(id, namaPaket, destinasi, harga, durasi);
        this.negara = negara;
        this.tourGuide = tourGuide;
        this.biayaVisa = biayaVisa;
    }

    @Override
    public PaketWisata updatePaketWisata(PaketWisataDalamNegeri paket) {
        return null;
    }

    @Override
    public PaketWisata updatePaketWisata(PaketWisataLuarNegeri paket) {
        setId(paket.getId());
        setNamaPaket(paket.getNamaPaket());
        setDestinasi(paket.getDestinasi());
        setHarga(paket.getHarga());
        setDurasi(paket.getDurasi());
        setNegara(paket.getNegara());
        setTourGuide(paket.getTourGuide());
        setBiayaVisa(paket.getBiayaVisa());
        return this;
    }

    @Override
    public double hitungHarga() {
        return getHarga() + getBiayaVisa();
    }
}
