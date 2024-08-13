package uz.dual.paymejava.dto.response;

import lombok.Data;

@Data
public class CheckPerformTransactionResponse {

    private ChPTResult result;

    public CheckPerformTransactionResponse (Boolean result){
        ChPTResult ptResult = new ChPTResult();
        ptResult.setAllow(result);
        this.result = ptResult;
    }
}
