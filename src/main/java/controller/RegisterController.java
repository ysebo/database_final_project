package controller;

import db.Database;
import model.Model;
import viewer.RegisterPanel;
import viewer.Viewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterController implements ActionListener {
    private RegisterPanel registerPanel;
    private Model model ;
    public RegisterController (Viewer viewer , RegisterPanel registerPanel) {
        this.registerPanel = registerPanel;
        model = new Model(viewer);
    }
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Login":
                model.showLoginPanel();
                break;
            case "Register":
                registerPanel.handleRegister();
                break;
            default:
                System.out.println("Invalid value");
                break;
        }

    }


}
