package com.mycompany.conversordemonedas.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.conversordemonedas.exception.TargetCurrencyNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiRequest {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/143962e079439b88376c11b2/latest/";

    // Método para obtener las tasas de conversión para una moneda base
    public Map<String, Double> getConversionRates(String baseCurrency) {
        try {
            URL url = new URL(API_URL + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Usar Gson para convertir la respuesta JSON en un objeto JsonObject
                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
                JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");

                // Convertir el JsonObject de tasas de conversión a un Map<String, Double>
                return gson.fromJson(rates, Map.class);
            } else {
                throw new RuntimeException("Error en la respuesta de la API: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las tasas de conversión: " + e.getMessage(), e);
        }
    }

    // Método para obtener la tasa de cambio entre dos monedas específicas
    public double getExchangeRate(String fromCurrency, String toCurrency) throws TargetCurrencyNotFoundException {
        Map<String, Double> conversionRates = getConversionRates(fromCurrency);
        if (conversionRates.containsKey(toCurrency)) {
            return conversionRates.get(toCurrency);
        } else {
            throw new TargetCurrencyNotFoundException("Tipo de moneda no encontrado");
        }
    }
}
