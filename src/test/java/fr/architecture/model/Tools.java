package fr.architecture.model;

import fr.architecture.model.entities.Serveur;
import fr.architecture.model.factory.CommandeFactory;
import fr.architecture.model.factory.ServeurFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tools {

    private Tools(){

    }

    public static List<Serveur> getLstServeursAvecCommandeEtDateDuJour(int montant,int nombreDeServeur){
        List<Serveur> lstServeurs = new ArrayList<>();

        for(int i = 0;i<nombreDeServeur;i++){
            Serveur server1 = ServeurFactory.getServeur();
            server1.ajouterCommande(CommandeFactory.getCommandeBoissonAvecMontant(montant, LocalDate.now()));
            lstServeurs.add(server1);
        }
        return lstServeurs;
    }
}
