package dto;

import java.time.LocalDate;


public class Client extends Person{
    private int code;


    public Client(String nom, String prenom, LocalDate dateNaissance, String telephone, String adress, int code, String email) {
        super(nom, prenom, dateNaissance, telephone, adress, email);
        this.code = code;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
