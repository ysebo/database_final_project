package controller;

import db.Database;
import model.Doctor;
import model.Model;
import viewer.DoctorPanel;
import viewer.LoginPanel;
import viewer.Viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorController implements ActionListener {
    private Model model ;
    private DoctorPanel doctorPanel;
    private Doctor doctorService;
    private LoginPanel loginPanel;
    public DoctorController(Viewer viewer , DoctorPanel doctorPanel , LoginPanel loginPanel) {
        model = new Model(viewer);
        doctorService = new Doctor();
        this.doctorPanel = doctorPanel;
        this.loginPanel = loginPanel;

    }
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Search":
                String searchQuery = doctorPanel.getSearchQuery();
                var searchResults = doctorService.searchPatient(searchQuery);
                updatePatientTable(searchResults, new String[]{"First Name", "Last Name", "Contact Info"});
                break;
            case "View_Patients":
                int userId = loginPanel.getUserId();
                int doctorId = getDoctorIdByUserId(userId);
                var patientData = doctorService.viewAllPatients(doctorId);
                updatePatientTable(patientData, new String[]{"Patient ID", "First Name", "Last Name"});
                break;
            case "Manage_Appointments":
                int userId2 = loginPanel.getUserId();
                int doctorId2 = getDoctorIdByUserId(userId2);
                var appointmentData = doctorService.manageAppointments(doctorId2);
                updatePatientTable(appointmentData, new String[]{"Appointment ID", "First Name", "Last Name", "Date", "Reason"});
                break;
            default:
                System.out.println("Invalid value");
                break;
        }

    }
    private void updatePatientTable(java.util.List<Object[]> data, String[] columnNames) {
        Object[][] tableData = new Object[data.size()][columnNames.length];
        for (int i = 0; i < data.size(); i++) {
            tableData[i] = data.get(i);
        }
        doctorPanel.setPatientTableData(tableData, columnNames);
    }
    public int getDoctorIdByUserId(int userId) {
        int doctorId = -1;
        try {
            Database database = new Database();
            Connection connection = database.getConnection();
            String query = "SELECT doctor_id FROM doctor_users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                doctorId = resultSet.getInt("doctor_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctorId;
    }

}
