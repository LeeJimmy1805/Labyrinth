package com.example.project_1;

import android.graphics.drawable.AnimationDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class JouerActivity extends AppCompatActivity {
    Button retour,next;
    ImageButton gauche,droite,haut,bas;
    ImageView personnage;
    ImageView fond;
    AnimationDrawable deplace;
    AnimationDrawable anim_fond;
    Timer temps = new Timer();
    Joueur j = new Joueur();
    Partie p = new Partie(j);
    int move;
    int vitesse_verticale = 1600;
    int vitesse_horizontale = 1800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);

        retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next = (Button) findViewById(R.id.next);
        next.setVisibility(View.INVISIBLE);

        personnage = (ImageView) findViewById(R.id.joueur);
        personnage.setBackgroundResource(R.drawable.chara_1_down);
        fond = (ImageView) findViewById(R.id.fond);
        fond.setBackgroundResource(R.drawable.x0y0);
        gauche = (ImageButton) findViewById(R.id.Gauche);
        droite = (ImageButton) findViewById(R.id.Droite);
        haut = (ImageButton) findViewById(R.id.Haut);
        bas = (ImageButton) findViewById(R.id.Bas);
        jouer();
    }
    public void jouer(){
        // Cheat code
        Log.d("***************SEQUENCE", " sequence : " + p.sequence.toString());

        /** DEROULEMENT DE LA PARTIE **/
        if (p.finDePartie() == false) {
            // A gauche move = 4
            gauche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    move = 4;
                    if (p.verifMouvement(move)) {
                        aGauche();
                    }
                    else {
                        fond.setBackgroundResource(R.drawable.mort);
                    }
                }
            });
            // En haut move = 8
            haut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    move = 8;
                    if (p.verifMouvement(move)) {
                        aHaut();
                    }
                    else {
                        fond.setBackgroundResource(R.drawable.mort);
                    }
                }
            });
            // A droite move = 6
            droite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    move = 6;
                    if (p.verifMouvement(move)) {
                        aDroite();
                    }
                    else {
                        fond.setBackgroundResource(R.drawable.mort);
                    }
                }
            });
            // En bas move = 2
            bas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    move = 2;
                    if (p.verifMouvement(move)) {
                        aBas();
                    }
                    else {
                        fond.setBackgroundResource(R.drawable.mort);
                    }
                }
            });
        }else {
            this.relance();
        }

    }
    // Incrémentation du Joueur j et instancier une nouvelle Partie p.
    public void relance(){
        next.setVisibility(View.VISIBLE);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j.niveau = j.niveau +1;
                p.joueurEtape = 0;
                p = new Partie(j);
                jouer();
            }
        });
    }

    /** ANIMATION **/
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    // Pour empécher que le joueur appuie sur une autre direction
    public void allDesable(){
        droite.setClickable(false);
        bas.setClickable(false);
        gauche.setClickable(false);
        haut.setClickable(false);

    }
    public void aGauche() {
        this.allDesable();
        this.personnage.setBackgroundResource(R.drawable.anim_gauche);
        this.deplace = (AnimationDrawable) this.personnage.getBackground();
        this.fond.setBackgroundResource(R.drawable.chemin_gauche);
        this.anim_fond = (AnimationDrawable) this.fond.getBackground();
        this.deplace.start();
        this.anim_fond.start();
        this.temps.schedule(new TimerTask() {
            @Override
            public void run() {
                deplace.stop();
                anim_fond.stop();
            }
        },vitesse_horizontale);
        gauche.setClickable(true);
        haut.setClickable(true);
        bas.setClickable(true);

    }
    public void aDroite() {
        this.allDesable();
        this.personnage.setBackgroundResource(R.drawable.anim_droite);
        this.deplace = (AnimationDrawable) this.personnage.getBackground();
        this.fond.setBackgroundResource(R.drawable.chemin_droite);
        this.anim_fond = (AnimationDrawable) this.fond.getBackground();
        this.deplace.start();
        this.anim_fond.start();

        this.temps.schedule(new TimerTask() {
            @Override
            public void run() {
                deplace.stop();
                anim_fond.stop();
            }
        },vitesse_horizontale);
        droite.setClickable(true);
        haut.setClickable(true);
        bas.setClickable(true);
    }
    public void aHaut() {
        this.allDesable();
        this.personnage.setBackgroundResource(R.drawable.anim_haut);
        this.deplace = (AnimationDrawable) this.personnage.getBackground();
        this.fond.setBackgroundResource(R.drawable.chemin_haut);
        this.anim_fond = (AnimationDrawable) this.fond.getBackground();
        this.deplace.start();
        this.anim_fond.start();

        this.temps.schedule(new TimerTask() {
            @Override
            public void run() {
                deplace.stop();
                anim_fond.stop();
            }
        },vitesse_verticale);
        haut.setClickable(true);
        droite.setClickable(true);
        gauche.setClickable(true);
    }
    public void aBas() {
        this.allDesable();
        this.personnage.setBackgroundResource(R.drawable.anim_bas);
        this.deplace = (AnimationDrawable) this.personnage.getBackground();
        this.fond.setBackgroundResource(R.drawable.chemin_bas);
        this.anim_fond = (AnimationDrawable) this.fond.getBackground();
        this.deplace.start();
        this.anim_fond.start();
        this.temps.schedule(new TimerTask() {
            @Override
            public void run() {
                deplace.stop();
                anim_fond.stop();
            }
        },vitesse_verticale);
        bas.setClickable(true);
        droite.setClickable(true);
        gauche.setClickable(true);
    }

}
