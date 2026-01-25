package pbo;


import com.fasterxml.jackson.databind.ser.Serializers;
import pbo.structure_data.Pelanggan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginSignin extends BasePage {
    private JPanel panel;
    private JTextField loginField;
    private JButton loginButton;
    private JButton signupButton;
    private JPasswordField passwordField;
    private JLabel idLabel;
    private JLabel passwordLabel;

    public LoginSignin() {
        customizeComponents();
        setupEventHandlers();
    }

    @Override
    protected JPanel getMainPanel() {
        return panel;
    }
    @Override
    protected void customizeComponents() {
        // Set the panel background with modern color
        applyPanelColor(panel, UIConstantStyles.WHITE);
        panel.setBorder(new EmptyBorder(40, 50, 40, 50));

        // Style labels
        styleSecondaryLabelBold(idLabel);
        styleSecondaryLabelBold(passwordLabel);

        // Style text fields
        styleTextField(loginField);
        styleTextField(passwordField);

        // Style buttons with modern design
        styleButton(loginButton, UIConstantStyles.DARK_BLUE, UIConstantStyles.WHITE);
        styleButton(signupButton, UIConstantStyles.DARK_RED, UIConstantStyles.WHITE);
    }
    @Override
    protected void setupEventHandlers() {
        // Login button handler
        loginButton.addActionListener(e -> handleLogin());

        // Signup button handler
        signupButton.addActionListener(e -> handleSignup());
    }

    private void handleLogin() {
        String idText = loginField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validate input
        if (idText.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(panel,
                    "Please fill in all fields",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Parse ID
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel,
                    "ID must be a valid number",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check credentials
        Pelanggan foundUser = Memory.getInstance().getPelangganById(id);

        if (foundUser == null) {
            JOptionPane.showMessageDialog(panel,
                    "Pelanggan ID not found",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
        } else if (!foundUser.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(panel,
                    "Incorrect password",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(panel,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Navigate to Dashboard
            SwingUtilities.getWindowAncestor(panel).dispose();
            Memory.getInstance().setSelectedPelanggan(foundUser);
            BasePage dashboard = new DashBoard();
            changeWindow(dashboard);
        }
    }

    private void handleSignup() {
        String idText = loginField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validate input
        if (idText.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(panel,
                    "Please fill in all fields",
                    "Sign Up Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Parse ID
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel,
                    "ID must be a valid number",
                    "Sign Up Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check for duplicate ID and create a new user
        List<Pelanggan> users = Memory.getInstance().getPelanggans();

        // Check for duplicate ID
        for (Pelanggan user : users) {
            if (user.getId().equals(id)) {
                JOptionPane.showMessageDialog(panel,
                        "Pelanggan ID already exists. Please choose a different ID.",
                        "Sign Up Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Create a new user
        Pelanggan newUser = new Pelanggan(id, password);
        Memory.getInstance().addPelanggan(newUser);

        JOptionPane.showMessageDialog(panel,
                "Sign up successful! You can now login.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    protected String getTitle() {
        return "Login / Sign In - Paket Wisata";
    }

    @Override
    protected Dimension getWindowSize() {
        return new Dimension(550, 350);
    }

}
