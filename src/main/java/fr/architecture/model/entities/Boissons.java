package fr.architecture.model.entities;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Boissons extends Commande{

    public Boissons(double montant, LocalDate date) {
        super(montant, date);
    }
}
