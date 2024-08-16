package uz.dual.paymejava.service.impl.method;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.dto.response.result.CheckPerformTransactionResult;
import uz.dual.paymejava.exceptions.WorkshopNotFoundException;
import uz.dual.paymejava.model.CheckPerformTransaction;
import uz.dual.paymejava.repository.WorkshopRepository;

@Service
@RequiredArgsConstructor
public class CheckPerformTransactionMethod {

    private final WorkshopRepository workshopRepository;
    public CheckPerformTransactionResult transaction(CheckPerformTransaction checkPerformTransaction){
        PayComValidator.checkAmount(checkPerformTransaction.getAmount());
        workshopRepository.findById(checkPerformTransaction.getAccount().getWorkshopId()).orElseThrow(() -> new WorkshopNotFoundException("workshop not found!"));

        return new CheckPerformTransactionResult(Boolean.TRUE);
    }
}
