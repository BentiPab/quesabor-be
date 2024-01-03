package com.queempanadas.model;

import jakarta.persistence.*;



@Entity(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "LoginUser", query = "from users where username like :username and password like :password"),
        @NamedQuery(name = "FindByUser", query = "from users where username like :username")
})
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", updatable = false, nullable = false)
    private long idUser;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getIdUser() {
        return idUser;
    }
}
