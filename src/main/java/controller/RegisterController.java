package controller;

import db.Database;
import model.Model;
import viewer.RegisterPanel;
import viewer.Viewer;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController implements ActionListener {
    private RegisterPanel registerPanel;
    private Database database;
    private Model model ;
    public RegisterController (Viewer viewer , RegisterPanel registerPanel) {
        this.registerPanel = registerPanel;
        database = new Database();
        model = new Model(viewer);
    }
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Login":
                model.showLoginPanel();
                break;
            case "Register":
                String name = registerPanel.getName();
                String email = registerPanel.getEmail();
                String password = registerPanel.getPassword();
                String role = registerPanel.getRole();
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(registerPanel, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Connection connection = database.getConnection();
                    String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, password);
                    preparedStatement.setString(4, role);

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(registerPanel, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        resetFields();
                    } else {
                        JOptionPane.showMessageDialog(registerPanel, "Failed to register user.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(registerPanel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                System.out.println("Invalid value");
                break;
        }

    }

    private void resetFields() {
        registerPanel.getNameField().setText("");
        registerPanel.getEmailField().setText("");
        registerPanel.getPasswordField().setText("");
        registerPanel.getRoleComboBox().setSelectedIndex(0);
    }
}
