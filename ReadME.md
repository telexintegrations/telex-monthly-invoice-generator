# Automatic Invoice Generator

The **Automatic Invoice Generator** is a powerful tool designed to help businesses generate invoices efficiently and accurately. It automates the invoice creation process, reducing manual effort and minimizing errors, while offering customization and seamless exporting options.

## Features

- **Extract Data from CSV**: Automatically extracts data of subscribed users from a CSV file.
- **Automated Invoice Generation**: Quickly create invoices with predefined templates.
- **Customizable Email Messaging**: Personalize email messages before sending invoices.
- **Email Integration**: Send invoices directly to clients via email.
- **Telex Notification**: Sends a notification to Telex indicating whether the invoice was successfully sent or not.
- **Spring Boot Support**: Easily run the application using Spring Boot.
- **REST API (Optional)**: Extend functionality with API endpoints for invoice generation.
- **Retry Mechanism (Upcoming Feature)**: A retry feature will be added later for failed invoice deliveries.

## Requirements

To run the Automatic Invoice Generator, ensure you have the following installed:

- **Java Development Kit (JDK) 21**
- **Apache Maven** (for building and managing dependencies)
- **Spring Boot 3.4.2** (for running the application seamlessly)

## Installation

Follow these steps to install and set up the project:

1. Clone the repository:
   ```bash
   git clone https://github.com/telexintegrations/telex-monthly-invoice-generator.git
   ```
2. Navigate to the project directory:
   ```bash
   cd telex-monthly-invoice-generator
   ```
3. Open the project in your preferred editor.
4. Build the project using Maven:
   ```bash
   mvn clean install
   ```
5. Compile the project:
   ```bash
   mvn build
   ```
6. Run the project:
   ```bash
   mvn run
   ```
7. Start the project using the Spring Boot wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

## Project Structure

The project is organized into the following directories and files:

```
telex-monthly-invoice-generator/
│-- src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/main/
│   │   │   │   ├── configs/
│   │   │   │   │   ├── MailConfig.java (Configuration for email services)
│   │   │   │   │   ├── WebConfig.java (Web configuration settings)
│   │   │   │   ├── controller/
│   │   │   │   │   ├── IntegrationJson.java (Handles JSON integrations)
│   │   │   │   │   ├── TelexController.java (Controller for Telex notifications)
│   │   │   │   ├── entity/
│   │   │   │   │   ├── User.java (Entity representing a user)
│   │   │   │   ├── repositories/
│   │   │   │   │   ├── UserRepository.java (Handles database operations for users)
│   │   │   │   ├── servie/
│   │   │   │   │   ├── CsvUserService.java (Processes CSV user data)
│   │   │   │   │   ├── InvoiceService.java (Handles invoice creation and email sending)
│   │   │   │   ├── AutomaticInvoiceGeneratorApplication.java (Main application class)
│   ├── resources/
│   │   ├── application.properties (Spring Boot configuration)
│   │   ├── env.properties (Environment variables and settings)
│   │   ├── IntegratedJson.json (Sample JSON integration file)
│   │   ├── users.csv (Sample user data in CSV format)
│   ├── test/
│   │   ├── java/
│   │   │   ├── com/example/project/
│   │   │   │   ├── AppTest.java (Test cases for the application)
│-- target/ (Generated files after build)
│-- pom.xml (Maven configuration file)
│-- README.md (Project documentation)
```

## Usage

### Running as a Standalone Application

1. Compile and package the project:
   ```bash
   mvn package
   ```
2. Run the application as a JAR file:
   ```bash
   java -jar target/Automatic-Invoice-Generator-1.0-SNAPSHOT.jar
   ```
3. The application will extract data of subscribed users from a CSV file.
4. It will generate an invoice and customize an email message.
5. The invoice will be sent to the client via email.
6. A notification will be sent to Telex indicating whether the invoice was successfully sent or not.
7. The generated invoice will be saved in the `invoices/` directory.

### Running with Spring Boot

1. Start the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
2. Access the application in your browser:
   ```
   http://localhost:8080
   ```



### TESTING THE INTEGRATION

1. Create a REST endpoint exposing the Telex integration JSON.
2. Reference the following integration JSON endpoint: [Telex Integration JSON](https://telex-monthly-invoice-generator.onrender.com/integration.json)
3. On the **Telex Dashboard**, click on **Add New** to create a new integration.
4. Paste the deployed JSON into the integration setup.
5. **Activate the app** in Telex.
6. **Create a channel** to handle invoice processing.
7. Click on **Manage App**.
8. In the **Output Section**, select **Custom** and choose the required channels.
9. Under **Settings**, specify the required cron job for scheduling invoice processing.
10. You can adjust the cron job however you need, but by default, it is set to start at the first second of the first minute of every hour.
11. Check the notifications in the channel created





