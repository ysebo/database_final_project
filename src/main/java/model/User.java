package model;

import db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private Long id ;
    private String name;

    private String email;

    private String password;
    private String role;
    public String authenticate(String email, String password) {
        try {
            Database database = new Database();
            Connection connection = database.getConnection();
            String query = "SELECT role FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
