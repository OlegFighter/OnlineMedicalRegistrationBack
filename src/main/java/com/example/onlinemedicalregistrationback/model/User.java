package com.example.onlinemedicalregistrationback.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    long id;
    String passport;
    String password;
    String policy;

    public User(String passport, String password, String policy) {
        this.passport = passport;
        this.password = password;
        this.policy = policy;
    }
}
