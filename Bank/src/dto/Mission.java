package dto;

public class Mission {

    private int code;
    private String nom;
    private String description;
    private Employe employe;

    public Mission(int code, String nom, String description, Employe employe) {
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.employe = employe;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}
