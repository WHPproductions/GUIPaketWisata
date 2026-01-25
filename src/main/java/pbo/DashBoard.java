package pbo;

import pbo.structure_data.Pelanggan;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DashBoard extends BasePage {
    private JPanel panel;
    private JButton logoutButton;
    private JButton deleteAccountButton;
    private JButton updateButton;
    private JButton pktWisataDlm;
    private JButton pktWisataLuar;
    private JLabel contentId;
    private JLabel contentNama;
    private JLabel contentNoTelp;
    private JLabel labelId;
    private JLabel labelNama;
    private JLabel labelNoTelp;

    public DashBoard() {
        customizeComponents();
        setupEventHandlers();
    }

    @Override
    protected JPanel getMainPanel() {
        return panel;
    }

    @Override
    protected void customizeComponents() {
        // Set background colors
        applyPanelColor(panel, UIConstantStyles.LIGHT_GRAY);

        // Set button colors
        applyButtonColor(logoutButton, UIConstantStyles.DARK_RED, UIConstantStyles.WHITE);
        applyButtonColor(deleteAccountButton, UIConstantStyles.DARK_RED, UIConstantStyles.WHITE);
        applyButtonColor(updateButton, UIConstantStyles.LIGHT_BLUE, UIConstantStyles.WHITE);
        applyButtonColor(pktWisataDlm, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);
        applyButtonColor(pktWisataLuar, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);

        // Set label colors
        stylePrimaryLabelBold(labelId);
        stylePrimaryLabelBold(labelNama);
        stylePrimaryLabelBold(labelNoTelp);
        stylePrimaryLabelDefault(contentId);
        stylePrimaryLabelDefault(contentNama);
        stylePrimaryLabelDefault(contentNoTelp);

        // Set labels
        Pelanggan pelanggan = Memory.getInstance().getSelectedPelanggan();
        contentId.setText(pelanggan.getId().toString());
        contentNama.setText(pelanggan.getNama());
        contentNoTelp.setText(pelanggan.getNoTelp());
    }

    private void goToUpdateUser(){
        UpdateUser updateUser = new UpdateUser();
        changeWindow(updateUser);
    }
    private void goLogout(){
        Memory.getInstance().setSelectedPelanggan(null);

        LoginSignin loginSignin = new LoginSignin();
        changeWindow(loginSignin);
    }
    private void goDeleteAccount() {
        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(
                panel,
                "Are you sure you want to delete your account? This action cannot be undone.",
                "Confirm Account Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }


        // Delete Pelanggan
        Pelanggan currentPelanggan = Memory.getInstance().getSelectedPelanggan();
        boolean deleted = Memory.getInstance().removePelanggan(currentPelanggan);

        if (deleted) {
            JOptionPane.showMessageDialog(
                    panel,
                    "Account deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Navigate back to login panel
            LoginSignin loginSignin = new LoginSignin();
            Memory.getInstance().setSelectedPelanggan(null);
            changeWindow(loginSignin);
        } else { // Failed to delete
            JOptionPane.showMessageDialog(
                    panel,
                    "Failed to delete account. Account not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    private void goToListPaketWisataDalamNegeri(){
        ListPaketWisata listPaketWisata = new ListPaketWisata(true);
        changeWindow(listPaketWisata);
    }
    private void goToListPaketWisataLuarNegeri(){
        ListPaketWisata listPaketWisata = new ListPaketWisata(false);
        changeWindow(listPaketWisata);
    }

    @Override
    protected void setupEventHandlers() {
        logoutButton.addActionListener(e -> goLogout());
        deleteAccountButton.addActionListener(e -> goDeleteAccount());
        updateButton.addActionListener(e -> goToUpdateUser());
        pktWisataDlm.addActionListener(e -> goToListPaketWisataDalamNegeri());
        pktWisataLuar.addActionListener(e -> goToListPaketWisataLuarNegeri());
    }

    @Override
    protected String getTitle() {
        return "DashBoard";
    }

    @Override
    protected Dimension getWindowSize() {
        return new Dimension(700, 200);
    }
}

