package viewer;

import controller.Controller;

import javax.swing.JFrame;
import java.awt.CardLayout;

public class Viewer {
    private Controller controller;
    private CardLayout cardLayout;
    private DoctorPanel doctorPanel;
    private PatientPanel patientPanel;
    private JFrame frame;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;

    public Viewer() {
        controller = new Controller();
        cardLayout = new CardLayout();
        doctorPanel = new DoctorPanel();
        patientPanel = new PatientPanel();
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(cardLayout);
        frame.add(patientPanel, "patientPanel");
        frame.add(doctorPanel, "doctorPanel");
        frame.add(loginPanel, "loginPanel");
        frame.add(registerPanel, "registerPanel");
        frame.setVisible(true);
        frame.setResizable(false);
        showLoginPanel();

    }

    public void showDoctorPanel() {
        cardLayout.show(frame.getContentPane(), "doctorPanel");
    }

    public void showPatientPanel() {
        cardLayout.show(frame.getContentPane(), "patientPanel");
    }

    public void showLoginPanel() {
        cardLayout.show(frame.getContentPane(), "loginPanel");
    }
    public void showRegisterPanel() {
        cardLayout.show(frame.getContentPane(), "registerPanel");
    }
}
