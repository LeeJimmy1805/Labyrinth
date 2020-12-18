package com.example.project_1;

import android.util.Log;
import java.util.Random;
import java.util.ArrayList;

public class Partie {
    int nbEtape = 5;
    int joueurEtape = 0;
    ArrayList<Integer> sequencePossible = new ArrayList<Integer>(4);
    ArrayList<Integer> sequence = new ArrayList<Integer>();

    /**
     * Constructeur qui initialise un tableau de sequencePossible de 4 possiblités (bas, gauche, haut, droite)
     * Initialise le tableau de sequence
     * @param niv qui s'ajoute au nombre d'étape
     */
    public Partie(Joueur j){
        this.sequencePossible.add(2);
        this.sequencePossible.add(4);
        this.sequencePossible.add(6);
        this.sequencePossible.add(8);

        this.nbEtape = nbEtape + j.getNiveau();   // Nombre d'étape qui augmente au fur et a mesure que l'on passe des niveau
        this.sequence = this.cheminAleatoire();
    }

    /**
     * Fonction qui initialise une séquence de chemin aléatoire avec l'impossiblité de revenir sur ces pas
     * Et retourne ce tableau
     */
    public ArrayList cheminAleatoire(){
        ArrayList<Integer> res = new ArrayList<Integer>(this.nbEtape);
        int tmp = -1;

        for (int i=0; i<this.nbEtape; i++){
            if ( i == 0 ){                                          // Pour la première direction sa peut etre n'importe qu'elle direction
                res.add(sequencePossible.get(new Random().nextInt(4)));

                tmp = res.get(i);
            } else {
                if ( tmp == 4 ) {   // par la suite je retire la valeur opposé a celle précédante
                    sequencePossible.remove(new Integer(6));
                    res.add(sequencePossible.get(new Random().nextInt(3)));
                    sequencePossible.add(new Integer(6));   // et l'ajoute après tirage
                    tmp = res.get(i);
                }
                else if ( tmp == 8 ) {
                    sequencePossible.remove(new Integer(2));
                    res.add(sequencePossible.get(new Random().nextInt(3)));
                    sequencePossible.add(new Integer(2));
                    tmp = res.get(i);
                }
                else if ( tmp == 6 ) {
                    sequencePossible.remove(new Integer(4));
                    res.add(sequencePossible.get(new Random().nextInt(3)));
                    sequencePossible.add(new Integer(4));
                    tmp = res.get(i);
                }
                else {
                    sequencePossible.remove(new Integer(8));
                    res.add(sequencePossible.get(new Random().nextInt(3)));
                    sequencePossible.add(new Integer(8));
                    tmp = res.get(i);
                }
            }
        }
        return res;
    }

    /**
     * Fonction qui vérifie les mouvements valide du joueur
     * @return true si @param1 correspond à la séquence et increment joueurEtape
     * @return false sinon
     **/
     public boolean verifMouvement(int mouvement){
        if (this.sequence.get(this.joueurEtape) == mouvement) {
            joueurEtape = joueurEtape + 1;
            return true;
        }
        joueurEtape = 0;

        return false;
     };

    /**
     * Fonction qui retourne true si la partie est finie sinon false
     */
    public boolean finDePartie() {
        if (this.nbEtape == ((this.joueurEtape)-1) ){
            return true;
        }
        return false;
    }
}
