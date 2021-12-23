package fr.architecture.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Serveur {

    @Getter
    @Setter
    List<Commande> lstCommandes = new ArrayList<>();

    @Getter
    @Setter
    List<Commande> lstCommandesFlagNonPayer = new ArrayList<>();

    public void ajouterCommande(final Commande commande) {
        this.lstCommandes.add(commande);
        LocalDate dateAvantControleGendarmerie = LocalDate.now().minusDays(15);
        if( dateAvantControleGendarmerie.compareTo(commande.getDateDeLaCommande()) >= 0){
            this.lstCommandesFlagNonPayer.add(commande);
        }
    }

    public List<Commande> transmissionALaGendarmerie(){
        List<Commande> lstCommandesOutput = this.lstCommandesFlagNonPayer;
        this.lstCommandesFlagNonPayer = new ArrayList<>();
        for (Commande c: lstCommandesOutput) {
            this.lstCommandes.remove(c);
        }
        return lstCommandesOutput;
    }

    public double getMontantTotalDesCommande(){
        double montantTotalDesCommandes = 0;
        for (Commande commande : this.lstCommandes) {
            montantTotalDesCommandes += commande.getMontantCommande();
        }
        return montantTotalDesCommandes;
    }
}
