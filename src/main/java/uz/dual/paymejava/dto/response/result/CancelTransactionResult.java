package uz.dual.paymejava.dto.response.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelTransactionResult extends BaseResult{
    private String transaction;
    private Long cancel_time;
    private Integer state;
}
