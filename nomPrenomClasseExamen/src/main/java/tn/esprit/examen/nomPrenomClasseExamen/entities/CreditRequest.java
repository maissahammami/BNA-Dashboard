package tn.esprit.examen.nomPrenomClasseExamen.entities;

import jakarta.persistence.*;
import jdk.jshell.Snippet;

import java.time.LocalDate;

@Entity
@Table(name = "credit_requests")
public class CreditRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., "Crédit immobilier", "Crédit auto"
    private Double amount;

    private LocalDate requestDate;
    @Enumerated(EnumType.STRING)
    private status status;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User requestedBy;

    public void setRequestDate(LocalDate localDate) {
        this.requestDate = localDate;
    }

    // Getters, setters, constructors
}
