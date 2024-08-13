package uz.dual.paymejava.model;

import lombok.Data;
import uz.dual.paymejava.dto.request.Account;

@Data
public class CheckPerformTransaction {

    private Long amount;

    private Account account;
}
