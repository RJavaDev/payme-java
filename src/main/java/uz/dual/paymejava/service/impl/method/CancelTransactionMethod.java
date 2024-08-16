package uz.dual.paymejava.service.impl.method;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.constantes.PayComTransactionState;
import uz.dual.paymejava.dto.response.result.CancelTransactionResult;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.entity.Workshop;
import uz.dual.paymejava.exceptions.TransactionNotFoundException;
import uz.dual.paymejava.model.CancelTransaction;
import uz.dual.paymejava.repository.PayComPaymentRepository;
import uz.dual.paymejava.repository.WorkshopRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CancelTransactionMethod {

    private final PayComPaymentRepository payComPaymentRepository;

    private final WorkshopRepository workshopRepository;

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
            if(PayComTransactionState.IMPLEMENTATION_CANCELED.getCode() != payment.getPayComState()){
                payment.setPayComState(PayComTransactionState.IMPLEMENTATION_CANCELED.getCode());
                Workshop workshop = workshopRepository.findById(payment.getWorkshopId()).orElseThrow(()-> new TransactionNotFoundException("transaction not found!"));
                workshop.setAccountBalance(workshop.getAccountBalance().subtract(payment.getAmount()));
                workshopRepository.save(workshop);
            }
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
