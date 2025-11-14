package model.services;

import model.exceptions.InstallmentException;
import model.exceptions.PaymentMethodException;

public class ValidationService {

 private ValidationService(){};

    public static void verifyName(String name){
        if(name.isBlank()){
            throw new IllegalArgumentException("The name cannot be blank.");
        }
    }
    public static void verifyContractNumber(int number){
        if(number < 0){
            throw new IllegalArgumentException("The contract number must be equal or greater or than 0.");
        }
    }

    public static void verifyPrice(double basePrice){
        if(basePrice <= 0){
            throw new IllegalArgumentException("The price must be greater than 0.");
        }
    }

    public static void verifyInstallment(int installmentAmount){
        if(installmentAmount < 1 || installmentAmount > 12){
            throw new InstallmentException("The installment amount must be between 1 and 12.");
        }
    }

    public static void paymentValidation(int method){
        if(method != 1 && method != 2 && method != 3){
            throw new PaymentMethodException("Type a valid method: ");
        }

    }


}
