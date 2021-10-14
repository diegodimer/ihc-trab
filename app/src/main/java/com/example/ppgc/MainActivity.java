package com.example.ppgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean verificalogin() {
        //TODO
        //retorna true se id e senha baterem, senão false
        return true;
    }

    public void Send(View view) {
        if (verificalogin()) {
            Intent i = new Intent(getApplicationContext(), teladecaptura.class);
            startActivity(i);
        }
        else{
            //TODO
            //jogar mensagem na tela dizendo que deu ruim
        }

    }

}