package fr.architecture.entities.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Serveur {

    @Getter
    @Setter
    double chiffreAffaire;

    List<Commande> lstCommand = new ArrayList<>();

    public void ajouterCommande(final Commande commande) {
        this.lstCommand.add(commande);
        updateChiffreAffaire();
    }

    private void updateChiffreAffaire() {
        this.chiffreAffaire = 0;
        for (Commande c: lstCommand) {
            this.chiffreAffaire += c.getMontantCommande();
        }
    }
}
