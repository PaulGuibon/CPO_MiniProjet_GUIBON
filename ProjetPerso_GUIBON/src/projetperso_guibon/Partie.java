/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetperso_guibon;

/**
 *
 * @author 33783
 */
import java.util.Scanner;
public class Partie {


    private GrilleDeJeu grille;
    private int vies;
    private boolean enCours;

    // Initialiser une nouvelle partie
    public void initialiserPartie(int nbLignes, int nbColonnes, int nbBombes, int viesInitiales) {
        grille = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        grille.placerBombesAleatoirement();
        grille.calculerBombesAdjacentes();
        vies = viesInitiales;
        enCours = true;
        System.out.println("Partie initialisée avec une grille de " + nbLignes + "x" + nbColonnes + " et " + nbBombes + " bombes.");
    }

    // Gérer un tour de jeu
    public void tourDeJeu(int ligne, int colonne) {
        if (!enCours) {
            System.out.println("La partie est terminée. Veuillez démarrer une nouvelle partie.");
            return;
        }

        try {
            // Révéler la cellule
            grille.revelerCellule(ligne, colonne);
            System.out.println("Cellule (" + ligne + ", " + colonne + ") révélée.");
            System.out.println(grille);

            // Vérifier si c'est une bombe
            if (grille.getPresenceBombe(ligne, colonne)) {
                vies--;
                System.out.println("Vous avez touché une bombe ! Vies restantes : " + vies);
                if (vies <= 0) {
                    enCours = false;
                    System.out.println("Vous avez perdu. Toutes vos vies sont épuisées.");
                }
            } else if (grille.toutesCellulesRevelees()) {
                enCours = false;
                System.out.println("Félicitations ! Vous avez gagné !");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    // Vérifier si le joueur a gagné
    public boolean verifierVictoire() {
        return grille.toutesCellulesRevelees();
    }

    // Boucle principale pour démarrer la partie
    public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Bienvenue dans le jeu de demineur !");
        while (enCours) {
            System.out.println("Grille actuelle : ");
            System.out.println(grille);
            System.out.println("Veuillez entrer les coordonnées de la cellule à révéler (ligne et colonne) : ");
            System.out.print("Ligne : ");
            int ligne = scanner.nextInt();
            System.out.print("Colonne : ");
            int colonne = scanner.nextInt();
            tourDeJeu(ligne, colonne);
        }
        System.out.println("Merci d'avoir joue !");
        scanner.close();
    }
}

    

