package com.example.ppgc;

import androidx.appcompat.app.AppCompatActivity;
import com.example.httpRequests.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> usuarios = new HashMap<String, String>();

    private TextView id;
    private TextView password;

    private HttpRequests httpRequester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        httpRequester = new HttpRequests("https://back287690.herokuapp.com");
    }

    //return userId if user login successfully
    //else return empty string
    public String loginUser() throws IOException, ExecutionException, InterruptedException, JSONException {
        try{
            id = findViewById(R.id.id);
            password = findViewById(R.id.password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String userResponse= httpRequester.loginUser(id.getText().toString(), password.getText().toString());
        JSONObject jsonObject = new JSONObject(userResponse);
        String userId = jsonObject.getString("userId");

        return userId;
    }

    public void Send(View view) throws IOException, ExecutionException, InterruptedException, JSONException {
        String userId = loginUser();
        if (userId != "") {
            String user = httpRequester.getUserById(userId);
            JSONObject jsonObject = new JSONObject(user);
            String name =jsonObject.getString("name");
            String email =jsonObject.getString("email");
            String advisor =jsonObject.getString("advisor");
            String phone =jsonObject.getString("phone");
            Intent i = new Intent(getApplicationContext(), ContainerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("userId", userId);
            bundle.putString("email", email);
            bundle.putString("advisor", advisor);
            bundle.putString("phone", phone);
            bundle.putString("name", name);
            i.putExtras(bundle);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Matricula ou senha inválidas", Toast.LENGTH_LONG).show();
        }

    }

}