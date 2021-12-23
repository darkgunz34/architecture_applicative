package fr.architecture.model.entities;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Nourriture extends Commande{

    public Nourriture(double montant, LocalDate date) {
        super(montant, date);
    }
}
