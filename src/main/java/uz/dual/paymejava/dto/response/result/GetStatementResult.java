package uz.dual.paymejava.dto.response.result;

import lombok.Getter;
import lombok.Setter;
import uz.dual.paymejava.model.GetStatementElement;

import java.util.List;

@Getter
@Setter
public class GetStatementResult extends BaseResult{

    private List<GetStatementElement> transactions;
}
