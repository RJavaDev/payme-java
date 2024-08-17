package uz.dual.paymejava.dto.response.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerformTransactionResult extends BaseResult{

    private Long perform_time = 0L;
    private Integer state;
}
