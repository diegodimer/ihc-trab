package com.example.ppgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int[] matriculas = {285642, 287691, 303030};
    private int[] senhas = {285642, 287691, 303030};

    TextView id;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean verificalogin() {
        id = findViewById(R.id.id);
        password = findViewById(R.id.password);

        //TODO
        //retorna true se id e senha estao certos, senão false
        for(int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] == Integer.parseInt(id.getText().toString())){
                for(int j = 0; j < senhas.length; j++){
                    if(senhas[j] == Integer.parseInt(password.getText().toString()))
                        return true;
                }
            }
        }
        return false;
    }

    public void Send(View view) {
        if (verificalogin()) {
            Intent i = new Intent(getApplicationContext(), teladecaptura.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Matricula ou senha inválidas", Toast.LENGTH_LONG).show();
        }

    }

}