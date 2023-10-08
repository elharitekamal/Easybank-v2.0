package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CompteEpargne extends Compte{

    private  int num_compte;
    private double tauxInteret;




    public CompteEpargne(int numero, double solde, LocalDate dateCreation, Etat etat, Client client, Employe employe, int num_compte, double decouvert) {
        super(numero, solde, dateCreation, etat, client, employe);
        this.num_compte = num_compte;
        this.tauxInteret = decouvert;
    }

    public CompteEpargne(Compte compte, double tauxInteret) {
    }

    public CompteEpargne() {

    }

    public int getNum_compte() {
        return num_compte;
    }

    public void setNum_compte(int num_compte) {
        this.num_compte = num_compte;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
