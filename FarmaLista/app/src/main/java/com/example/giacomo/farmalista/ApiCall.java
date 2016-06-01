package com.example.giacomo.farmalista;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Utente on 18/05/2016.
 */
public class ApiCall extends AsyncTask<String,String,String> {
    public static String credenziali = "";
    public static String medicine;
    public static String contatti;
    public UFC11 activity;

    public ApiCall(UFC11 a) {
        this.activity = a;
    }

    public interface AsyncResponse {
        void processFinish(String output);
    }


    public AsyncResponse delegate = null;

    public ApiCall(AsyncResponse delegate){
        this.delegate = delegate;
    }



    @Override
    protected String doInBackground(String... params) {
        String JsonResponse = null;
        String JsonDATA = params[0];
        String indirizzo = params[1];
        String chiamata = params[2];
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(indirizzo);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            // is output buffer writter
            urlConnection.setRequestMethod(chiamata);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            //set headers and method
            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            writer.write(JsonDATA);
            // json data
            writer.close();
            InputStream inputStream = urlConnection.getInputStream();
            //input stream
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine + "\n");
            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
            JsonResponse = buffer.toString();
            //response data
            Log.i("tag",JsonResponse);

            try {
            //send to post execute
                return JsonResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;



        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("tag", "Error closing stream", e);
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("out", "#"+s+"#");
        String prova = s.trim();
        if (prova.equals("test")) {
            activity.change();
        } else {
            Boolean test = s.equals("test");
            Log.d("out", test.toString());
            Toast.makeText(activity, "Non sei registrato", Toast.LENGTH_SHORT).show();
        }
        delegate.processFinish(s);

    }
}
