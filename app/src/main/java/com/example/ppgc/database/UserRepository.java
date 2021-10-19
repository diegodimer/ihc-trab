package com.example.ppgc.database;

import android.annotation.SuppressLint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.example.ppgc.database.dbmodel.User;

public class UserRepository {

    private DatabaseHelper db;

    public UserRepository(){

    }

    public UserRepository(DatabaseHelper dbHelper) {
        this.db = dbHelper;
    }

    @SuppressLint("Range")
    public User getUser(String login, String password) {
        User user = null;
        Connection c = db.getExtraConnection();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM IHC.\"User\" WHERE login = " + login + " AND password = " + password);

            if (rs.next()) {
                int id = rs.getInt("id");
                int advisorId = rs.getInt("advisorid");
                String name = rs.getString("name");
                String userLogin = rs.getString("login");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                user = new User(id, userLogin, name, email, phone, advisorId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
