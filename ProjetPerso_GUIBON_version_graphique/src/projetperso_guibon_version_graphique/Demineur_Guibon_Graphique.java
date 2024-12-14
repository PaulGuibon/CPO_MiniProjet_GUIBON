package projetperso_guibon_version_graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @author 33783
 */
public class Demineur_Guibon_Graphique extends javax.swing.JFrame {

    private final int NB_LIGNES = 10;
    private final int NB_COLONNES = 10;
    private final int NB_BOMBES = 15;
    private JButton[][] boutons;
    private boolean[][] estBombe;
    private boolean[][] devoilee;
    private int[][] bombesAdjacentes;

    public Demineur_Guibon_Graphique() {
        initComponents();
        initialiserGrille();
    }
    

    private void initialiserGrille() {
        boutons = new JButton[NB_LIGNES][NB_COLONNES];
        estBombe = new boolean[NB_LIGNES][NB_COLONNES];
        devoilee = new boolean[NB_LIGNES][NB_COLONNES];
        bombesAdjacentes = new int[NB_LIGNES][NB_COLONNES];

        PanneauGrille.setLayout(new GridLayout(NB_LIGNES, NB_COLONNES));
        Random rand = new Random();

        // Placement aléatoire des bombes
        int bombesPlacees = 0;
        while (bombesPlacees < NB_BOMBES) {
            int x = rand.nextInt(NB_LIGNES);
            int y = rand.nextInt(NB_COLONNES);
            if (!estBombe[x][y]) {
                estBombe[x][y] = true;
                bombesPlacees++;
            }
        }

        // Calcul du nombre de bombes adjacentes
        for (int i = 0; i < NB_LIGNES; i++) {
            for (int j = 0; j < NB_COLONNES; j++) {
                if (!estBombe[i][j]) {
                    bombesAdjacentes[i][j] = compterBombesAdjacentes(i, j);
                }
            }
        }

        // Création des boutons et ajout des listeners
        for (int i = 0; i < NB_LIGNES; i++) {
            for (int j = 0; j < NB_COLONNES; j++) {
                boutons[i][j] = new JButton();
                boutons[i][j].setFont(new Font("Arial", Font.BOLD, 14));
                int x = i;
                int y = j;
                boutons[i][j].addActionListener(e -> revelerCellule(x, y));
                PanneauGrille.add(boutons[i][j]);
            }
        }
        for (int i = 0; i < NB_LIGNES; i++) {
    for (int j = 0; j < NB_COLONNES; j++) {
        if (!estBombe[i][j]) {
            bombesAdjacentes[i][j] = compterBombesAdjacentes(i, j);
        }
    }
}

    }
    

   private int compterBombesAdjacentes(int x, int y) {
    int count = 0;
    for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
            int nx = x + dx;
            int ny = y + dy;
            if (nx >= 0 && nx < NB_LIGNES && ny >= 0 && ny < NB_COLONNES && estBombe[nx][ny]) {
                count++;
            }
        }
    }
    return count;
}


    private void revelerCellule(int x, int y) {
        if (devoilee[x][y]) {
            return;
        }

        devoilee[x][y] = true;
        boutons[x][y].setEnabled(false);

        if (estBombe[x][y]) {
            boutons[x][y].setText("B");
            boutons[x][y].setBackground(Color.RED);
            afficherMessagePerdu();
        } 
        else {
            int bombesAdj = bombesAdjacentes[x][y];
        
        if (bombesAdj == 0) {
            for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < NB_LIGNES && ny >= 0 && ny < NB_COLONNES) {
                    revelerCellule(nx, ny);
                    }
                }   
            }
        }
        else if (bombesAdj == 1){
            boutons[x][y].setText( "1");
        }
        else if (bombesAdj == 2){
            boutons[x][y].setText( "2");
        }
        else if (bombesAdj == 3){
            boutons[x][y].setText( "3");
        }
}


        if (verifierVictoire()) {
            afficherMessageGagne();
        }
    }

    private boolean verifierVictoire() {
        for (int i = 0; i < NB_LIGNES; i++) {
            for (int j = 0; j < NB_COLONNES; j++) {
                if (!estBombe[i][j] && !devoilee[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void afficherMessagePerdu() {
        JOptionPane.showMessageDialog(this, "Vous avez perdu !", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void afficherMessageGagne() {
        JOptionPane.showMessageDialog(this, "Félicitations, vous avez gagné !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauGrille = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanneauGrille.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout PanneauGrilleLayout = new javax.swing.GroupLayout(PanneauGrille);
        PanneauGrille.setLayout(PanneauGrilleLayout);
        PanneauGrilleLayout.setHorizontalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PanneauGrilleLayout.setVerticalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Demineur_Guibon_Graphique().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanneauGrille;
    // End of variables declaration//GEN-END:variables
}
