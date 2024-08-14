package uz.dual.paymejava.service.impl.payme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.constantes.PayComTransactionState;
import uz.dual.paymejava.dto.response.result.CancelTransactionResult;
import uz.dual.paymejava.dto.response.result.PerformTransactionResult;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.exceptions.TransactionNotFoundException;
import uz.dual.paymejava.model.CancelTransaction;
import uz.dual.paymejava.repository.PayComPaymentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CancelTransactionMethod {

    private final PayComPaymentRepository payComPaymentRepository;
    public CancelTransactionResult transaction(CancelTransaction cancelTransaction){
        Optional<PayComPayment> comPaymentDB = payComPaymentRepository.getByPayComId(cancelTransaction.getId());
        if(comPaymentDB.isEmpty()){
            throw new TransactionNotFoundException("transaction not found!");
        }

        changeTransactionState(comPaymentDB.get(), cancelTransaction);
        PayComPayment saved = payComPaymentRepository.save(comPaymentDB.get());

        return convertToPerformTransactionResult(saved);
    }

    private void changeTransactionState(PayComPayment payment, CancelTransaction transaction){
        if(transaction.getReason() == 3){
            payment.setPayComState(PayComTransactionState.CREATED_CANCELED.getCode());
        } else if (transaction.getReason() == 5) {
            payment.setPayComState(PayComTransactionState.IMPLEMENTATION_CANCELED.getCode());
        }
        if(payment.getCancelDateMillisecond() == 0) payment.setCancelDateMillisecond(System.currentTimeMillis());
        payment.setReason(transaction.getReason());
    }

    private CancelTransactionResult convertToPerformTransactionResult(PayComPayment payComPayment){
        CancelTransactionResult result = new CancelTransactionResult();
        result.setTransaction(payComPayment.getId().toString());
        result.setCancel_time(payComPayment.getCancelDateMillisecond());
        result.setState(payComPayment.getPayComState());
        return result;
    }
}
