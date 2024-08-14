package uz.dual.paymejava.dto.response.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckPerformTransactionResult extends BaseResult{
    private Boolean allow;

    public CheckPerformTransactionResult(Boolean allow) {
        this.allow = allow;
    }
}
