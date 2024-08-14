package uz.dual.paymejava.dto.response.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PerformTransactionResult extends BaseResult{

    private Long perform_time = 0L;
    private Integer state;
}
