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
    double chiffreAffaire;

    @Getter
    @Setter
    List<Commande> lstCommandes = new ArrayList<>();

    @Getter
    @Setter
    List<Commande> lstCommandesFlagNonPayer = new ArrayList<>();

    public void ajouterCommande(final Commande commande) {
        /*
        public static void main(String[] args) {
      LocalDate ld1 = LocalDate.parse("2019-02-15");
      LocalDate ld2 = LocalDate.parse("2019-02-12");
      System.out.println("The first LocalDate object is: " + ld1);
      System.out.println("The second LocalDate object is: " + ld2);
      int val = ld1.compareTo(ld2);
      if(val > 0)
         System.out.println("\nThe first LocalDate object is greater than the second LocalDate object");
      else if(val < 0)
         System.out.println("\nThe first LocalDate object is lesser than the second LocalDate object");
      else
         System.out.println("\nThe LocalDate objects are equal");
   }
         */
        this.lstCommandes.add(commande);
        LocalDate dateAvantControleGendarmerie = LocalDate.now().minusDays(15);
        if( dateAvantControleGendarmerie.compareTo(commande.getDateDeLaCommande()) >= 0){
            this.lstCommandesFlagNonPayer.add(commande);
        }
        updateChiffreAffaire();
    }

    private void updateChiffreAffaire() {
        this.chiffreAffaire = 0;
        for (Commande c: lstCommandes) {
            this.chiffreAffaire += c.getMontantCommande();
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
}
