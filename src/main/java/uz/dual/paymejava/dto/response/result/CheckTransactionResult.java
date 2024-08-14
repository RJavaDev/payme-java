package uz.dual.paymejava.dto.response.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CheckTransactionResult extends BaseResult{

      private long create_time;

      private long perform_time;

      private long cancel_time;

      private Integer state;

      private Integer reason;
}
