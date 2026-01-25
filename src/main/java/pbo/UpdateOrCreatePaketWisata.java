package pbo;

import pbo.structure_data.PaketWisata;
import pbo.structure_data.PaketWisataDalamNegeri;
import pbo.structure_data.PaketWisataLuarNegeri;
import pbo.structure_data.Pelanggan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class UpdateOrCreatePaketWisata extends BasePage {
    private JPanel panel;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7, textField8;
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    private JPanel buttonPanels;
    private JPanel textLabelsPanel;
    private JLabel[] textLabels;
    private JPanel textFieldsPanel;
    private JTextField[] textFields;
    private boolean isDalamNegeriMode;
    private boolean isNewPaketMode;

    public UpdateOrCreatePaketWisata(boolean isDalamNegeriMode, boolean isNewPaketMode) {
        this.isNewPaketMode = isNewPaketMode;
        this.isDalamNegeriMode = isDalamNegeriMode;

        // Initialize arrays after panels are created
        initializeLabelComponents();

        customizeComponents();
        setupEventHandlers();
    }

    private void initializeLabelComponents() {
        // Get components and filter them into proper arrays
        if (textLabelsPanel != null) {
            Component[] labelComponents = textLabelsPanel.getComponents();
            ArrayList<JLabel> labelList = new ArrayList<>();
            for (Component comp : labelComponents) {
                if (comp instanceof JLabel) {
                    labelList.add((JLabel) comp);
                }
            }
            textLabels = labelList.toArray(new JLabel[0]);
        } else {
            textLabels = new JLabel[0];
        }

        if (textFieldsPanel != null) {
            Component[] fieldComponents = textFieldsPanel.getComponents();
            ArrayList<JTextField> fieldList = new ArrayList<>();
            for (Component comp : fieldComponents) {
                if (comp instanceof JTextField) {
                    fieldList.add((JTextField) comp);
                }
            }
            textFields = fieldList.toArray(new JTextField[0]);
        } else {
            textFields = new JTextField[0];
        }
    }

    private boolean inputIsValid(){
        // Validate input fields
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        panel,
                        "Isi semua input!",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        try {
            Integer.parseInt(textField1.getText());
            Double.parseDouble(textField4.getText());
            Integer.parseInt(textField5.getText());
            Double.parseDouble(textField8.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    panel,
                    "Input harus berupa angka!",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void goSaveNewPaketWisataDalamNegeri() {
        // Check if input is valid
        if (!inputIsValid()) return;

        PaketWisataDalamNegeri newPaketWisata = new PaketWisataDalamNegeri(
                Integer.parseInt(textField1.getText()),
                textField2.getText(),
                textField3.getText(),
                Double.parseDouble(textField4.getText()),
                Integer.parseInt(textField5.getText()),
                textField6.getText(),
                textField7.getText(),
                Double.parseDouble(textField8.getText())
            );
        Memory.getInstance().addPaketWisataDalamNegeri(newPaketWisata);

        JOptionPane.showMessageDialog(
                panel,
                "Paket Wisata Dalam Negeri saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Navigate back to PaketWisata list
        goCancelToListPaketWisata();
    }
    private void goSaveNewPaketWisataLuarNegeri() {
        // Check if input is valid
        if (!inputIsValid()) return;

        PaketWisataLuarNegeri newPaketWisata = new PaketWisataLuarNegeri(
                Integer.parseInt(textField1.getText()),
                textField2.getText(),
                textField3.getText(),
                Double.parseDouble(textField4.getText()),
                Integer.parseInt(textField5.getText()),
                textField6.getText(),
                textField7.getText(),
                Double.parseDouble(textField8.getText())
        );
        Memory.getInstance().addPaketWisataLuarNegeri(newPaketWisata);

        JOptionPane.showMessageDialog(
                panel,
                "Paket Wisata Dalam Negeri saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Navigate back to PaketWisata list
        goCancelToListPaketWisata();
    }
    private void goUpdatePaketWisataDalamNegeri(){
        PaketWisataDalamNegeri paketDalamNegeri = new PaketWisataDalamNegeri(
                Integer.parseInt(textFields[0].getText()),
                textFields[1].getText(),
                textFields[2].getText(),
                Double.parseDouble(textFields[3].getText()),
                Integer.parseInt(textFields[4].getText()),
                textFields[5].getText(),
                textFields[6].getText(),
                Double.parseDouble(textFields[7].getText())
        );

        try {
            Memory.getInstance().updatePaketWisataDalamNegeri(paketDalamNegeri);
        }  catch (Error e) {
        if (e.getMessage().equals("No paket selected")) {
            JOptionPane.showMessageDialog(panel,
                    "No paket selected",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (e.getMessage().equals("Paket ID already exists")) {
            JOptionPane.showMessageDialog(panel,
                    "Paket ID already exists",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return;
    }
        JOptionPane.showMessageDialog(
                panel,
                "Paket wisata berhasil diupdate!",
                "Berhasil Update",
                JOptionPane.INFORMATION_MESSAGE);

        DetailPaketWisata detailPage = new DetailPaketWisata(isDalamNegeriMode);
        changeWindow(detailPage);
    }
    private void goUpdatePaketWisataLuarNegeri(){
        PaketWisataLuarNegeri paketLuarNegeri = new PaketWisataLuarNegeri(
                Integer.parseInt(textFields[0].getText()),
                textFields[1].getText(),
                textFields[2].getText(),
                Double.parseDouble(textFields[3].getText()),
                Integer.parseInt(textFields[4].getText()),
                textFields[5].getText(),
                textFields[6].getText(),
                Double.parseDouble(textFields[7].getText())
        );
        try {
            Memory.getInstance().updatePaketWisataLuarNegeri(paketLuarNegeri);
        }  catch (Error e) {
            if (e.getMessage().equals("No paket selected")) {
                JOptionPane.showMessageDialog(panel,
                        "No paket selected",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (e.getMessage().equals("Paket ID already exists")) {
                JOptionPane.showMessageDialog(panel,
                        "Paket ID already exists",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        JOptionPane.showMessageDialog(
                panel,
                "Paket wisata berhasil diupdate!",
                "Berhasil Update",
                JOptionPane.INFORMATION_MESSAGE);

        DetailPaketWisata detailPage = new DetailPaketWisata(isDalamNegeriMode);
        changeWindow(detailPage);
    }
    private void goCancelToListPaketWisata(){
        ListPaketWisata listPaketWisata = new ListPaketWisata(isDalamNegeriMode);
        changeWindow(listPaketWisata);
    }
    private void goCancelToDetailPaketWisata(){
        DetailPaketWisata detailPaketWisata = new DetailPaketWisata(isDalamNegeriMode);
        changeWindow(detailPaketWisata);
    }

    @Override
    protected JPanel getMainPanel() {
        return panel;
    }

    @Override
    protected void customizeComponents() {
        // Labels initialization
        textLabels[0].setText("ID");
        textLabels[1].setText("Nama Paket");
        textLabels[2].setText("Destinasi");
        textLabels[3].setText("Harga");
        textLabels[4].setText("Durasi");
        if (isDalamNegeriMode) {
            textLabels[5].setText("Daerah");
            textLabels[6].setText("Kategori");
            textLabels[7].setText("Diskon");
        } else {
            textLabels[5].setText("Negara");
            textLabels[6].setText("Tour Guide");
            textLabels[7].setText("Biaya Visa");
        }

        // Set the panel background with padding
        applyPanelColor(panel, UIConstantStyles.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        for (Component component : panel.getComponents()) {
            component.setFont(UIConstantStyles.DEFAULT_FONT);
        }

        // Style labels
        for (Component component : panel.getComponents()) { //total of 8 labels
            if (component instanceof JLabel) {
                stylePrimaryLabelBold((JLabel) component);
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


        // If this is a new paket mode, then stop the code
        if (isNewPaketMode) return;
        // Cast the correct type of paket wisata
        PaketWisata paketWisata = Memory.getInstance().getSelectedPaketWisata();
        PaketWisataDalamNegeri paketDalamNegeri = null;
        PaketWisataLuarNegeri paketLuarNegeri = null;
        if (isDalamNegeriMode) {
            paketDalamNegeri = (PaketWisataDalamNegeri) paketWisata;
        } else {
            paketLuarNegeri = (PaketWisataLuarNegeri) paketWisata;
        }

        // Set text fields with paket wisata data
        // Very hardcoded I know :(
        textFields[0].setText(String.valueOf(paketWisata.getId()));
        textFields[1].setText(paketWisata.getNamaPaket());
        textFields[2].setText(paketWisata.getDestinasi());
        textFields[3].setText(String.valueOf(paketWisata.getHarga()));
        textFields[4].setText(String.valueOf(paketWisata.getDurasi()));
        if (isDalamNegeriMode) {
            textFields[5].setText(paketDalamNegeri.getDaerah());
            textFields[6].setText(paketDalamNegeri.getKategori());
            textFields[7].setText(String.valueOf(paketDalamNegeri.getDiskon()));
        } else {
            textFields[5].setText(paketLuarNegeri.getNegara());
            textFields[6].setText(paketLuarNegeri.getTourGuide());
            textFields[7].setText(String.valueOf(paketLuarNegeri.getBiayaVisa()));
        }
    }

    @Override
    protected void setupEventHandlers() {
        if (isNewPaketMode && isDalamNegeriMode) {
            saveButton.addActionListener(e -> goSaveNewPaketWisataDalamNegeri());
        } else if (isNewPaketMode && !isDalamNegeriMode) {
            saveButton.addActionListener(e -> goSaveNewPaketWisataLuarNegeri());
        } else if (!isNewPaketMode && isDalamNegeriMode) {
            saveButton.addActionListener(e -> goUpdatePaketWisataDalamNegeri());
        } else if (!isNewPaketMode && !isDalamNegeriMode) {
            saveButton.addActionListener(e -> goUpdatePaketWisataLuarNegeri());
        }

        if (isNewPaketMode) {
            cancelButton.addActionListener(e -> goCancelToListPaketWisata());
        } else {
            cancelButton.addActionListener(e -> goCancelToDetailPaketWisata());
        }
    }

    @Override
    protected String getTitle() {
        if (isNewPaketMode && isDalamNegeriMode) {
            return "Tambah Paket Wisata Dalam Negeri";
        } else if (isNewPaketMode && !isDalamNegeriMode) {
            return "Tambah Paket Wisata Luar Negeri";
        } else if (!isNewPaketMode && isDalamNegeriMode) {
            return "Update Paket Wisata Dalam Negeri";
        } else {
            return "Update Paket Wisata Luar Negeri";
        }
    }

    @Override
    protected Dimension getWindowSize() {
        return new Dimension(600, 600);
    }

}
