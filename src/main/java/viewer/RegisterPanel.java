package viewer;

import controller.RegisterController;
import db.Database;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterPanel extends JPanel {
    private JTextField nameField, emailField, lastNameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton registerButton, loginButton;
    private RegisterController registerController;
    private Image background;
    private JLabel nameLabel, emailLabel, passwordLabel, roleLabel, lastNameLabel;

    public RegisterPanel(Viewer viewer) {
        File backgroundImage = new File("src/main/resources/background.jpg");
        try {
            background = ImageIO.read(backgroundImage);
        } catch (IOException e) {
            System.out.println("Error loading image");
        }
        registerController = new RegisterController(viewer, this);
        nameLabel = new JLabel("First Name:");
        nameField = new JTextField(20);
        lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        roleLabel = new JLabel("Role:");
        roleComboBox = new JComboBox<>(new String[]{"doctor", "patient"});
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        setLayout(null);
        nameLabel.setBounds(350, 250, 100, 25);
        nameField.setBounds(450, 250, 200, 25);
        lastNameLabel.setBounds(350, 300, 100, 25);
        lastNameField.setBounds(450, 300, 200, 25);
        emailLabel.setBounds(350, 350, 100, 25);
        emailField.setBounds(450, 350, 200, 25);
        passwordLabel.setBounds(350, 400, 100, 25);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(450, 400, 200, 25);
        roleLabel.setBounds(350, 450, 100, 25);
        roleComboBox.setBounds(450, 450, 200, 25);
        registerButton.setBounds(538, 500, 100, 30);
        loginButton.setBounds(865, 38, 100, 30);

        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        registerButton.setBackground(new Color(44, 62, 80));


        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        loginButton.setBackground(new Color(44, 62, 80));

        add(lastNameLabel);
        add(lastNameField);
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(roleLabel);
        add(roleComboBox);
        add(registerButton);
        add(loginButton);
        registerButton.addActionListener(registerController);
        loginButton.addActionListener(registerController);

    }

    public String getName() {
        return nameField.getText().trim();
    }

    public String getLastName() {
        return lastNameField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }

    public String getRole() {
        return roleComboBox.getSelectedItem().toString();
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }

    public void handleRegister() {
        String name = getName();
        String lastName = getLastName();
        String email = getEmail();
        String password = getPassword();
        String role = getRole().toLowerCase();
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Database database = new Database();
            Connection connection = database.getConnection();
            String query = "INSERT INTO users (FirstName, LastName, email, password, role) VALUES (?, ?, ?, ? , ?)";
            PreparedStatement usersStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            usersStatement.setString(1, name);
            usersStatement.setString(2, lastName);
            usersStatement.setString(3, email);
            usersStatement.setString(4, password);
            usersStatement.setString(5, role);

            int rowsInserted = usersStatement.executeUpdate();
            if (rowsInserted > 0) {
                var userGeneratedKeys = usersStatement.getGeneratedKeys();
                int userId = -1;
                if (userGeneratedKeys.next()) {
                    userId = userGeneratedKeys.getInt(1);
                }
                if (role.equals("doctor")) {
                    String doctorInsertQuery = "INSERT INTO Doctor (FirstName, LastName) VALUES (?, ?)";
                    PreparedStatement doctorStmt = connection.prepareStatement(doctorInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                    doctorStmt.setString(1, name);
                    doctorStmt.setString(2, lastName);

                    int doctorRowsInserted = doctorStmt.executeUpdate();
                    if (doctorRowsInserted > 0) {
                        var doctorGeneratedKeys = doctorStmt.getGeneratedKeys();
                        if (doctorGeneratedKeys.next()) {
                            int doctorId = doctorGeneratedKeys.getInt(1);

                            String doctorUserInsertQuery = "INSERT INTO doctor_users (user_id, doctor_id) VALUES (?, ?)";
                            PreparedStatement doctorUserStmt = connection.prepareStatement(doctorUserInsertQuery);
                            doctorUserStmt.setInt(1, userId);
                            doctorUserStmt.setInt(2, doctorId);
                            doctorUserStmt.executeUpdate();
                        }
                    }
                } else if (role.equals("patient")) {
                    String patientInsertQuery = "INSERT INTO Patient (FirstName, LastName, DateOfBirth, Gender) VALUES (?, ?, ?, ?)";
                    PreparedStatement patientStmt = connection.prepareStatement(patientInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                    patientStmt.setString(1, name);
                    patientStmt.setString(2, lastName);
                    patientStmt.setString(3, "2005-01-01");
                    patientStmt.setString(4, "Non-binary");

                    int patientRowsInserted = patientStmt.executeUpdate();
                    if (patientRowsInserted > 0) {
                        var patientGeneratedKeys = patientStmt.getGeneratedKeys();
                        if (patientGeneratedKeys.next()) {
                            int patientId = patientGeneratedKeys.getInt(1);

                            String patientUserInsertQuery = "INSERT INTO patient_users (user_id, patient_id) VALUES (?, ?)";
                            PreparedStatement patientUserStmt = connection.prepareStatement(patientUserInsertQuery);
                            patientUserStmt.setInt(1, userId);
                            patientUserStmt.setInt(2, patientId);
                            patientUserStmt.executeUpdate();
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to register user.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        getNameField().setText("");
        getEmailField().setText("");
        getLastNameField().setText("");
        getPasswordField().setText("");
        getRoleComboBox().setSelectedIndex(0);
    }
}
