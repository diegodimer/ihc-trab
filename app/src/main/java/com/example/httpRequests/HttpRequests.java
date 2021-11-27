package com.example.httpRequests;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequests implements Serializable {
    private String baseUrl;

    public HttpRequests(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUserById(String userId) throws IOException, ExecutionException, InterruptedException {
        return new GetUser().execute(this.baseUrl + "/user/get", userId).get();
    }

    public String loginUser(String userName, String userPassword) throws IOException, ExecutionException, InterruptedException {

        return new LoginUser().execute(this.baseUrl + "/user/login", userName, userPassword).get();
    }

    public void addUserPresence(String userId, String day, String month, String year, String reason, String status) throws IOException {
    }

    public static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

class LoginUser extends AsyncTask<String, Integer, String> {
    // This is run in a background thread
    @Override
    protected String doInBackground(String... params) {
        String userId = "";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put("user", params[1]);
            postDataParams.put("password", params[2]);

            //write parameters to url string
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(HttpRequests.getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            //execute post
            int responseCode = conn.getResponseCode();
            //check response code
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    userId += line;
                }
            } else {
                userId = "";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userId;
    }
}
class GetUser extends AsyncTask<String, Integer, String> {
    // This is run in a background thread
    @Override
    protected String doInBackground(String... params) {
        String user = "";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put("userId", params[1]);

            //write parameters to url string
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(HttpRequests.getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            //execute post
            int responseCode = conn.getResponseCode();
            //check response code
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    user+=line;
                }
            }
            else {
                user="";
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
