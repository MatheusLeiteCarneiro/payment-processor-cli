# Payment Processor CLI 💸

**Description:** `A Java command-line application that simulates a contract payment processing, featuring different methods to pay with different taxes for each one.`

This Java project was developed to practice and implement core Object-Oriented Programming (OOP) design principles. The primary focus was on:
* Interface-Based Design implemented via Strategy Pattern.
* Robust Custom Exception Handling.

The application uses these patterns to generate a list of installments for a contract, dynamically applying different business rules (fees and interest rates) based on the chosen payment method (Pix, Debit, or Credit).

---

## ✨ Features

* **Contract Registration:** Gathers essential contract data (number, payer name, and base price) from the user.

* **Dynamic Payment Method Selection:** Allows the user to select a payment method (Pix, Debit Card, Credit Card).

* **Dynamic Fee Calculation:**
    * The core feature of the system.
    * Dynamically applies the correct business logic based on the user's choice:
      * **Pix:** Processes the payment as a single installment with no fees.
      * **Debit:** Applies a fixed processing fee (1.49%) and processes as a single installment.
      * **Credit:**  Applies a base fee (2.99%) and monthly compound interest (1.99% a.m.) for plans over 3 installments.
* **Installment Generation:** Generates a final `List<Installment>`, with each installment object containing its calculated due date and final price.
* **Robust Input Validation:**
  * Uses custom checked exceptions (`InstallmentException`, `PaymentMethodException`).
  * This forces the UI layer to enforce business rules, like a 12-installment maximum, in try-catch loops, preventing invalid data from entering the system.
---

## 🛠️ Technologies Used

* **Java 25**
* **IntelliJ IDEA**
* **Java Date-Time API** (`LocalDate`,`DateTimeFormatter`)
* **Git & GitHub** for version control.

---

## 🚀 What I Learned (Concepts Practiced)

This was a complex project that solidified several advanced concepts:

* **Advanced Interface-Based Design (Strategy Pattern):**
    * This was the primary goal. I designed the entire system around the `PaymentService` interface (the Strategy), with concrete implementations like `PixService`, `DebitService`, and `CreditService`.
    * Used Dependency Injection in the `ProcessingService` constructor to inject the chosen `PaymentService`. This makes the logic flexible and swappable.

* **Robust Custom Exception Handling:**
    * This was the second main focus. I implemented custom checked exceptions (`InstallmentException`, `PaymentMethodException`) to represent specific business rule failures.
    * This forced the Program to handle user input errors in try-catch loops, creating a robust separation between error detection (in ValidationService) and error handling (in Program).

* **Clean Architecture:**
    * Successfully separated the application into distinct layers, each with a single responsibility: `application` (UI), `model.entities` (Data), `model.services` (Business Logic), and `model.exceptions` (Errors).

* **Git & Version Control:**
    * Utilized Git to manage the project's development, practicing commits for each new feature.

---

## 🏁 How to Run
1.  Open your **Terminal** (or `Git Bash` on Windows).
2.  Navigate to the directory (folder) where you want to save the project.
3.  **Copy and paste** the following command into your terminal and press **Enter**:

    ```bash
    git clone https://github.com/MatheusLeiteCarneiro/Payment-processor-cli
    ```
    *(This will create a new folder named `Payment-processor-cli` with all the project files.)*

4. Open the project in your Java IDE.
5. Locate and run the `Program.java` file (under `src/application/Program.java`).

---

Author: **Matheus Leite Carneiro**