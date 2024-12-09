package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    public LoginController() {
    }
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Login":
                System.out.println("Login");
                break;
            case "Register":
                System.out.println("Register");
                break;
            default:
                System.out.println("Invalid value");
                break;
        }

    }
}
