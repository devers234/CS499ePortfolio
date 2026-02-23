package com.example.danieleversinventoryapp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ScryfallApiHelper {

    public static double fetchCardPrice(String cardName) {
        HttpURLConnection connection = null;

        try {
            String encodedName = URLEncoder.encode(cardName, "UTF-8");
            String apiUrl = "https://api.scryfall.com/cards/named?exact=" + encodedName;

            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.setRequestProperty("User-Agent", "DanielEversInventoryApp/1.0");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            reader.close();

            JSONObject obj = new JSONObject(json.toString());
            JSONObject prices = obj.getJSONObject("prices");

            if (!prices.isNull("usd")) {
                return Double.parseDouble(prices.getString("usd"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return 0.0;
    }
}