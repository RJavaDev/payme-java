package uz.dual.paymejava.dto.response.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionResult extends BaseResult{

    private Long create_time;
    private Integer state;
}
