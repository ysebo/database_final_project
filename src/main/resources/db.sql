CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       FirstName VARCHAR(50) NOT NULL,
                       LastName VARCHAR(50) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) CHECK (role IN ('doctor', 'patient')) NOT NULL,
                       reference_id INT,
                       reference_type VARCHAR(20) CHECK (reference_type IN ('doctor', 'patient')) NOT NULL,
                       CONSTRAINT fk_users_doctor FOREIGN KEY (reference_id) REFERENCES Doctor(DoctorID),
                       CONSTRAINT fk_users_patient FOREIGN KEY (reference_id) REFERENCES Patient(PatientID)
);

CREATE TABLE Department (
                            DepartmentID SERIAL PRIMARY KEY,
                            DepartmentName VARCHAR(100) NOT NULL
);
CREATE TABLE Doctor (
                        DoctorID SERIAL PRIMARY KEY,
                        FirstName VARCHAR(50) NOT NULL,
                        LastName VARCHAR(50) NOT NULL,
                        ContactInfo VARCHAR(100),
                        DepartmentID INT,
                        Availability VARCHAR(100),
                        CONSTRAINT fk_doctor_department FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID) ON DELETE SET NULL
);
CREATE TABLE Patient (
                         PatientID SERIAL PRIMARY KEY,
                         FirstName VARCHAR(50) NOT NULL,
                         LastName VARCHAR(50) NOT NULL,
                         DateOFBirth DATE NOT NULL,
                         Gender VARCHAR(10) CHECK (Gender IN ('Male', 'Female', 'Other')),
                         ContactInfo VARCHAR(100),
                         EmergencyContact VARCHAR(100),
                         Address TEXT);
CREATE TABLE Appointment (
                             AppointmentID SERIAL PRIMARY KEY,
                             PatientID INT NOT NULL,
                             DoctorID INT NOT NULL,
                             AppointmentDate TIMESTAMP NOT NULL,
                             Reason TEXT,
                             Status VARCHAR(20) DEFAULT 'Scheduled' CHECK (Status IN ('Scheduled', 'Completed', 'Cancelled')),
                             CONSTRAINT fk_appointment_patient FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
                             CONSTRAINT fk_appointment_doctor FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);
CREATE TABLE MedicalRecord (
                               MedicalRecordID SERIAL PRIMARY KEY,
                               PatientID INT NOT NULL,
                               RecordDate DATE NOT NULL,
                               Diagnosis TEXT,
                               Treatment TEXT,
                               Medication TEXT,
                               Allergies TEXT,
                               Notes TEXT,
                               FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
);
INSERT INTO Department (DepartmentName)
VALUES
    ('Cardiology');
INSERT INTO Doctor (FirstName, LastName, Specialization, ContactInfo, DepartmentID, Availability)
VALUES
    ('Miller', 'Erdanovich', 'Cardiologist', '123-456-7890', 1, 'Mon-Fri 9AM-5PM');


INSERT INTO Patient (FirstName, LastName, DOB, Gender, ContactInfo, EmergencyContact, Address)
VALUES
    ('Jane', 'Patient', '1990-01-01', 'Female', '987-654-3210', '123-456-7890', '123 Main St', 'None');

INSERT INTO users (name, email, password, role, reference_id)
VALUES
    ('Erdan', 'doctor', '123', 'doctor', 1),
    ('Erdan', 'patient', '123', 'patient', 1);

INSERT INTO Appointment (PatientID, DoctorID, AppointmentDate, Reason, Status)
VALUES
    (1, 1, '2024-12-15 10:00:00', 'Routine Checkup', 'Scheduled');
INSERT INTO Department (departmentname)
VALUES
    ('Neurology'),
    ('Pediatrics');

FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
INSERT INTO MedicalRecord (PatientID, RecordDate, Diagnosis, Medication, Allergies, Procedures, Notes)
VALUES
    (1, '2024-12-01', 'Hypertension', 'Lisinopril', 'None', 'Blood pressure measurement', 'Patient advised to monitor BP daily'),
    (1, '2024-12-05', 'Common Cold', 'Paracetamol', 'None', 'None', 'Rest and hydration recommended');


