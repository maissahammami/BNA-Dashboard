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

    @OneToMany(mappedBy = "requestedBy")
    private List<CreditRequest> creditRequests;

    // Getters, setters, constructors
}
