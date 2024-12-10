package model;

import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    public List<Object[]> searchPatient(String patientName) {
        List<Object[]> patientData = new ArrayList<>();
        try {
            Database database = new Database();
            Connection connection = database.getConnection();

            String query = "SELECT * FROM Patient WHERE FirstName LIKE ? OR LastName LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + patientName + "%");
            statement.setString(2, "%" + patientName + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Object[] row = new Object[3];
                row[0] = resultSet.getString("FirstName");
                row[1] = resultSet.getString("LastName");
                row[2] = resultSet.getString("ContactInfo");
                patientData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientData;
    }
    public List<Object[]> viewAllPatients(int doctorId) {
        List<Object[]> patientData = new ArrayList<>();
        try {
            Database database = new Database();
            Connection connection = database.getConnection();

            String query = "SELECT DISTINCT p.PatientID, p.FirstName, p.LastName FROM Appointment a " +
                    "JOIN Patient p ON a.PatientID = p.PatientID WHERE a.DoctorID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, doctorId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Object[] row = new Object[3];
                row[0] = resultSet.getInt("PatientID");
                row[1] = resultSet.getString("FirstName");
                row[2] = resultSet.getString("LastName");
                patientData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientData;
    }
    public List<Object[]> manageAppointments(int doctorId) {
        List<Object[]> appointmentData = new ArrayList<>();
        try {
            Database database = new Database();
            Connection connection = database.getConnection();

            String query = "SELECT a.AppointmentID, p.FirstName AS PatientFirstName, p.LastName AS PatientLastName, " +
                    "a.AppointmentDate, a.Reason FROM Appointment a " +
                    "JOIN Patient p ON a.PatientID = p.PatientID WHERE a.DoctorID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, doctorId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getInt("AppointmentID");
                row[1] = resultSet.getString("PatientFirstName");
                row[2] = resultSet.getString("PatientLastName");
                row[3] = resultSet.getString("AppointmentDate");
                row[4] = resultSet.getString("Reason");
                appointmentData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentData;
    }

}
