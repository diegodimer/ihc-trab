package com.example.ppgc.database.dbmodel;

public class User {

    public static final String TABLE_NAME = "User";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_LOGIN = "Login";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PHONE = "PhoneNumber";
    public static final String COLUMN_ADVISOR = "AdvisorId";

    private String name;
    private String login;
    private String password;
    private String userEmail;
    private String phoneNumber;
    private int userId;
    private int advisorId;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_LOGIN + " TEXT,"
            + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PHONE + " TEXT,"
            + COLUMN_ADVISOR + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_ADVISOR + ") REFEREMCES Advisor(" + COLUMN_ADVISOR + "));";


    public User(int id, String login, String name, String mail, String phone, int advId) {
        this.userId = id;
        this.name = name;
        this.login = login;
        this.userEmail = mail;
        this.phoneNumber = phone;
        this.advisorId = advId;
    }

    public User() {
    }

}
