package com.mycompany.conversordemonedas;

import com.mycompany.conversordemonedas.exception.TargetCurrencyNotFoundException;
import com.mycompany.conversordemonedas.model.ApiRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConversorDeMonedas {

    // Lista de códigos de moneda válidos
    private static final List<String> VALID_CURRENCIES = Arrays.asList("USD", "EUR", "ARS", "AUD", "BGN", "CAD", "CHF", "CNY", "EGP", "GBP");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiRequest aRequest = new ApiRequest();

        boolean exit = false;
        while (!exit) {
            showMenu();
            int option = getUserChoice(scanner);
            switch (option) {
                case 1:
                    convertCurrency(scanner, aRequest);
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
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
        int choice = -1;
        while (choice == -1) {
            System.out.print("Ingrese una opción: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next(); // clear the invalid input
            }
        }
        return choice;
    }

    private static String getValidCurrency(Scanner scanner, String tipoMoneda) {
        String currency;
        while (true) {
            System.out.print("Ingrese el código de la moneda de " + tipoMoneda + " (por ejemplo, USD, EUR, ARS, AUD, BGN, CAD, CHF, CNY, EGP, GBP): ");
            currency = scanner.next().toUpperCase();
            if (VALID_CURRENCIES.contains(currency)) {
                break;
            } else {
                System.out.println("Moneda no válida. Intente nuevamente.");
            }
        }
        return currency;
    }

    private static double getAmount(Scanner scanner) {
        double amount = -1;
        while (amount <= 0) {
            System.out.print("Ingrese la cantidad a convertir: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("La cantidad debe ser mayor a cero.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next(); // clear the invalid input
            }
        }
        return amount;
    }

    private static void convertCurrency(Scanner scanner, ApiRequest aRequest) {
        String fromCurrency = getValidCurrency(scanner, "'origen'");
        String toCurrency = getValidCurrency(scanner, "'destino'");
        double amount = getAmount(scanner);

        try {
            double exchangeRate = aRequest.getExchangeRate(fromCurrency, toCurrency);
            double convertedAmount = amount * exchangeRate;
            System.out.printf("\n%.2f %s equivale a %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
        } catch (TargetCurrencyNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Se produjo un error al realizar la conversión: " + e.getMessage());
        }
    }
}
