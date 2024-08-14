package uz.dual.paymejava.service.impl.payme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.constantes.PayComTransactionState;
import uz.dual.paymejava.dto.response.result.PerformTransactionResult;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.exceptions.TransactionNotFoundException;
import uz.dual.paymejava.model.PerformTransaction;
import uz.dual.paymejava.repository.PayComPaymentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerformTransactionMethod {

    private final PayComPaymentRepository payComPaymentRepository;

    public PerformTransactionResult transaction(PerformTransaction performTransaction){
        Optional<PayComPayment> comPaymentOptional = payComPaymentRepository.getByPayComId(performTransaction.getId());

        if(comPaymentOptional.isEmpty()){
            throw new TransactionNotFoundException("transaction not found!");
        }

        PayComPayment payComPayment = changePayComPaymentState(comPaymentOptional.get());
        PayComPayment saved = payComPaymentRepository.save(payComPayment);

        return getTransactionResponse(saved);
    }

    private PerformTransactionResult getTransactionResponse(PayComPayment saved) {

        PerformTransactionResult result = new PerformTransactionResult();
        result.setTransaction(saved.getId().toString());
        result.setPerform_time(saved.getPerformDateMillisecond());
        result.setState(saved.getPayComState());

        return result;
    }

    private PayComPayment changePayComPaymentState(PayComPayment payComPayment) {
        payComPayment.setPayComState(PayComTransactionState.IMPLEMENTATION.getCode());
        if(payComPayment.getPerformDateMillisecond() == 0)payComPayment.setPerformDateMillisecond(System.currentTimeMillis());
        return payComPayment;
    }
}
