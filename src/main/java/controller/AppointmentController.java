package controller;

import db.Database;
import model.Model;
import model.Patient;
import viewer.AppointmentPanel;
import viewer.LoginPanel;
import viewer.Viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppointmentController implements ActionListener {
    private Model model;
    private AppointmentPanel appointmentPanel;
    private Patient patientService;
    private LoginPanel loginPanel;
    public AppointmentController(Viewer viewer , AppointmentPanel appointmentPanel , LoginPanel loginPanel) {
        model = new Model(viewer);
        this.appointmentPanel = appointmentPanel;
        patientService = new Patient();
        this.loginPanel = loginPanel;

    }
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Confirm":
                System.out.println("appointment confirm");
                int userId = loginPanel.getUserId();
                int patientId = getPatientIdByUserId(userId);
                String doctor = appointmentPanel.getDoctorId();
                int doctorId = Integer.parseInt(doctor);
                String appointmentDate = appointmentPanel.getAppointmentDate();
                String reason = appointmentPanel.getReason();
                patientService.bookAppointment(patientId,doctorId, appointmentDate, reason);
                appointmentPanel.showAppointmentDialog();
                appointmentPanel.clearAllFields();                break;
            case "Back":
                System.out.println("appointment back");
                model.showPatientPanel();
                break;
        }
    }
    public int getPatientIdByUserId(int userId) {
        int doctorId = -1;
        try {
            Database database = new Database();
            Connection connection = database.getConnection();
            String query = "SELECT patient_id FROM patient_users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                doctorId = resultSet.getInt("patient_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctorId;
    }

}
