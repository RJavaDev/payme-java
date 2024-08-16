package uz.dual.paymejava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.dual.paymejava.dto.request.Account;
import uz.dual.paymejava.dto.response.result.CheckTransactionResult;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetStatementElement  extends CheckTransactionResult {

    private String id;

    private Account account;

    private Long amount;

    private Long time;
}
