package application;

import model.entities.Contract;
import model.exceptions.InstallmentException;
import model.exceptions.PaymentMethodException;
import model.services.CreditService;
import model.services.DebitService;
import model.services.PixService;
import model.services.ProcessingService;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the payment processor system\n");
        System.out.println("Enter the contract data:");
        System.out.print("Number: ");
        int contractNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("Payer name: ");
        String payerName = sc.nextLine();
        System.out.print("Base price: ");
        double basePrice = sc.nextDouble();

        Contract contract = new Contract(contractNumber, payerName, basePrice, LocalDate.now());

        int paymentMethod = 0;
        int installmentAmount = 0;
        System.out.println("Select a payment method: ");
        while (true){
            System.out.print("Digit-(1-pix|2-DebitCard|3-CreditCard): ");
            try {
                paymentMethod = sc.nextInt();
                paymentValidation(paymentMethod);
                break;
            }
            catch (PaymentMethodException e){
                System.out.println(e.getMessage());
            }

        }
        ProcessingService service;
        switch (paymentMethod){
            case 1:
                installmentAmount = 1;
                service = new ProcessingService(new PixService());
                service.processInstallments(contract, installmentAmount);
                break;
            case 2:
                installmentAmount = 1;
                service = new ProcessingService(new DebitService());
                service.processInstallments(contract, installmentAmount);
                break;
            case 3:
                System.out.println("Up to 3 interest-free installments and max of 12 installments.");
                while (true) {
                    System.out.print("Enter the amount of Installments you want: ");
                    try{
                        installmentAmount = sc.nextInt();
                        verifyInstallment(installmentAmount);
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
    public static void verifyInstallment(int installmentAmount){
        if(installmentAmount < 1 || installmentAmount > 12){
            throw new InstallmentException("The installment amount must be between 1 and 12");
        }
    }

    public static void paymentValidation(int method){
        if(method != 1 && method != 2 && method != 3){
            throw new PaymentMethodException("Type a valid method: ");
        }

    }

}
