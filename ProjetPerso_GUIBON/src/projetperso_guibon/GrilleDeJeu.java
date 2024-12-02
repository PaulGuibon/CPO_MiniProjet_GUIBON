/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetperso_guibon;

/**
 *
 * @author 33783
 */
import java.util.Random;

public class GrilleDeJeu {

    private Cellule[][] matriceCellules;
    private int nbLignes;
    private int nbColonnes;
    private int nbBombes;

    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
        matriceCellules = new Cellule[nbLignes][nbColonnes];

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule(false, false, 0); // Aucune bombe, non dévoilée, 0 bombes adjacentes

            }
        }
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getNbBombes() {
        return nbBombes;
    }


    public void placerBombesAleatoirement() {
        Random rand = new Random();
        int bombesPlacees = 0;

        while (bombesPlacees < nbBombes) {
            int i = rand.nextInt(nbLignes);
            int j = rand.nextInt(nbColonnes);


            if (!matriceCellules[i][j].getPresenceBombe()) {
                matriceCellules[i][j].placerBombe();
                bombesPlacees++;
            }
        }
    }

    public void calculerBombesAdjacentes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int nbBombesAdjacentes = 0;

                    for (int x = i - 1; x <= i + 1; x++) {
                        for (int y = j - 1; y <= j + 1; y++) {
                            if (x >= 0 && x < nbLignes && y >= 0 && y < nbColonnes) {
                                if (matriceCellules[x][y].getPresenceBombe()) {
                                    nbBombesAdjacentes++;
                                }
                            }
                        }
                    }
                    matriceCellules[i][j].setNbBombesAdjacentes(nbBombesAdjacentes);
                }
            }
        }
    }

    public void revelerCellule(int ligne, int colonne) {
        // Vérifie si la cellule est déjà dévoilée
        if (matriceCellules[ligne][colonne].getDevoilee()) {
            return;
        }

        matriceCellules[ligne][colonne].revelerCellule();

        if (matriceCellules[ligne][colonne].getPresenceBombe()) {
            System.out.println("BOOM ! Vous avez révélé une bombe.");
            return;
        }

        if (matriceCellules[ligne][colonne].getNbBombesAdjacentes() == 0) {
            for (int i = ligne - 1; i <= ligne + 1; i++) {
                for (int j = colonne - 1; j <= colonne + 1; j++) {
                    if (i >= 0 && i < nbLignes && j >= 0 && j < nbColonnes) {
                        revelerCellule(i, j);
                    }
                }
            }
        }
    }

    public boolean getPresenceBombe(int i, int j) {
        return matriceCellules[i][j].getPresenceBombe();
    }

    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe() && !matriceCellules[i][j].getDevoilee()) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
public String toString() {
    StringBuilder builder = new StringBuilder();

    // Ajouter une bordure supérieure avec les indices des colonnes
    builder.append("    ");
    for (int col = 0; col < nbColonnes; col++) {
        builder.append(col).append(" ");
    }
    builder.append("\n");

    // Ajouter une ligne de séparation
    builder.append("   ");
    for (int col = 0; col < nbColonnes; col++) {
        builder.append("--");
    }
    builder.append("\n");

    // Parcourir la matrice pour lire l'état de chaque cellule
    for (int i = 0; i < nbLignes; i++) {
        // Ajouter l'indice de la ligne
        builder.append(i).append(" | ");
        for (int j = 0; j < nbColonnes; j++) {
            // Appeler la méthode toString() de chaque cellule
            builder.append(matriceCellules[i][j].toString()).append(" ");
        }
        builder.append("\n");
    }

    return builder.toString();
}

}
