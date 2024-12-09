package model;

import java.util.Date;

public class Patient {
    private Long patientId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String contactInfo;
    private String emergencyContact;
    private String address;
    private String medicalHistory;
    public Patient(Long patientId, String firstName, String lastName, Date dateOfBirth, String gender, String contactInfo, String emergencyContact, String address, String medicalHistory) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.emergencyContact = emergencyContact;
        this.address = address;
        this.medicalHistory = medicalHistory;
    }
}
