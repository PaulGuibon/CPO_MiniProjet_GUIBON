/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetperso_guibon;

/**
 *
 * @author 33783
 */
public class ProjetPerso_GUIBON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        
    } 
    public boolean parc (int a, int b, int c){
            
        GrilleDeJeu grille = new GrilleDeJeu(8,8,2);
        boolean u =true;
        int n = 0;
        for (int i = 0; i<8; i++){
            
            for (int j=0; j<8; j++){
                u = grille.getPresenceBombe(i,j);
                if (u==false){
                    n=n+1;
                    return u;                 
                }
            }
        }
        if (n==0){
            return u;
        }
        return true;
    }
}
