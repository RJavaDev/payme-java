package uz.dual.paymejava.dto.response.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CancelTransactionResult extends BaseResult{
    private String transaction;
    private Long cancel_time;
    private Integer state;
}
