package model;

public class Doctor {
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String contactInfo;
    private int departmentId;
    private String availability;
    public Doctor(Long doctorId, String firstName, String lastName, String specialization, String contactInfo, int departmentId, String availability) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.contactInfo = contactInfo;
        this.departmentId = departmentId;
        this.availability = availability;
    }
}
