package pbo;

import pbo.structure_data.*;

import javax.swing.*;
import java.io.IOException;

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
