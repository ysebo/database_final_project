package model;

import viewer.LoginPanel;
import viewer.Viewer;

public class Model {
    private LoginPanel loginPanel;
    private User userModel;
    private Viewer viewer;
    public Model(Viewer viewer) {
        this.viewer = viewer;
    }
    public void showRegisterPanel() {
        viewer.showRegisterPanel();
    }



}
