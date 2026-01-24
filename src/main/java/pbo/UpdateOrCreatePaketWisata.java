package pbo;

import pbo.structure_data.PaketWisataDalamNegeri;
import pbo.structure_data.Pelanggan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class UpdateOrCreatePaketWisata extends BasePage {
    private JPanel panel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8;
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    private boolean isDalamNegeriMode;
    private boolean isNewPaketMode;

    public UpdateOrCreatePaketWisata(ArrayList<Pelanggan> pelanggans, Pelanggan selectedPelanggan, boolean isDalamNegeriMode, boolean isNewPaketMode, pbo.structure_data.PaketWisata selectedPaketWisata) {
        super(pelanggans, selectedPelanggan, selectedPaketWisata);
        this.isNewPaketMode = isNewPaketMode;
        this.isDalamNegeriMode = isDalamNegeriMode;
        customizeComponents();
        setupEventHandlers();
    }

    private void goSaveNewPaketWisataDalamNegeri(){
        PaketWisataDalamNegeri paketWisata = new PaketWisataDalamNegeri(

        );
    }
    private void goCancelToListPaketWisata(){
        ListPaketWisata listPaketWisata = new ListPaketWisata(getPelanggans(), getSelectedPelanggan(), isDalamNegeriMode);
        changeWindow(listPaketWisata);
    }

    @Override
    protected JPanel getMainPanel() {
        return panel;
    }

    @Override
    protected void customizeComponents() {
        // Set the panel background with padding
        applyPanelColor(panel, UIConstantStyles.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        for (Component component : panel.getComponents()) {
            component.setFont(UIConstantStyles.DEFAULT_FONT);
        }

        // Style labels
        for (Component component : panel.getComponents()) { //total of 8 labels
            if (component instanceof JLabel) {
                styleSecondaryLabelBold((JLabel) component);
            }
        }

        // Style text fields
        for (Component component : panel.getComponents()) { //total of 8 labels
            if (component instanceof JTextField) {
                styleTextField((JTextField) component);
            }
        }

        // Style buttons
        styleButton(saveButton, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);
        styleButton(cancelButton, UIConstantStyles.DARK_RED, UIConstantStyles.WHITE);
    }

    @Override
    protected void setupEventHandlers() {
        saveButton.addActionListener(e -> goSaveToPaketWisata());
        cancelButton.addActionListener(e -> goCancelToListPaketWisata());
    }

    @Override
    protected String getTitle() {
        return "Update / Create Paket Wisata";
    }

    @Override
    protected Dimension getWindowSize() {
        return new Dimension(600, 600);
    }

}
