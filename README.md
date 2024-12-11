# Hospital Management System

## Project Overview
The **Hospital Management System** is a Java-based application designed to streamline hospital operations such as managing departments, doctors, patients, appointments, and medical records. The system integrates a PostgreSQL database to ensure data consistency and accessibility, while the application provides a user-friendly interface for efficient operation.

---

## Features
- **Database Integration**: Centralized relational database using PostgreSQL.
- **Role-based Access**: Separate functionalities for doctors and patients.
- **Appointment Management**: Scheduling, updating, and viewing appointments.
- **Medical Records**: Storage and retrieval of patient history and treatment details.
- **Department Management**: Association of doctors with departments for better organization.

---

## Technologies Used
- **Programming Language**: Java
- **Database**: PostgreSQL
- **GUI Framework**: Swing Library (optional for GUI development)
- **Database Connectivity**: JDBC

---

## Getting Started
### Prerequisites
1. Install **Java** (version 8 or above).
2. Install **PostgreSQL** (version 12 or above).
3. IDE of choice (e.g., IntelliJ IDEA, Eclipse).

### Setting Up the Database
1. Create a PostgreSQL database named `hospital`.
2. Connect to the database using:
    ```java
    private static final String url = "jdbc:postgresql://localhost:5432/hospital";
    private static final String username = "postgres";
    private static final String password = "1234";
    ```
    Update these details as per your local configuration.
3. Execute the `DDL.sql` file located in `src/main/resources` to create the required tables and relationships.

### Project Setup
1. Clone this repository:
    ```bash
    git clone https://github.com/ysebo/database_final_project.git
    ```
2. Import the project into your IDE.
3. Add the PostgreSQL JDBC Driver to your project dependencies.
4. Run the application.

---

## Usage
1. **Database Initialization**:
   - Use `DDL.sql` to set up the database structure.

2. **Application Execution**:
   - For GUI: Launch the Swing based application to interact with the system via a graphical interface(using Main.java .

3. **Key Operations**:
   - Add, update, or delete doctors, patients, appointments, and medical records.
   - View department-wise doctor allocation.
   - Retrieve and update patient treatment histories.

---

## Folder Structure
```
Hospital Management System
├── src
│   ├── main
│   │   │ └──├──controller
│   │   │    ├── model
│   │   │    ├── service
|   |   |    ├── viewer
|   |   |    ├── db
│   │   │    └── Main.java
│   │   ├── resources
│   │   │   ├── DDL.sql
│
├── README.md
```
## License
This project is licensed under the [MIT License](LICENSE).
