package com.montrehack.app.model;

import com.montrehack.app.util.HibernateUtil;

import javax.persistence.*;

import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;
import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;


@Entity
@Table(name="User")
public class User {

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
        this.creationTime = System.currentTimeMillis();
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
