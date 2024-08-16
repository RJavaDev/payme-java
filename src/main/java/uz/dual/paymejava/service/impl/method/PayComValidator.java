package uz.dual.paymejava.service.impl.method;

import lombok.experimental.UtilityClass;
import uz.dual.paymejava.exceptions.WrongAmountException;

import java.util.Objects;

@UtilityClass
public class PayComValidator {

    public void checkAmount(Long amount){
        if(Objects.isNull(amount) || amount < 1){
            throw new WrongAmountException("amount is null or less than one");
        }
    }
}
