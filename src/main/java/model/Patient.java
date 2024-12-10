package model;

import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    public List<Object[]> viewPatientInfo(int patientId) {
        List<Object[]> patientData = new ArrayList<>();
        try {
            Database database = new Database();
            Connection connection = database.getConnection();

            String query = "SELECT FirstName, LastName, DateOFBirth, Gender, ContactInfo FROM Patient WHERE PatientID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getString("FirstName");
                row[1] = resultSet.getString("LastName");
                row[2] = resultSet.getString("DateOFBirth");
                row[3] = resultSet.getString("Gender");
                row[4] = resultSet.getString("ContactInfo");
                patientData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientData;
    }
    public List<Object[]> viewAppointments(int patientId) {
        List<Object[]> appointmentData = new ArrayList<>();
        try {
            Database database = new Database();
            Connection connection = database.getConnection();

            String query = "SELECT a.AppointmentID, d.FirstName AS DoctorFirstName, d.LastName AS DoctorLastName, " +
                    "a.AppointmentDate, a.Reason FROM Appointment a " +
                    "JOIN Doctor d ON a.DoctorID = d.DoctorID WHERE a.PatientID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getInt("AppointmentID");
                row[1] = resultSet.getString("DoctorFirstName");
                row[2] = resultSet.getString("DoctorLastName");
                row[3] = resultSet.getString("AppointmentDate");
                row[4] = resultSet.getString("Reason");
                appointmentData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointmentData;
    }
    public void bookAppointment(int patientId, int doctorId, String appointmentDate, String reason) {
        try {
            Database database = new Database();
            Connection connection = database.getConnection();

            String query = "INSERT INTO Appointment (PatientID, DoctorID, AppointmentDate, Reason) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);
            statement.setInt(2, doctorId);
            statement.setString(3, appointmentDate);
            statement.setString(4, reason);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
