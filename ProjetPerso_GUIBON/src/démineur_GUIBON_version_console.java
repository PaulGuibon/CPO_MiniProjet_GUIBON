/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author 33783
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class démineur_GUIBON_version_console {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FenetrePrincipale::new);
    }
}

class FenetrePrincipale extends JFrame {
    private static final int TAILLE = 10; // Taille de la grille
    private static final int NOMBRE_MINES = 10; // Nombre de mines
    private JButton[][] boutons;
    private boolean[][] mines;
    private boolean[][] revelees;
    private int[][] chiffres;

    public FenetrePrincipale() {
        setTitle("Démineur");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initialiserJeu();
        setVisible(true);
    }

    private void initialiserJeu() {
        boutons = new JButton[TAILLE][TAILLE];
        mines = new boolean[TAILLE][TAILLE];
        revelees = new boolean[TAILLE][TAILLE];
        chiffres = new int[TAILLE][TAILLE];

        // Création de la grille
        setLayout(new GridLayout(TAILLE, TAILLE));
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                boutons[i][j] = new JButton();
                final int x = i, y = j;
                boutons[i][j].addActionListener(e -> gererClic(x, y));
                add(boutons[i][j]);
            }
        }

        placerMines();
        calculerChiffres();
    }

    private void placerMines() {
        Random random = new Random();
        int minesPlacees = 0;

        while (minesPlacees < NOMBRE_MINES) {
            int x = random.nextInt(TAILLE);
            int y = random.nextInt(TAILLE);
            if (!mines[x][y]) {
                mines[x][y] = true;
                minesPlacees++;
            }
        }
    }

    private void calculerChiffres() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if (mines[i][j]) continue;

                int compteur = 0;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = i + dx, ny = j + dy;
                        if (nx >= 0 && ny >= 0 && nx < TAILLE && ny < TAILLE && mines[nx][ny]) {
                            compteur++;
                        }
                    }
                }
                chiffres[i][j] = compteur;
            }
        }
    }

    private void gererClic(int x, int y) {
        if (revelees[x][y]) return;

        revelees[x][y] = true;
        if (mines[x][y]) {
            revelerMines();
            JOptionPane.showMessageDialog(this, "Boom ! Vous avez perdu.");
            resetJeu();
        } else {
            revelerCase(x, y);
            if (verifierVictoire()) {
                JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez gagné.");
                resetJeu();
            }
        }
    }

    private void revelerCase(int x, int y) {
        boutons[x][y].setEnabled(false);
        boutons[x][y].setText(chiffres[x][y] == 0 ? "" : String.valueOf(chiffres[x][y]));

        if (chiffres[x][y] == 0) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx, ny = y + dy;
                    if (nx >= 0 && ny >= 0 && nx < TAILLE && ny < TAILLE && !revelees[nx][ny]) {
                        gererClic(nx, ny);
                    }
                }
            }
        }
    }

    private void revelerMines() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if (mines[i][j]) {
                    boutons[i][j].setText("M");
                    boutons[i][j].setEnabled(false);
                }
            }
        }
    }

    private boolean verifierVictoire() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if (!mines[i][j] && !revelees[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetJeu() {
        dispose();
        new FenetrePrincipale();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
