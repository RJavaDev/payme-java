package uz.dual.paymejava.service.impl.method;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.dto.response.result.CheckTransactionResult;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.exceptions.TransactionNotFoundException;
import uz.dual.paymejava.model.CheckTransaction;
import uz.dual.paymejava.repository.PayComPaymentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckTransactionMethod {

    private final PayComPaymentRepository payComPaymentRepository;

    public CheckTransactionResult transaction(CheckTransaction checkTransaction){
        Optional<PayComPayment> comPaymentDB = payComPaymentRepository.getByPayComId(checkTransaction.getId());
        if(comPaymentDB.isEmpty()){
            throw new TransactionNotFoundException("transaction not found!");
        }

        return convertToCheckTransactionResponse(comPaymentDB.get());
    }

    private static CheckTransactionResult convertToCheckTransactionResponse(PayComPayment comPaymentDB) {
        CheckTransactionResult result = new CheckTransactionResult();

        result.setTransaction(comPaymentDB.getId().toString());
        result.setState(comPaymentDB.getPayComState());
        result.setCreate_time(comPaymentDB.getCreatedDateMillisecond());
        result.setCancel_time(comPaymentDB.getCancelDateMillisecond());
        result.setPerform_time(comPaymentDB.getPerformDateMillisecond());
        result.setReason(comPaymentDB.getReason());
        return result;
    }
}
