package model;

import java.time.LocalDateTime;

public class Appointment {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private String status;
    private String reason;
}