package com.example.ppgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> usuarios = new HashMap<String, String>();

    TextView id;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //db = new DatabaseHelper();

        usuarios.put("530246", "530246");
        usuarios.put("530247", "530247");
        usuarios.put("584968", "584968");
    }

    public boolean verificalogin() {
        try{
            id = findViewById(R.id.id);
            password = findViewById(R.id.password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (usuarios.containsKey(id.getText().toString())) {
            if (usuarios.get(id.getText().toString()).equals(password.getText().toString())) {
                return  true;
            }
        }
            return false;
    }

    public void Send(View view) {
        if (verificalogin()) {
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Matricula ou senha inválidas", Toast.LENGTH_LONG).show();
        }

    }

}