package pbo;

import pbo.structure_data.Pelanggan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UpdateUser extends BasePage {
    private JPanel panel;
    private JTextField inputId;
    private JTextField inputNama;
    private JTextField inputNoTelp;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel noTelpLabel;
    private JButton updateButton;
    private JButton cancelButton;

    public UpdateUser(ArrayList<Pelanggan> pelanggans, Pelanggan selectedPelanggan) {
        super(pelanggans, selectedPelanggan);
        customizeComponents();
        setupEventHandlers();
        inputId.setText(selectedPelanggan.getId().toString());
        inputNama.setText(selectedPelanggan.getNama());
        inputNoTelp.setText(selectedPelanggan.getNoTelp());
    }

    // Event handler
    private void goCancelToDashboard(){
        DashBoard dashBoard = new DashBoard(getPelanggans(), getSelectedPelanggan());
        changeWindow(dashBoard);
    }

    @Override
    protected JPanel getMainPanel() {
        return panel;
    }

    @Override
    protected void customizeComponents() {
        // Style panel
        panel.setBackground(UIConstantStyles.WHITE);

        // Style labels
        styleSecondaryLabelBold(idLabel);
        styleSecondaryLabelBold(namaLabel);
        styleSecondaryLabelBold(noTelpLabel);

        // Style text fields
        styleTextField(inputId);
        styleTextField(inputNama);
        styleTextField(inputNoTelp);

        // Style buttons
        styleButton(updateButton, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);
        styleButton(cancelButton, UIConstantStyles.DARK_RED, UIConstantStyles.WHITE);
    }

    @Override
    protected void setupEventHandlers() {
        cancelButton.addActionListener(e -> goCancelToDashboard());
    }

    @Override
    protected String getTitle() {
        return "UpdateUser";
    }

    @Override
    protected Dimension getWindowSize() {
        return new Dimension(800, 400);
    }
}
