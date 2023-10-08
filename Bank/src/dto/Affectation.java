package dto;


import java.time.LocalDate;

public class Affectation {

    private LocalDate dateDebut ;
    private LocalDate dateFin ;
    private Mission mission;
    private Employe employe;

    public Affectation(LocalDate dateDebut, LocalDate dateFin, Mission mission, Employe employe) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.mission = mission;
        this.employe = employe;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}
