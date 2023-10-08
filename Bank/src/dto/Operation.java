package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Operation {

    private int numero;
    private Date dateCreation;
    private double montant;
    private TYPE type;
    private Employe employe;
    private Compte compte1;
    private Compte compte2;


    public Operation( Date dateCreation, double montant, TYPE type, Employe employe, Compte compte1, Compte compte2) {
        this.dateCreation = dateCreation;
        this.montant = montant;
        this.type = type;
        this.employe = employe;
        this.compte1 = compte1;
        this.compte2 = compte2;
    }

    public Operation(int operationId, java.sql.Date dateCreation, double montant, TYPE type, Employe employe) {
        this.numero = operationId;
        this.dateCreation = dateCreation;
        this.montant = montant;
        this.type=type;
        this.employe=employe;





    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Compte getCompte1() {
        return compte1;
    }

    public void setCompte1(Compte compte1) {
        this.compte1 = compte1;
    }

    public Compte getCompte2() {
        return compte2;
    }

    public void setCompte2(Compte compte2) {
        this.compte2 = compte2;
    }

    public enum TYPE{
        virement,
        retrait,

        versement
    }
}
