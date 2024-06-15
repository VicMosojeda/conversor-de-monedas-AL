# Conversor de Monedas

Este proyecto es una aplicación de consola que permite convertir cantidades entre diferentes monedas utilizando la API de ExchangeRate.

## Características

- Conversión de monedas entre varias monedas populares.
- Validación de entradas para asegurar que se utilicen códigos de moneda válidos.
- Manejo de errores en la obtención de datos de la API y en las conversiones.

## Tecnologías Utilizadas

- Java
- Gson (para manejar JSON)
- API de ExchangeRate

## Requisitos

- JDK 8 o superior
- Acceso a Internet (para realizar las solicitudes a la API de ExchangeRate)
- Maven (opcional, para la gestión de dependencias)

## Instalación y Configuración

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/tu-usuario/conversor-de-monedas.git
   cd conversor-de-monedas

2. **Configurar el entorno:**

   ```java
   private static final String API_URL = "https://v6.exchangerate-api.com/v6/TU_API_KEY/latest/";

3. **Instalar dependencias:**

   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.mycompany</groupId>
    <artifactId>conversordemonedas</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.8</version>
        </dependency>
    </dependencies>
  </project>

- **Luego instala las dependencias:**

   ```bash
   mvn clean install

4. **Configurar el entorno:**

   ```java
   private static final String API_URL = "https://v6.exchangerate-api.com/v6/TU_API_KEY/latest/";

# Uso

1. **Compila la aplicación:**

   ```bash
   mvn package

2. **Ejecuta la aplicación:**

   ```bash
   java -cp target/conversordemonedas-1.0-SNAPSHOT.jar com.mycompany.conversordemonedas.Conversordemonedas

# Ejemplo de uso

   ```txt
    ********************
    BIENVENIDO AL
    CONVERSOR DE MONEDAS
    ********************
    Seleccione la opción para continuar:
    1. Convertir moneda
    10. Salir
    ______________________________________________________
    Ingrese una opción: 1
    Ingrese el código de la moneda de origen (por ejemplo, USD, EUR, ARS, AUD, BGN, CAD, CHF, CNY, EGP, GBP): USD
    Ingrese el código de la moneda de destino (por ejemplo, USD, EUR, ARS, AUD, BGN, CAD, CHF, CNY, EGP, GBP): EUR
    Ingrese la cantidad a convertir: 100
    100.00 USD equivale a 85.00 EUR