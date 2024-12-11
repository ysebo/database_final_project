package model;

import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private int userId;
    private String role;
    public User(int userId, String role) {
        this.userId = userId;
        this.role = role;
    }
    public User() {
        this.userId = 0;
        this.role = "";
    }
    public User authenticate(String email, String password) {
        try {
            Database database = new Database();
            Connection connection = database.getConnection();
            String query = "SELECT id, role FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String role = resultSet.getString("role");
                if (role.equals("doctor")) {
                    return new User(userId, "doctor");
                } else if (role.equals("patient")) {
                    return new User(userId, "patient");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }
    public String setRole(String role) {
        return role;
    }
}
