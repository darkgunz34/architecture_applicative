package fr.architecture.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Commande {

    @Getter
    @Setter
    double montantCommande;

    @Getter
    @Setter
    boolean status;

    @Getter
    LocalDate dateDeLaCommande;

    Commande(double montant,LocalDate date) {
        this.montantCommande = montant;
        this.dateDeLaCommande = date;
    }
}
