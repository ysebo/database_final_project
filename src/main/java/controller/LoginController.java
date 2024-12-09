package controller;

import model.Model;
import viewer.LoginPanel;
import viewer.Viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private Model model ;
    LoginPanel loginPanel;
    public LoginController(Viewer viewer, LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
        model = new Model(viewer);
    }
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Login":
                loginPanel.handleLogin();
                break;
            case "Register":
                model.showRegisterPanel();
                break;
            default:
                System.out.println("Invalid value");
                break;
        }

    }

}
