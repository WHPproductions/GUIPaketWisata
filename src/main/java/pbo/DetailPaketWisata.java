package pbo;

import pbo.structure_data.Pelanggan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class DetailPaketWisata extends BasePage{
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    private JLabel contentLabel1, contentLabel2, contentLabel3, contentLabel4, contentLabel5, contentLabel6, contentLabel7, contentLabel8;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panelButtons;

    public DetailPaketWisata(ArrayList<Pelanggan> pelanggans, Pelanggan selectedPelanggan) {
        super(pelanggans, selectedPelanggan);
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

        applyPanelColor(panel, UIConstantStyles.LIGHT_GRAY);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelButtons.setBackground(UIConstantStyles.LIGHT_GRAY);
        for(JPanel panel : allLabelPanels){ //iterate to all Panels
            applyPanelColor(panel, UIConstantStyles.WHITE);
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            System.out.println(panel.getComponents());
        }

        // Style label headers (bold, darker color)
        styleSecondaryLabelBold(label1);
        styleSecondaryLabelBold(label2);
        styleSecondaryLabelBold(label3);
        styleSecondaryLabelBold(label4);
        styleSecondaryLabelBold(label5);
        styleSecondaryLabelBold(label6);
        styleSecondaryLabelBold(label7);
        styleSecondaryLabelBold(label8);

        // Style content labels (normal weight)
        stylePrimaryLabelDefault(contentLabel1);
        stylePrimaryLabelDefault(contentLabel2);
        stylePrimaryLabelDefault(contentLabel3);
        stylePrimaryLabelDefault(contentLabel4);
        stylePrimaryLabelDefault(contentLabel5);
        stylePrimaryLabelDefault(contentLabel6);
        stylePrimaryLabelDefault(contentLabel7);
        stylePrimaryLabelDefault(contentLabel8);


        // Style buttons with hover effect
        styleButton(updateButton, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);
        styleButton(deleteButton, UIConstantStyles.DARK_RED, UIConstantStyles.WHITE);
    }

    @Override
    protected void setupEventHandlers() {

    }
}
