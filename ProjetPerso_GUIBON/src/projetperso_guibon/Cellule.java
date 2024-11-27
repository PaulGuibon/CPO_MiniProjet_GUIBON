/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetperso_guibon;

/**
 *
 * @author 33783
 */
public class Cellule {
    private boolean presenceBombe; 
    private boolean devoilee; 
    private int nbBombesAdjacentes; 
    
    public Cellule(boolean presenceBombe, boolean devoilee, int nbBombesAdjacentes) {
        this.presenceBombe = presenceBombe;
        this.devoilee = devoilee;
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }

    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    public void placerBombe() {
        this.presenceBombe = true;
    }

    public void revelerCellule() {
        this.devoilee = true;
    }
    public boolean getDevoilee() {
    return devoilee;
    }
    public void setNbBombesAdjacentes(int nbBombesAdjacentes) {
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }
    @Override
    public String toString() {
        if (!devoilee) {
            return "?"; 
        }
        if (presenceBombe) {
            return "B"; 
        }
        if (nbBombesAdjacentes > 0) {
            return String.valueOf(nbBombesAdjacentes);
        }
        return " "; 
    }
}

