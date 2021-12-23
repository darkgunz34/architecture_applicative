package fr.architecture.entities.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Commande {

    @Getter
    @Setter
    double montantCommande;

    public Commande(double montant) {
        this.montantCommande = montant;
    }
}
