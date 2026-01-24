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

    public LoginSignin(ArrayList<Pelanggan> testPelanggans, Pelanggan pelanggan0) {
        super(testPelanggans, pelanggan0);
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

        // Sign up button handler
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
        try {
            List<Pelanggan> users = JSONFileManager.readFromJSON(Pelanggan.class);

            Pelanggan foundUser = users.stream()
                    .filter(user -> user.getId() == id)
                    .findFirst()
                    .orElse(null);

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
                BasePage dashboard = new DashBoard((ArrayList<Pelanggan>) users, foundUser);
                changeWindow(dashboard);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(panel,
                    "Error reading user data: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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

        // Check for duplicate ID and create new user
        try {
            JSONFileManager.setFileName("users.json");
            List<Pelanggan> users = JSONFileManager.readFromJSON(Pelanggan.class);

            // Check for duplicate ID
            for (Pelanggan user : users) {
                if (user.getId() == id) {
                    JOptionPane.showMessageDialog(panel,
                            "Pelanggan ID already exists. Please choose a different ID.",
                            "Sign Up Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Create new user
            Pelanggan newUser = new Pelanggan(id, password);
            JSONFileManager.appendToJSON(newUser, Pelanggan.class);

            JOptionPane.showMessageDialog(panel,
                    "Sign up successful! You can now login.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Clear fields
            loginField.setText("");
            passwordField.setText("");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(panel,
                    "Error saving user data: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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
