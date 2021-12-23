package fr.architecture.model.factory;

import fr.architecture.model.entities.Commande;

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
