package pbo;

import pbo.structure_data.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailPaketWisata extends BasePage{
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    private JLabel contentLabel1, contentLabel2, contentLabel3, contentLabel4, contentLabel5, contentLabel6, contentLabel7, contentLabel8;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panelButtons;
    private JButton backButton;
    private boolean isDalamNegeriMode;

    public DetailPaketWisata(boolean isDalamNegeriMode) {
        this.isDalamNegeriMode = isDalamNegeriMode;
        customizeComponents();
        setupEventHandlers();
    }

    public void goBackToListPaketWisata(){
        Memory.getInstance().setSelectedPaketWisata(null);
        ListPaketWisata listPaketWisata = new ListPaketWisata(isDalamNegeriMode);
        changeWindow(listPaketWisata);
    }
    public void goUpdatePaketWisata(){
        UpdateOrCreatePaketWisata updatePage = new UpdateOrCreatePaketWisata(isDalamNegeriMode, false);
        changeWindow(updatePage);
    }
    public void goDeletePaketWisata(){
        // Confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(
                this.panel,
                "Apakah Anda yakin ingin menghapus paket wisata ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        // If the user clicks no, then stop the code
        if (confirm == JOptionPane.NO_OPTION) return;

        // Delete paket wisata
        PaketWisata paketWisata = Memory.getInstance().getSelectedPaketWisata();
        boolean success;
        // Deletes the correct type of paket wisata
        if (isDalamNegeriMode) {
            success = Memory.getInstance().removePaketWisataDalamNegeri((PaketWisataDalamNegeri) paketWisata);
        } else {
            success = Memory.getInstance().removePaketWisataLuarNegeri((PaketWisataLuarNegeri) paketWisata);
        }
        // Show a success / failure message
        if (success) {
            JOptionPane.showMessageDialog(
                    panel,
                    "Paket wisata berhasil dihapus!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE
            );
            Memory.getInstance().setSelectedPaketWisata(null);
            goBackToListPaketWisata();
        } else {
            JOptionPane.showMessageDialog(
                    panel,
                    "Gagal menghapus paket wisata!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
    }

    @Override
    protected JPanel getMainPanel() {
        return panel;
    }

    @Override
    protected String getTitle(){
        return "Detail Paket Wisata";
    }

    @Override
    protected Dimension getWindowSize(){
        return new Dimension(600, 700);
    }

    @Override
    protected void customizeComponents() {
        // Set panel background with padding
        JPanel[] allLabelPanels = new JPanel[]{
                panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8
        };
        JLabel[] allLabels = new JLabel[]{
                label1, label2, label3, label4, label5, label6, label7, label8
        };
        JLabel[] allContentLabels = new JLabel[]{
                contentLabel1, contentLabel2, contentLabel3, contentLabel4, contentLabel5, contentLabel6, contentLabel7, contentLabel8
        };

        // Panel styling
        applyPanelColor(panel, UIConstantStyles.LIGHT_GRAY);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelButtons.setBackground(UIConstantStyles.LIGHT_GRAY);
        for(JPanel panel : allLabelPanels){ //iterate to all Panels
            applyPanelColor(panel, UIConstantStyles.WHITE);
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        }

        // Style label headers (bold, darker color)
        for(JLabel label : allLabels){
            styleSecondaryLabelBold(label);
        }

        // Style content labels (normal weight)
        for(JLabel label : allContentLabels){
            stylePrimaryLabelDefault(label);
        }


        // Style buttons with a hover effect
        styleButton(updateButton, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);
        styleButton(deleteButton, UIConstantStyles.DARK_RED, UIConstantStyles.WHITE);
        styleButton(backButton, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);

        // Initialize property names and values to all labels
        PaketWisata paketWisata = Memory.getInstance().getSelectedPaketWisata();
        PaketWisataDalamNegeri paketDalamNegeri = null;
        PaketWisataLuarNegeri paketLuarNegeri = null;
        // Check type first, then cast
        if (isDalamNegeriMode) {
            paketDalamNegeri = (PaketWisataDalamNegeri) paketWisata;
            // use paketDalamNegeri
        } else {
            paketLuarNegeri = (PaketWisataLuarNegeri) paketWisata;
            // use paketLuarNegeri
        }

        //
        label1.setText("ID");
        contentLabel1.setText(String.valueOf(paketWisata.getId()));
        label2.setText("Nama Paket");
        contentLabel2.setText(paketWisata.getNamaPaket());
        label3.setText("Destinasi");
        contentLabel3.setText(paketWisata.getDestinasi());
        label4.setText("Harga");
        contentLabel4.setText(String.valueOf(paketWisata.getHarga()));
        label5.setText("Durasi");
        contentLabel5.setText(String.valueOf(paketWisata.getDurasi()) + " Hari");
        if (isDalamNegeriMode) {
            label6.setText("Daerah");
            contentLabel6.setText(paketDalamNegeri.getDaerah());
            label7.setText("Kategori");
            contentLabel7.setText(paketDalamNegeri.getKategori());
            label8.setText("Diskon");
            contentLabel8.setText(String.valueOf(paketDalamNegeri.getDiskon()) + "%");
        } else {
            label6.setText("Negara");
            contentLabel6.setText(paketLuarNegeri.getNegara());
            label7.setText("Tour Guide");
            contentLabel7.setText(paketLuarNegeri.getTourGuide());
            label8.setText("Biaya Visa");
            contentLabel8.setText(String.valueOf(paketLuarNegeri.getBiayaVisa()) + " IDR");
        }
    }

    @Override
    protected void setupEventHandlers() {
        backButton.addActionListener(e -> goBackToListPaketWisata());

        updateButton.addActionListener(e -> goUpdatePaketWisata());

        deleteButton.addActionListener(e -> goDeletePaketWisata());
    }
}
