package dto;

import java.time.LocalDate;

public class CompteCourant extends Compte{

    private  int num_compte;
    private double decouvert;

    public CompteCourant(int numero, double solde, LocalDate dateCreation, Etat etat, Client client, Employe employe, int num_compte, double decouvert) {
        super(numero, solde, dateCreation, etat, client, employe);
        this.num_compte = num_compte;
        this.decouvert = decouvert;
    }

    public CompteCourant(Compte compte, double decouvert) {
    }


    public int getNum_compte() {
        return num_compte;
    }

    public void setNum_compte(int num_compte) {
        this.num_compte = num_compte;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
