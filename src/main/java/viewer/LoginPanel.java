package viewer;

import controller.LoginController;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
    private LoginController loginController;
    public LoginPanel(Viewer viewer) {
        this.viewer = viewer;
        this.userModel = new User();
        loginController = new LoginController( viewer, this);
        File backgroundImage = new File("src/main/resources/background.jpg");
        JLabel usernameLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        usernameLabel.setBounds(350, 300, 100, 30);
        emailField.setBounds(450, 300, 200, 30);
        passwordLabel.setBounds(350, 350, 100, 30);
        passwordField.setBounds(450, 350, 200, 30);
        loginButton.setBounds(450, 400, 100, 30);
        registerButton.setBounds(350, 400, 100, 30);
        setLayout(null);
        add(usernameLabel);
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

        String role = userModel.authenticate(email, password);
        if (role == null) {
            showErrorMessage("Invalid credentials. Please try again.");
            return;
        }

        userModel.setRole(role);
        if ("doctor".equals(role)) {
            viewer.showDoctorPanel();
        } else if ("patient".equals(role)) {
            viewer.showPatientPanel();

        }
    }
}
