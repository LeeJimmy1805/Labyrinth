package com.example.project_1;

import android.os.Bundle;

public class Joueur {
    int vie;
    int gold;
    int niveau;

    Joueur() {
        this.vie = 3;
        this.gold = 10;
        this.niveau = 1;
    }

    public int getGold() {
        return this.gold;
    }
    public int getVie() { return this.vie; }
    public int getNiveau() {
        return this.niveau;
    }

    public void setGold(int gold) {
        this.gold = this.gold + gold;
    }
    public void setVie(int vie) {
        this.vie = this.vie + vie;
    }
    public void setNiveau(){ this.niveau = this.niveau + 1; }
}
