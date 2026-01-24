package pbo.structure_data;

import java.util.ArrayList;

public final class TestData {
    // Test paket wisata data
    public static final PaketWisataDalamNegeri TEST_PAKET_WISATA0 = new PaketWisataDalamNegeri(1, "Mempelajari Budaya Mahmud", "Bandung", 500000.0, 1, "Mahmud", "Budaya", 10.0);
    public static final PaketWisataDalamNegeri TEST_PAKET_WISATA1 = new PaketWisataDalamNegeri(2, "Wisata Candi Borobudur", "Jogja", 2000000.0, 5, "Badrawati", "Budaya", 20.0);
    public static final PaketWisataLuarNegeri TEST_PAKET_WISATA2 = new PaketWisataLuarNegeri(3, "Fuji Hiking Trip", "Yamanashi", 5000000.0, 3, "Jepang", "Mendaki di gunung", 20.0);

    // Test list paket wisata
    public static final ArrayList<PaketWisata> TEST_LIST_PAKET_WISATA0;
    public static final ArrayList<PaketWisata> TEST_LIST_PAKET_WISATA1;

    static {
        TEST_LIST_PAKET_WISATA0 = new ArrayList<>();
        TEST_LIST_PAKET_WISATA0.add(TEST_PAKET_WISATA0);
        TEST_LIST_PAKET_WISATA0.add(TEST_PAKET_WISATA1);
        TEST_LIST_PAKET_WISATA0.add(TEST_PAKET_WISATA2);
        TEST_LIST_PAKET_WISATA1 = new ArrayList<>();
        TEST_LIST_PAKET_WISATA1.add(TEST_PAKET_WISATA0);
    }

    // Test pelanggan data
    public static final Pelanggan PELANGGAN0 = new Pelanggan(0, "password0", "Jessica", "089655306907", TEST_LIST_PAKET_WISATA0);
    public static final Pelanggan PELANGGAN1 = new Pelanggan(1, "password1", "Jassy", "089655306908", TEST_LIST_PAKET_WISATA1);

    // Test list pelanggans
    public static final ArrayList<Pelanggan> TEST_PELANGGANS;

    static {
        TEST_PELANGGANS = new ArrayList<>();
        TEST_PELANGGANS.add(PELANGGAN0);
        TEST_PELANGGANS.add(PELANGGAN1);
    }

}
