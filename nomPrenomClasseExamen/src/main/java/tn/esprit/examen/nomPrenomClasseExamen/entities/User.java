package tn.esprit.examen.nomPrenomClasseExamen.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // "ADMIN" or "AGENT"
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @OneToMany(mappedBy = "requestedBy")
    private List<CreditRequest> creditRequests;



    // Getters, setters, constructors
}
