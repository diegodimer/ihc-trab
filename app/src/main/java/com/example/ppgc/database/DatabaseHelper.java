package com.example.ppgc.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.example.ppgc.database.dbmodel.User;


public class DatabaseHelper{

    // Database Name
    private final String DATABASE_NAME = "IHC";
    private final String DATABASE_ADDRESS = "10.0.2.2";
    private final Integer DATABASE_PORT = 5432;
    private final String DATABASE_USER = "postgres";
    private final String DATABASE_PASSWORD = "changeme";
    private String connectionString = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    private Connection connection;


    public DatabaseHelper() {
        this.connectionString = String.format(this.connectionString, this.DATABASE_ADDRESS, this.DATABASE_PORT, this.DATABASE_NAME);
        connect();
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(connectionString, DATABASE_USER, DATABASE_PASSWORD);
                    status = true;
                    System.out.println("connected:" + status);
                } catch (Exception e) {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection(){
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(connectionString, DATABASE_USER, DATABASE_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

}
