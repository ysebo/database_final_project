package viewer;

import controller.RegisterController;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class RegisterPanel extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton registerButton, loginButton;
    private RegisterController registerController;
    private Image background;
    private JLabel nameLabel, emailLabel, passwordLabel, roleLabel;

    public RegisterPanel(Viewer viewer) {
        File backgroundImage = new File("src/main/resources/background.jpg");
        try {
            background = ImageIO.read(backgroundImage);
        } catch (IOException e) {
            System.out.println("Error loading image");
        }
        registerController = new RegisterController(viewer, this);
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        roleLabel = new JLabel("Role:");
        roleComboBox = new JComboBox<>(new String[]{"doctor", "patient"});
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        setLayout(null);
        nameLabel.setBounds(350, 300, 100, 25);
        nameField.setBounds(450, 300, 200, 25);
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

    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }
}
