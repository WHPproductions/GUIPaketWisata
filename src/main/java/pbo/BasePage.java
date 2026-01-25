package pbo;

import pbo.structure_data.PaketWisata;
import pbo.structure_data.Pelanggan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class BasePage {

    public BasePage() {

    }

    protected abstract JPanel getMainPanel();
    protected abstract void customizeComponents();
    protected abstract void setupEventHandlers();
    public void show() {
        JFrame frame = new JFrame(getTitle());
        frame.setContentPane(getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(getWindowSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void changeWindow(BasePage page) {
        // Get the current frame and dispose of it properly
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(getMainPanel());

        // Create and show a new frame
        JFrame newFrame = new JFrame(page.getTitle());
        newFrame.setContentPane(page.getMainPanel());
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(page.getWindowSize());
        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);

        // Dispose old frame to free memory
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }

    protected String getTitle() {
        return "Paket Wisata";
    }

    protected Dimension getWindowSize() {
        return new Dimension(800, 600);
    }

    // Setting components colors
    protected void applyButtonColor(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    protected void applyLabelColor(JLabel label, Color fgColor) {
        label.setForeground(fgColor);
    }
    protected void applyPanelColor(JPanel panel, Color bgColor) {
        panel.setBackground(bgColor);
    }

    // Setting components default styles
    protected void styleButton(JButton button, Color bgColor, Color fgColor) {
        applyButtonColor(button, bgColor, fgColor);
        button.setFont(UIConstantStyles.DEFAULT_BOLD_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));

        // Add a rounded appearance effect
        button.setOpaque(true);
    }
    protected void stylePrimaryLabelDefault(JLabel label) {
        applyLabelColor(label, UIConstantStyles.BLACK);
        label.setFont(UIConstantStyles.DEFAULT_FONT);
    }
    protected void styleSecondaryLabelDefault(JLabel label) {
        applyLabelColor(label, UIConstantStyles.DARK_BLUE);
        label.setFont(UIConstantStyles.DEFAULT_FONT);
    }
    protected void stylePrimaryLabelBold(JLabel label) {
        applyLabelColor(label, UIConstantStyles.BLACK);
        label.setFont(UIConstantStyles.DEFAULT_BOLD_FONT);
    }
    protected void styleSecondaryLabelBold(JLabel label) {
        applyLabelColor(label, UIConstantStyles.DARK_BLUE);
        label.setFont(UIConstantStyles.DEFAULT_BOLD_FONT);
    }
    protected void styleTextField(JTextField textField) {
        textField.setBackground(UIConstantStyles.WHITE);
        textField.setFont(UIConstantStyles.DEFAULT_FONT);
        textField.setBorder(UIConstantStyles.TEXT_FIELD_BORDER);
    }
}
