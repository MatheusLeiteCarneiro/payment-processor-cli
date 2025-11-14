package application;

import model.entities.Contract;
import model.exceptions.InstallmentException;
import model.exceptions.PaymentMethodException;
import model.services.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;



public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the payment processor system\n");
        System.out.println("Enter the contract data:");

        int contractNumber;
        while (true){
        System.out.print("Number: ");
        try {
            contractNumber = sc.nextInt();
            ValidationService.verifyContractNumber(contractNumber);
            break;
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        }

        sc.nextLine();
        String payerName;

        while (true) {
            System.out.print("Payer name: ");
            try {
                payerName = sc.nextLine();
                ValidationService.verifyName(payerName);
                break;
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }
        double basePrice;
        while (true) {
            System.out.print("Base price: ");
            try{
                basePrice = sc.nextDouble();
                ValidationService.verifyPrice(basePrice);
                break;
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        Contract contract = new Contract(contractNumber, payerName, basePrice, LocalDate.now());

        int paymentMethod;
        int installmentAmount;
        System.out.println("Select a payment method: ");
        while (true){
            System.out.print("Enter (1-Pix | 2-Debit Card | 3-Credit Card): ");
            try {
                paymentMethod = sc.nextInt();
                ValidationService.paymentValidation(paymentMethod);
                break;
            }
            catch (PaymentMethodException e){
                System.out.println(e.getMessage());
            }

        }
        ProcessingService service;
        switch (paymentMethod){
            case 1:
                System.out.println("(There is no processing fee for pix payment).\n");
                installmentAmount = 1;
                service = new ProcessingService(new PixService());
                service.processInstallments(contract, installmentAmount);
                break;
            case 2:
                System.out.println("(A 1.49% processing fee applies to all debit payments).\n");
                installmentAmount = 1;
                service = new ProcessingService(new DebitService());
                service.processInstallments(contract, installmentAmount);
                break;
            case 3:
                System.out.println("Monthly interest applies after 3 installments with a maximum of 12 installments.");
                System.out.println("(A 2.99% processing fee applies to all credit payments).\n");
                while (true) {
                    System.out.print("Enter the amount of Installments you want: ");
                    try{
                        installmentAmount = sc.nextInt();
                        ValidationService.verifyInstallment(installmentAmount);
                        break;
                    }
                    catch (InstallmentException e){
                        System.out.println(e.getMessage());
                    }

                }
                service = new ProcessingService(new CreditService());
                service.processInstallments(contract, installmentAmount);
                break;
        }
        System.out.println(contract.showAllInstallments());





        sc.close();
    }

}
