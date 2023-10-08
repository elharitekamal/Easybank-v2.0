package dto;

import java.time.LocalDate;
import java.util.List;

public class Compte {

    private int numero;
    private double solde;
    private LocalDate dateCreation;
    private Etat etat;
    private List<Operation> operations;
    private Client client;
    private Employe employe;






    public Compte() {

    }




    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Compte(int numero, double solde, LocalDate dateCreation, Etat etat,  Client client, Employe employe) {
        this.numero = numero;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.etat = etat;
        this.client = client;
        this.employe = employe;
    }



    public enum Etat{
        actif,
        inactif
    }
}
