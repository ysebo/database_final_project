# Database Documentation

## Overview

This documentation provides details about the database schema, including the Entity-Relationship Diagram (ERD) and Relational Model. It serves as a reference for developers and analysts working with the database.

---

## Entity-Relationship Diagram (ERD)

The ERD provides a high-level visual representation of the entities, their attributes, and the relationships between them. Each entity is represented as a rectangle, with lines indicating relationships.

<img src="/src/main/resources/database-erd-diagram.png">


---

## Relational Model

The Relational Model provides a detailed view of the database schema, including tables, primary keys, foreign keys, and constraints.

<img src="/src/main/resources/database-relational-model.png">

---

## Table Descriptions
### Table: `users`
| Column Name | Data Type     | Constraints                                | Description                                     |
|-------------|---------------|--------------------------------------------|-------------------------------------------------|
| id          | SERIAL        | PK, NOT NULL                               | Unique identifier for each user.                |
| FirstName   | VARCHAR(50)   | NOT NULL                                   | First name of the user.                         |
| LastName    | VARCHAR(50)   | NOT NULL                                   | Last name of the user.                          |
| email       | VARCHAR(100)  | UNIQUE, NOT NULL                           | Unique email address of the user.               |
| password    | VARCHAR(255)  | NOT NULL                                   | Encrypted password for authentication.          |
| role        | VARCHAR(20)   | NOT NULL, CHECK(role IN ('doctor', 'patient')) | Role of the user, either `doctor` or `patient`. |

### Table: Department

| Column Name      | Data Type     | Constraints        | Description                         |
|------------------|---------------|--------------------|-------------------------------------|
| DepartmentID     | SERIAL        | PK, NOT NULL       | Unique identifier for the department. |
| DepartmentName   | VARCHAR(100)  | NOT NULL           | Name of the department.             |
### Table: Doctor

| Column Name      | Data Type     | Constraints                                       | Description                                   |
|------------------|---------------|---------------------------------------------------|-----------------------------------------------|
| DoctorID         | SERIAL        | PK, NOT NULL                                      | Unique identifier for each doctor.            |
| FirstName        | VARCHAR(50)   | NOT NULL                                          | First name of the doctor.                     |
| LastName         | VARCHAR(50)   | NOT NULL                                          | Last name of the doctor.                      |
| ContactInfo      | VARCHAR(100)  |                                                   | Contact information for the doctor.           |
| DepartmentID     | INT           | FK, REFERENCES Department (DepartmentID), NULLABLE| Associated department of the doctor.          |
| Availability     | VARCHAR(100)  |

### Table: Patient

| Column Name       | Data Type     | Constraints                                      | Description                                |
|-------------------|---------------|--------------------------------------------------|--------------------------------------------|
| PatientID         | SERIAL        | PK, NOT NULL                                     | Unique identifier for each patient.        |
| FirstName         | VARCHAR(50)   | NOT NULL                                         | First name of the patient.                 |
| LastName          | VARCHAR(50)   | NOT NULL                                         | Last name of the patient.                  |
| DateOfBirth       | VARCHAR(28)   | NOT NULL                                         | Date of birth of the patient.              |
| Gender            | VARCHAR(20)   | CHECK (Gender IN ('Male', 'Female', 'Non-binary')) | Gender of the patient.                     |
| ContactInfo       | VARCHAR(100)  |                                                  | Contact information for the patient.       |
| EmergencyContact  | VARCHAR(100)  |                                                  | Emergency contact details of the patient.  |
| Address           | TEXT          |                                                  | Address of the patient.                    |

### Table: doctor_users

| Column Name | Data Type | Constraints                                                | Description                              |
|-------------|-----------|------------------------------------------------------------|------------------------------------------|
| id          | SERIAL    | PK, NOT NULL                                               | Unique identifier for each record.       |
| user_id     | INT       | FK, NOT NULL, REFERENCES `users(id)` ON DELETE CASCADE     | References the `id` column in `users`.   |
| doctor_id   | INT       | FK, NOT NULL, REFERENCES `Doctor(DoctorID)` ON DELETE CASCADE | References the `DoctorID` column in `Doctor`. |
### Table: patient_users

| Column Name | Data Type | Constraints                                                | Description                                |
|-------------|-----------|------------------------------------------------------------|--------------------------------------------|
| id          | SERIAL    | PK, NOT NULL                                               | Unique identifier for each record.         |
| user_id     | INT       | FK, NOT NULL, REFERENCES `users(id)` ON DELETE CASCADE     | References the `id` column in `users`.     |
| patient_id  | INT       | FK, NOT NULL, REFERENCES `Patient(PatientID)` ON DELETE CASCADE | References the `PatientID` column in `Patient`. |
### Table: Appointment

| Column Name       | Data Type    | Constraints                                              | Description                              |
|-------------------|--------------|----------------------------------------------------------|------------------------------------------|
| AppointmentID     | SERIAL       | PK, NOT NULL                                             | Unique identifier for each appointment.  |
| PatientID         | INT          | FK, NOT NULL, REFERENCES `Patient(PatientID)`           | References the `PatientID` in `Patient`. |
| DoctorID          | INT          | FK, NOT NULL, REFERENCES `Doctor(DoctorID)`             | References the `DoctorID` in `Doctor`.   |
| AppointmentDate   | VARCHAR(50)  | NOT NULL                                                 | Date and time of the appointment.        |
| Reason            | TEXT         |                                                          | Reason or purpose of the appointment.    |
| Status            | VARCHAR(20)  | DEFAULT 'Scheduled', CHECK (Status IN ('Scheduled', 'Completed', 'Cancelled')) | Status of the appointment. |
### Table: MedicalRecord

| Column Name      | Data Type    | Constraints                                             | Description                               |
|------------------|--------------|---------------------------------------------------------|-------------------------------------------|
| MedicalRecordID  | SERIAL       | PK, NOT NULL                                            | Unique identifier for each medical record. |
| PatientID        | INT          | FK, NOT NULL, REFERENCES `Patient(PatientID)` ON DELETE CASCADE | References the `PatientID` in `Patient`.  |
| RecordDate       | VARCHAR(70)  | NOT NULL                                                | Date when the medical record was created.  |
| Diagnosis        | TEXT         |                                                         | Details of the diagnosis.                 |
| Treatment        | TEXT         |                                                         | Description of the prescribed treatment.   |
| Medication       | TEXT         |                                                         | List of prescribed medications.            |
| Allergies        | TEXT         |                                                         | Information about patient allergies.       |
| Notes            | TEXT         |   