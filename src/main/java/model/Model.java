package model;

import viewer.Viewer;

public class Model {

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


}
