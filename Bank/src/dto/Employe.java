package dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Employe extends Person{

    private int matricule;
    private LocalDate dateRecrutement;
    private List<Mission> missions;
    private List<Compte> comptes;

    public Employe(String nom, String prenom, LocalDate dateNaissance, String telephone, String adress, int matricule, LocalDate dateRecrutement, String email) {
        super(nom, prenom, dateNaissance, telephone, adress, email);
        this.matricule = matricule;
        this.dateRecrutement = dateRecrutement;

    }



    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public LocalDate getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(LocalDate dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }
}
