package fr.architecture.model.factory;

import fr.architecture.model.entities.Boissons;
import fr.architecture.model.entities.Commande;
import fr.architecture.model.entities.Nourriture;

import java.time.LocalDate;

public final class CommandeFactory {

    private CommandeFactory(){

    }

    public static Commande getCommandeBoissonAvecMontant(double montant, LocalDate date) {
        return new Boissons(montant,date);
    }

    public static Commande getCommandeNourritureAvecMontant(double montant, LocalDate date) {
        return new Nourriture(montant,date);
    }
}
