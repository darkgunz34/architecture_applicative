package fr.architecture.entities.factory;

import fr.architecture.entities.model.Commande;

public final class CommandeFactory {

    private CommandeFactory(){

    }

    public static Commande getCommande(){
        return new Commande();
    }

    public static Commande getCommandeAvecMontant(double montant) {
        return new Commande(montant );
    }
}
