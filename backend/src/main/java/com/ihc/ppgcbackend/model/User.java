package com.ihc.ppgcbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String phone;
    private String password;
    private String advisor;

    public User(String name, String email, String phone, String password, String advisor) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.advisor = advisor;
    }
}
