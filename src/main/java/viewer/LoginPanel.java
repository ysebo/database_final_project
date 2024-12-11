package viewer;

import controller.LoginController;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton , registerButton;
    private Image background;
    private Viewer viewer;
    private User userModel;
    int userId;
    private LoginController loginController;
    public LoginPanel(Viewer viewer) {
        this.viewer = viewer;
        loginController = new LoginController( viewer, this);
        File backgroundImage = new File("src/main/resources/background.jpg");
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        userModel = new User();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        emailLabel.setBounds(350, 300, 100, 30);
        emailField.setBounds(450, 300, 200, 30);
        passwordLabel.setBounds(350, 350, 100, 30);
        passwordField.setBounds(450, 350, 200, 30);
        loginButton.setBounds(529, 400, 100, 30);
        registerButton.setBounds(865, 38, 100, 30);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        registerButton.setBackground(new Color(44, 62, 80));


        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        loginButton.setBackground(new Color(44, 62, 80));

        setLayout(null);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.setActionCommand("Login");
        loginButton.addActionListener(loginController);
        registerButton.setActionCommand("Register");
        registerButton.addActionListener(loginController);
        try {
            background = ImageIO.read(backgroundImage);
        } catch (IOException e) {
            System.out.println("Error loading image");
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0 , 0, getWidth() , getHeight() ,  null);
    }
    public String getEmail() {
        return emailField.getText();
    }
    public void setLoginController(LoginController controller) {
        this.loginController = controller;
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public  void handleLogin() {

        String email = getEmail();
        String password = getPassword();
        User user1 = userModel.authenticate(email, password);
        if (user1 == null) {
            showErrorMessage("Invalid credentials. Please try again.");
            return;
        }
        String role = user1.getRole();
        userId = user1.getUserId();
        setUserId(userId);
        if ("doctor".equals(role)) {
            viewer.showDoctorPanel();
        } else if ("patient".equals(role)) {
            viewer.showPatientPanel();

        }
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
