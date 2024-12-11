package model;

import viewer.LoginPanel;
import viewer.Viewer;

public class Model {
    private LoginPanel loginPanel;
    private Viewer viewer;
    public Model(Viewer viewer) {
        this.viewer = viewer;

    }
    public void showRegisterPanel() {
        viewer.showRegisterPanel();
    }


    public void showLoginPanel() {
        viewer.showLoginPanel();
    }
    public void showPatientPanel() {
        viewer.showPatientPanel();
    }


    public void showAppointmentPanel() {
        viewer.showAppointmentPanel();
    }
}
