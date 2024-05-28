package com.mycompany.conversordemonedas;

import com.mycompany.conversordemonedas.exception.TargetCurrencyNotFoundException;
import com.mycompany.conversordemonedas.model.ApiRequest;

import java.util.Scanner;

public class Conversordemonedas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiRequest aRequest = new ApiRequest();

        while (true) {
            showMenu();
            int option = getUserChoice(scanner);
            if (option == 10) {
                break;
            }

            String fromCurrency = getCurrencyFromUser(scanner, "origen");
            String toCurrency = getCurrencyFromUser(scanner, "destino");
            double amount = getAmount(scanner);

            try {
                double exchangeRate = aRequest.getExchangeRate(fromCurrency, toCurrency);
                double convertedAmount = amount * exchangeRate;
                System.out.println(amount + " " + fromCurrency + " equivale a " + convertedAmount + " " + toCurrency);
            } catch (TargetCurrencyNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Se produjo un error al realizar la conversión: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("********************");
        System.out.println("BIENVENIDO AL");
        System.out.println("CONVERSOR DE MONEDAS");
        System.out.println("********************");
        System.out.println("Seleccione la opción para continuar:");
        System.out.println("1. Convertir moneda");
        System.out.println("10. Salir");
        System.out.println("______________________________________________________");
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.println("Ingrese una opción: ");
        return scanner.nextInt();
    }

    private static String getCurrencyFromUser(Scanner scanner, String tipoMoneda) {
        System.out.println("Ingrese el código de la moneda de " + tipoMoneda + " (por ejemplo, USD, EUR, ARS, AUD , BGN , CAD , CHF , CNY ,EGP, GBP): ");
        return scanner.next().toUpperCase();
    }

    private static double getAmount(Scanner scanner) {
        System.out.println("Ingrese la cantidad a convertir: ");
        return scanner.nextDouble();
    }
}
