package com.csgames.exodus.model;

import com.csgames.exodus.util.HibernateUtil;

import javax.persistence.*;

import static com.csgames.exodus.util.HibernateUtil.*;
import static com.csgames.exodus.util.Calculation.currentTimeSeconds;
import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;
import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;


@Entity
@Table(name="User")
public class User {
    public static int TOK_CREATION_TIME = 0;
    public static int TOK_USERNAME = 1;
    public static int TOK_NAME = 2;
    public static int TOK_EMAIL = 3;
    public static int TOK_ROLE = 4;
    public static int TOK_SIGNATURE = 5;

    private long id;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String email;
    private String role;
    private long creationTime;

    public User() {}

    public User(String username, String password, String name, String email) {
        this.salt = gensalt();

        this.username = username;
        this.password = hashpw(password, this.salt);
        this.name = name;
        this.email = email;
        this.role = HibernateUtil.SCUMBAGS;
        this.creationTime = currentTimeSeconds();
    }

    public static String genToken(User user){
        String signature = genSignature(user);
        String token = user.getCreationTime() + PIPE_S + user.getUsername() + PIPE_S + user.getName() + PIPE_S + user.getEmail() + PIPE_S + user.getRole() + PIPE_S + signature;

        return token;
    }

    public static String genSignature(User user) {
        return genSignature(Long.toString(user.getCreationTime()), user.getUsername(), user.getName(), user.getEmail(), user.getRole());
    }

    public static String genSignature(String creationTime, String username, String name, String email, String role) {
        // We don't want the password or the salt in the token to avoid leakage of sensitive data
        final String data = creationTime + username + name + email + role;
        return Integer.toString(data.hashCode());
    }

    public static boolean isTokenValid(String token) {
        String[] parts = token.split(SPLITTER);

        String creationTime = parts[TOK_CREATION_TIME];
        String username = parts[TOK_USERNAME];
        String name = parts[TOK_NAME];
        String email = parts[TOK_EMAIL];
        String role = parts[TOK_ROLE];
        String signature = parts[TOK_SIGNATURE];

        String generatedSignature = genSignature(creationTime, username, name, email, role);
        return signature.equals(generatedSignature);
    }

    public void changePassword(String password) {
        this.password = hashpw(password, this.salt);
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getSalt() {
        return salt;
    }

    private void setSalt(String salt) {
        this.salt = salt;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column
    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}
