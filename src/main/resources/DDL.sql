CREATE TABLE Department
(
    DepartmentID   SERIAL PRIMARY KEY,
    DepartmentName VARCHAR(100) NOT NULL
);

CREATE TABLE Doctor
(
    DoctorID     SERIAL PRIMARY KEY,
    FirstName    VARCHAR(50) NOT NULL,
    LastName     VARCHAR(50) NOT NULL,
    ContactInfo  VARCHAR(100),
    DepartmentID INT,
    Availability VARCHAR(100),
    CONSTRAINT fk_doctor_department FOREIGN KEY (DepartmentID) REFERENCES Department (DepartmentID) ON DELETE SET NULL
);
CREATE TABLE Patient
(
    PatientID        SERIAL PRIMARY KEY,
    FirstName        VARCHAR(50) NOT NULL,
    LastName         VARCHAR(50) NOT NULL,
    DateOFBirth      VARCHAR(28)        NOT NULL,
    Gender           VARCHAR(10) CHECK (Gender IN ('Male', 'Female', 'Other')),
    ContactInfo      VARCHAR(100),
    EmergencyContact VARCHAR(100),
    Address          TEXT
);
CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    FirstName VARCHAR(50)                                       NOT NULL,
    LastName  VARCHAR(50)                                       NOT NULL,
    email     VARCHAR(100) UNIQUE                               NOT NULL,
    password  VARCHAR(255)                                      NOT NULL,
    role      VARCHAR(20) CHECK (role IN ('doctor', 'patient')) NOT NULL
);
CREATE TABLE doctor_users
(
    id       SERIAL PRIMARY KEY,
    user_id  INT NOT NULL,
    doctor_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES Doctor (DoctorID) ON DELETE CASCADE
);

CREATE TABLE patient_users
(
    id        SERIAL PRIMARY KEY,
    user_id   INT NOT NULL,
    patient_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES Patient (PatientID) ON DELETE CASCADE
);
CREATE TABLE Appointment
(
    AppointmentID   SERIAL PRIMARY KEY,
    PatientID       INT       NOT NULL,
    DoctorID        INT       NOT NULL,
    AppointmentDate TIMESTAMP NOT NULL,
    Reason          TEXT,
    Status          VARCHAR(20) DEFAULT 'Scheduled' CHECK (Status IN ('Scheduled', 'Completed', 'Cancelled')),
    CONSTRAINT fk_appointment_patient FOREIGN KEY (PatientID) REFERENCES Patient (PatientID),
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (DoctorID) REFERENCES Doctor (DoctorID)
);
CREATE TABLE MedicalRecord
(
    MedicalRecordID SERIAL PRIMARY KEY,
    PatientID       INT         NOT NULL,
    RecordDate      VARCHAR(70) NOT NULL,
    Diagnosis       TEXT,
    Treatment       TEXT,
    Medication      TEXT,
    Allergies       TEXT,
    Notes           TEXT,
    FOREIGN KEY (PatientID) REFERENCES Patient (PatientID) ON DELETE CASCADE
);
INSERT INTO Department (DepartmentName)
VALUES ('Cardiology'),
       ('Neurology'),
       ('Pediatrics');
INSERT INTO users (FirstName, LastName, email, password, role)
VALUES ('Erdan', 'Aitkaziev', 'doctor@gmail.com', '123', 'doctor'),
       ('Erkin', 'Mamadaliev', 'patient@gmail.com', '123', 'patient');

VALUES (1, 1, '2024-12-15 10:00:00', 'Routine Checkup', 'Scheduled');
INSERT INTO Doctor (FirstName, LastName, ContactInfo, DepartmentID, Availability)
VALUES ('Miller', 'Erdanovich', '123-456-7890', 1, 'Mon-Fri 9AM-5PM');

INSERT INTO Patient (FirstName, LastName, DateOFBirth, Gender, ContactInfo, EmergencyContact, Address)
VALUES ('Aman', 'Nursbekov', '1990-01-01', 'Male', '987-654-3210', '123-456-7890', '123 Main St'),
('Erdan', 'Uuulu', '1985-02-15', 'Male', '555-555-5555', '987-654-3210', '456 Elm St');

INSERT INTO MedicalRecord (MedicalRecordID, PatientID, RecordDate, Diagnosis, Treatment, Medication, Allergies, Notes)
VALUES (1, 1, ' December 5', 'Hypertension', 'Mukaltin ili Koydun mayu', 'None', 'Blood pressure measurement',
        'Patient advised to monitor diseases daily'),
       (2, 2, '12 January', 'Common Cold', 'Paracetamol', 'None', 'None', 'Rest and hydration recommended');
INSERT INTO Appointment (PatientID, DoctorID, AppointmentDate, Reason, Status)
VALUES (1, 1    , '2024-12-11 10:00:00', 'Routine Checkup', 'Scheduled');
