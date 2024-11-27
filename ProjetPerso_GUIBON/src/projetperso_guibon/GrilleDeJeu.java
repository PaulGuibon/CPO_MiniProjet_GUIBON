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

    // Constructeur de la classe GrilleDeJeu
    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
        matriceCellules = new Cellule[nbLignes][nbColonnes];

        // Initialiser toutes les cellules à non-bombes et non-dévoilées
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule(false, false, 0); // Aucune bombe, non dévoilée, 0 bombes adjacentes

            }
        }
    }

    // Accesseurs en lecture
    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getNbBombes() {
        return nbBombes;
    }

    // Méthode pour placer les bombes aléatoirement dans la grille
    public void placerBombesAleatoirement() {
        Random rand = new Random();
        int bombesPlacees = 0;

        while (bombesPlacees < nbBombes) {
            int i = rand.nextInt(nbLignes);
            int j = rand.nextInt(nbColonnes);

            // Vérifie si la cellule contient déjà une bombe
            if (!matriceCellules[i][j].getPresenceBombe()) {
                matriceCellules[i][j].placerBombe();
                bombesPlacees++;
            }
        }
    }

    // Méthode pour calculer le nombre de bombes adjacentes pour chaque cellule
    public void calculerBombesAdjacentes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int nbBombesAdjacentes = 0;

                    // Vérifie les cellules voisines (8 directions)
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

    // Méthode pour révéler une cellule spécifique
    public void revelerCellule(int ligne, int colonne) {
        // Vérifie si la cellule est déjà dévoilée
        if (matriceCellules[ligne][colonne].getDevoilee()) {
            return;
        }

        // Révèle la cellule
        matriceCellules[ligne][colonne].revelerCellule();

        // Si la cellule contient une bombe, la partie est terminée
        if (matriceCellules[ligne][colonne].getPresenceBombe()) {
            System.out.println("BOOM ! Vous avez révélé une bombe.");
            return;
        }

        // Si la cellule n'a pas de bombes adjacentes, on révèle les cellules adjacentes
        if (matriceCellules[ligne][colonne].getNbBombesAdjacentes() == 0) {
            // Parcours des voisins autour de la cellule
            for (int i = ligne - 1; i <= ligne + 1; i++) {
                for (int j = colonne - 1; j <= colonne + 1; j++) {
                    // Vérifie les limites de la grille et révèle les cellules adjacentes
                    if (i >= 0 && i < nbLignes && j >= 0 && j < nbColonnes) {
                        revelerCellule(i, j);
                    }
                }
            }
        }
    }

    // Méthode pour vérifier si une cellule contient une bombe
    public boolean getPresenceBombe(int i, int j) {
        return matriceCellules[i][j].getPresenceBombe();
    }

    // Méthode pour vérifier si toutes les cellules sûres ont été révélées
    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                // Si la cellule n'a pas de bombe et n'est pas révélée
                if (!matriceCellules[i][j].getPresenceBombe() && !matriceCellules[i][j].getDevoilee()) {
                    return false;
                }
            }
        }
        return true;
    }
}
