package com.example.project_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button jouer,parametres, credits,quitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jouer = (Button) findViewById(R.id.jouer);
        parametres = (Button) findViewById(R.id.parametres);
        credits = (Button) findViewById(R.id.credits);
        quitter = (Button) findViewById(R.id.quitter);

        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJouer();
            }
        });
        parametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openParametres();
            }
        });
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCredits();
            }
        });
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    public void openJouer() {
        Intent intent = new Intent(this, JouerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void openParametres() {
        Intent intent = new Intent(this,ParametresActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void openCredits() {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
