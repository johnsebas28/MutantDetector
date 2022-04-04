package com.example.xmenmutants.models;
import javax.persistence.*;

@Entity
@Table(name = "postdna")
public class StatsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;

    private String dna;
    private boolean isMutant;

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }

    public String getDna() {
        return dna;
    }
    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean getIsMutant() {
        return isMutant;
    }
    public void setIsMutant(boolean isMutant) {
        this.isMutant = isMutant;
    }
}
