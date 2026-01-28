package pbo;

import pbo.structure_data.*;

import javax.swing.*;
import java.io.IOException;

/*
    # UTB 2025 tugas UAS siakad semester 3
        ## Informasi
            - Nama Program: Aplikasi sistem manajemen paket wisata
            - Mata Kuliah: Pemograman Berbasis Objek 1
            - Tahun: 2025-2026
            - Semester: 3

        ## Kelompok 1
            Kelas: TIF RP 24 D
            - Winata Hadi Pratama (24552011280)
            - Alifa Halim Rasyidin (24552011300)
            - Sani Rahmatani (242011021)
            - Shakira Fazilatunnisa (24552011001)

        ## Dosen Pengampu
            Gia Yuliana, S.Kom.
*/

public class Main {
    public static void main(String[] args) {
        Memory memory = Memory.getInstance();
        // Dummies Initialization, put comment if not used
//        memory.setPelanggans(TestData.TEST_PELANGGANS);
//        memory.updateDatabase();
        SwingUtilities.invokeLater(() -> {
            new LoginSignin().show();
        });
    }
}
