package com.example.armada_nasar.budayaid;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BudayaDownloader extends AsyncTask<URL, Void, ArrayList<Budaya>> {

    private String downloadURL = "";
    private ArrayList<Budaya> budayaList = new ArrayList<Budaya>();

    public BudayaDownloader(String url) {
        super();
        this.downloadURL = url;
    }

    @Override
    protected ArrayList<Budaya> doInBackground(URL... urls) {
        // Create URL object
        URL url = createUrl(downloadURL);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            // TODO Handle the IOException
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<Budaya> budayas = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return budayas;
    }

    /**
     * Update the screen with the given earthquake (which was the result of the
     * {@link BudayaDownloader}).
     */
    @Override
    protected void onPostExecute(ArrayList<Budaya> budayas) {
        if (budayas == null) {
            return;
        }
        //updateUi(earthquake);
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e("url process", "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link ArrayList<Budaya>} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
    private ArrayList<Budaya> extractFeatureFromJson(String earthquakeJSON) {
        try {
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            JSONArray budayaArray = baseJsonResponse.getJSONArray("budaya");

            for (int i = 0; i < budayaArray.length(); i++) {
                JSONObject budayaItem = budayaArray.getJSONObject(i);

                budayaList.add(new Budaya(budayaItem.getString("name"), "", budayaItem.getString("google_search_term"), budayaItem.getString("image_url")));
            }
            return budayaList;
        } catch (JSONException e) {
            Log.e("JSON Problem", "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }

    public ArrayList<Budaya> getBudayasList() {
        return budayaList;
    }
}