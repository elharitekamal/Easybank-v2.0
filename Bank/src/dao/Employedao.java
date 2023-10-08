package dao;
import java.util.List;
import java.util.Optional;

import dto.Employe;

public interface Employedao {


     Optional<Employe> ajouterEmploye(Employe employe);
     int supprimerEmploye(int matricule);
     Optional<Employe> chercherEmploye(int matricule);
     Optional<List<Employe>> listEmploye();
     Optional<Employe> chercherEmployeTel(String telephone );
     Optional<Employe> modifierEmploye(Employe employeModifie);




}
