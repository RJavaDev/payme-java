package uz.dual.paymejava.dto.response.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.dual.paymejava.model.GetStatementElement;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetStatementResult extends BaseResult{

    private List<GetStatementElement> transactions;
}
