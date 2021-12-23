package fr.architecture.model.factory;

import fr.architecture.model.entities.Commande;

import java.time.LocalDate;

public final class CommandeFactory {

    private CommandeFactory(){

    }

    public static Commande getCommandeAvecMontant(double montant, LocalDate date) {
        return new Commande(montant,date);
    }
}
