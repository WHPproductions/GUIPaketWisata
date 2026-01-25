package pbo;

import pbo.structure_data.Pelanggan;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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

    public UpdateUser() {
        customizeComponents();
        setupEventHandlers();
    }

    // Event handler
    private void goCancelToDashboard(){
        DashBoard dashBoard = new DashBoard();
        changeWindow(dashBoard);
    }
    private void goUpdateUser(){
        // Validate inputs
        if (inputId.getText().isEmpty() || inputNama.getText().isEmpty() || inputNoTelp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panel,
                    "Please fill in all fields",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int newId;
        String newNama;
        String newNoTelp;
        try {
            newId = Integer.parseInt(inputId.getText());
            newNama = inputNama.getText();
            newNoTelp = inputNoTelp.getText();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel,
                    "ID must be a valid number",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pelanggan selectedPelanggan = Memory.getInstance().getSelectedPelanggan();
        Pelanggan newSelectedPelanggan = new Pelanggan(newId,  selectedPelanggan.getPassword(),newNama, newNoTelp, selectedPelanggan.getDaftarPaketDalamNegeri(), selectedPelanggan.getDaftarPaketLuarNegeri());

        // Update Pelanggan in memory and database
        try {
            Memory.getInstance().updatePelanggan(newSelectedPelanggan);
        } catch (Error e) {
            if (e.getMessage().equals("No pelanggan selected")) {
                JOptionPane.showMessageDialog(panel,
                        "No pelanggan selected",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (e.getMessage().equals("Pelanggan ID already exists")) {
                JOptionPane.showMessageDialog(panel,
                        "Pelanggan ID already exists",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        JOptionPane.showMessageDialog(panel,
                "User updated successfully",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        goCancelToDashboard();
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
        Pelanggan selectedPelanggan = Memory.getInstance().getSelectedPelanggan();
        inputId.setText(selectedPelanggan.getId().toString());
        inputNama.setText(selectedPelanggan.getNama());
        inputNoTelp.setText(selectedPelanggan.getNoTelp());
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
        updateButton.addActionListener(e -> goUpdateUser());
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
