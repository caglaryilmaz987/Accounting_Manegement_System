/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author cagla
 */
public class Converter {

    public double getExchangeRate(String from, String to) throws Exception {
        // Preparing the API key and the URL to be used
        String apiKey = "1e2306fb71466edac4b29974";
        String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + from;

// Creating the URL and opening the connection
        URL url = new URL(url_str);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET"); // I want to fetch the data using the GET method

// Getting the HTTP response code from the API
        int responseCode = con.getResponseCode();

// If the response is successful
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Reading the incoming data line by line
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;

            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine); // Adding each line of the response to the response object
            }
            in.close(); // Closing the reader after finishing the read operation

            // Printing the received response to the console (optional)
            System.out.println("API response: " + response.toString());

            // Converting the response received as a string to a JSON object
            JSONObject jsonResponse = new JSONObject(response.toString());

            // If the "result" part is not "success", throwing an error
            if (!jsonResponse.getString("result").equals("success")) {
                throw new Exception("API Error : " + jsonResponse.getString("error-type"));
            }

            // Accessing the conversion_rates section and retrieving the exchange rates
            JSONObject rates = jsonResponse.getJSONObject("conversion_rates");

            // Checking if the target currency exists in the JSON data
            if (!rates.has(to)) {
                throw new Exception("Target currency not found: " + to);
            }

            // Returning the value of the target currency
            return rates.getDouble(to);
        } else {
            // If the HTTP code is not successful, throwing a general error
            throw new Exception("Could not get exchange rate information. HTTP code: " + responseCode);
        }
    }
}
