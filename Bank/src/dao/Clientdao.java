package dao;

import dto.Client;
import dto.Employe;

import java.util.List;
import java.util.Optional;

public interface Clientdao {

    Optional<Client> ajouterClient(Client client);
    int supprimerClient(int code);
    Optional<Client> chercherClient(int code);
    Optional<List<Client>> listClient();
    Optional<Client> chercherClientparTel(String telephone);

    Optional<Client> modifierClient(Client clientModifie);

}
