package uz.dual.paymejava.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayComTransactionResponse {
    private Object result;

    public PayComTransactionResponse(Object result){
        this.result = result;
    }
}
