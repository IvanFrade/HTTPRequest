package com.example.httprequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Interfaccia per gestire le chiamate asincrone
 */
interface iListener {
    void onTaskComplete(String text);
}


public class Downloader implements Runnable {
    public iListener listener;

    public void setListener(iListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            URL url = new URL("https://opentdb.com/api.php?amount=1&type=multiple");

            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String responseText = "";
            String line;

            while((line = bufferedReader.readLine()) != null)
                responseText = responseText.concat(line);

            if (listener != null) {
                listener.onTaskComplete(responseText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
