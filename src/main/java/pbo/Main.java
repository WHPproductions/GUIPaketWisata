package pbo;

import pbo.structure_data.TestData;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginSignin(TestData.TEST_PELANGGANS, TestData.PELANGGAN0).show();
        });
    }
}
