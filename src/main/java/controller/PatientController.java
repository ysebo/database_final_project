package controller;

import db.Database;
import model.Model;
import model.Patient;
import viewer.LoginPanel;
import viewer.PatientPanel;
import viewer.Viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PatientController implements ActionListener {
    private PatientPanel patientPanel;
    private Model model ;
    private LoginPanel loginPanel;
    private Patient patientService;
    public PatientController(Viewer viewer , PatientPanel patientPanel , LoginPanel loginPanel) {
        this.patientPanel = patientPanel;
        model = new Model(viewer);
        this.loginPanel = loginPanel;
        patientService = new Patient();
    }
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "My_Info":
                int userId = loginPanel.getUserId();
                int patientId = getPatientIdByUserId(userId);
                var patientData = patientService.viewPatientInfo(patientId);
                updatePatientTable(patientData, new String[]{"First Name", "Last Name", "Date of Birth", "Gender", "Contact Info"});
                break;
            case "View_Appointments":

                int userId1 = loginPanel.getUserId();
                int patientId1 = getPatientIdByUserId(userId1);
                var appointments = patientService.viewAppointments(patientId1);
                updateAppointmentTable(appointments, new String[]{"Appointment ID", "Doctor Name", "Date", "Reason"});
                break;
            case "Book_Appointment":
                patientPanel.showAppointmentBookingFields();
                break;
        }

    }
    private void updatePatientTable(List<Object[]> data, String[] columnNames) {
        Object[][] tableData = new Object[data.size()][columnNames.length];
        for (int i = 0; i < data.size(); i++) {
            tableData[i] = data.get(i);
        }
        patientPanel.setAppointmentsTableData(tableData, columnNames);
    }

    private void updateAppointmentTable(List<Object[]> data, String[] columnNames) {
        Object[][] tableData = new Object[data.size()][columnNames.length];
        for (int i = 0; i < data.size(); i++) {
            tableData[i] = data.get(i);
        }
        patientPanel.setAppointmentsTableData(tableData, columnNames);
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
