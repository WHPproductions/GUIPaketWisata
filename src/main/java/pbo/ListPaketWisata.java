package pbo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pbo.structure_data.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class ListPaketWisata extends BasePage {
    private JPanel panel;
    private JTable tabelPaketWisata;
    private JButton exitButton;
    private JButton newPaketButton;
    private JTextField inputCari;
    private JButton cariButton;
    private final boolean isDalamNegeriMode;

    public ListPaketWisata(boolean isDalamNegeriMode) {
        this.isDalamNegeriMode = isDalamNegeriMode;
        customizeComponents();
        setupEventHandlers();
        initializeTable();
    }

    private void initializeTable() {
        // Make the table array model
        ArrayList<? extends PaketWisata> paketWisataList = isDalamNegeriMode ?
            Memory.getInstance().getSelectedPelanggan().getDaftarPaketDalamNegeri() :
            Memory.getInstance().getSelectedPelanggan().getDaftarPaketLuarNegeri();
    
        Object[][] data = new Object[paketWisataList.size()][5];
    
        for (PaketWisata paket : paketWisataList) {
            int row = paketWisataList.indexOf(paket);
            data[row][0] = paket.getId();
            data[row][1] = paket.getNamaPaket();
            data[row][2] = paket.getDestinasi();
            data[row][3] = paket.getHarga();
            data[row][4] = paket.getDurasi();
        }
        // Populate the table UI
        DefaultTableModel tableModel = new DefaultTableModel(
                data,
                Memory.getInstance().getSelectedPelanggan().getColumnNames()
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table read-only
            }
        };

        // Apply styles and populate the table
        tabelPaketWisata.setModel(tableModel);
    }

    private void goToDashboard(){
        DashBoard dashBoard = new DashBoard();
        changeWindow(dashBoard);
    }
    private void goToCreatePaketWisata(){
        UpdateOrCreatePaketWisata createPaketWisata = new UpdateOrCreatePaketWisata(
                isDalamNegeriMode,
                true  // isNewPaketMode = true for creating new Paket Wisata
        );
        changeWindow(createPaketWisata);
    }
    private void goToDetailPaketWisata(){
        int id;
        try {
           id = Integer.parseInt(inputCari.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel,
                    "ID harus berupa angka!",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Set paket wisata dalam memori
        PaketWisata paketWisata = isDalamNegeriMode
                ? Memory.getInstance().findPaketWisataDalamNegeriByID(id)
                : Memory.getInstance().findPaketWisataLuarNegeriByID(id);
        if (paketWisata == null) {
            JOptionPane.showMessageDialog(panel,
                    "Paket Wisata dengan ID tersebut tidak ditemukan!",
                    "Paket Wisata Tidak Ditemukan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Memory.getInstance().setSelectedPaketWisata(paketWisata);

        DetailPaketWisata detailPaketWisata = new DetailPaketWisata(isDalamNegeriMode);
        changeWindow(detailPaketWisata);
    }

    @Override
    protected JPanel getMainPanel() {
        return panel;
    }

    @Override
    protected void customizeComponents() {
        // Panel styling
        applyPanelColor(panel, UIConstantStyles.WHITE);
        panel.setBorder(UIConstantStyles.MAIN_PANEL_PADDING);

        // Button styling
        styleButton(exitButton, UIConstantStyles.DARK_RED, UIConstantStyles.WHITE);
        styleButton(newPaketButton, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);
        styleButton(cariButton, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);

        // TextField styling
        styleTextField(inputCari);

        // Table styling
        styleTable();
    }

    @Override
    protected void setupEventHandlers() {
        exitButton.addActionListener(e -> goToDashboard());
        newPaketButton.addActionListener(e -> goToCreatePaketWisata());
        cariButton.addActionListener(e -> goToDetailPaketWisata());
    }

    private void styleTable() {
        // Table header styling
        JTableHeader header = tabelPaketWisata.getTableHeader();
        header.setBackground(UIConstantStyles.DARK_BLUE);
        header.setForeground(UIConstantStyles.WHITE);
        header.setFont(UIConstantStyles.DEFAULT_BOLD_FONT);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        // Table body styling
        tabelPaketWisata.setFont(UIConstantStyles.DEFAULT_FONT);
        tabelPaketWisata.setRowHeight(35);
        tabelPaketWisata.setSelectionBackground(new Color(173, 216, 230)); // Light blue
        tabelPaketWisata.setSelectionForeground(UIConstantStyles.BLACK);
        tabelPaketWisata.setShowGrid(true);
        tabelPaketWisata.setGridColor(UIConstantStyles.LIGHT_GRAY);
        tabelPaketWisata.setIntercellSpacing(new Dimension(1, 1));

        // Center align cell content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        tabelPaketWisata.setDefaultRenderer(Object.class, centerRenderer);
    }

    @Override
    protected String getTitle() {
        return isDalamNegeriMode
                ? "List Paket Wisata Dalam Negeri"
                : "List Paket Wisata Luar Negeri";
    }

    @Override
    protected Dimension getWindowSize() {
        return new Dimension(900, 550);
    }
}
