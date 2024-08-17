package uz.dual.paymejava.dto.response.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckPerformTransactionResult extends BaseResult{
    private Boolean allow;

    public CheckPerformTransactionResult(Boolean allow) {
        this.allow = allow;
    }
}
