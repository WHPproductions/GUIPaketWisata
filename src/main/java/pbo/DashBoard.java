package pbo;

import pbo.structure_data.Pelanggan;

import javax.swing.*;
import java.awt.*;
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

    public DashBoard(ArrayList<Pelanggan> pelanggans, Pelanggan selectedPelanggan) {
        super(pelanggans, selectedPelanggan);
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
        contentId.setText(getSelectedPelanggan().getId().toString());
        contentNama.setText(getSelectedPelanggan().getNama());
        contentNoTelp.setText(getSelectedPelanggan().getNoTelp());
    }

    private void goToUpdateUser(){
        UpdateUser updateUser = new UpdateUser(getPelanggans(), getSelectedPelanggan());
        changeWindow(updateUser);
    }
    private void goLogout(){
        setSelectedPelanggan(null);
        LoginSignin loginSignin = new LoginSignin(getPelanggans(), getSelectedPelanggan());
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

        try {
            // Delete from JSON file
            Integer currentId = getSelectedPelanggan().getId();
            boolean deleted = JSONFileManager.deleteFromJSON(
                    Pelanggan.class,
                    pelanggan -> currentId.equals(pelanggan.getId())
            );

            if (deleted) {
                // Remove from in-memory list
                getPelanggans().removeIf(p -> p.getId().intValue() == currentId.intValue());
                System.out.println(getPelanggans().toString());

                JOptionPane.showMessageDialog(
                        panel,
                        "Account deleted successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Navigate back to login
                LoginSignin loginSignin = new LoginSignin(null, null);
                changeWindow(loginSignin);
            } else {
                JOptionPane.showMessageDialog(
                        panel,
                        "Failed to delete account. Account not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    panel,
                    "Error deleting account: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    private void goToListPaketWisataDalamNegeri(){
        ListPaketWisata listPaketWisata = new ListPaketWisata(getPelanggans(), getSelectedPelanggan(), true);
        changeWindow(listPaketWisata);
    }
    private void goToListPaketWisataLuarNegeri(){
        ListPaketWisata listPaketWisata = new ListPaketWisata(getPelanggans(), getSelectedPelanggan(), false);
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

