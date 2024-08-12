package uz.dual.paymejava.model;

import lombok.Data;
import uz.dual.paymejava.dto.Account;

@Data
public class CreateTransaction {

    private String id;

    private Long time;

    private Long amount;

    private Account account;
}
