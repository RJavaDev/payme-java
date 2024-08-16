package uz.dual.paymejava.dto.response.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateTransactionResult extends BaseResult{

    private Long create_time;
    private Integer state;
}
