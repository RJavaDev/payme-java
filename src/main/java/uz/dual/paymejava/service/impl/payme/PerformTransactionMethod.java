package uz.dual.paymejava.service.impl.payme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.constantes.PayComTransactionState;
import uz.dual.paymejava.constantes.PaymentMediator;
import uz.dual.paymejava.dto.response.result.PerformTransactionResult;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.entity.Workshop;
import uz.dual.paymejava.entity.WorkshopPayment;
import uz.dual.paymejava.exceptions.TransactionNotFoundException;
import uz.dual.paymejava.model.PerformTransaction;
import uz.dual.paymejava.repository.PayComPaymentRepository;
import uz.dual.paymejava.repository.WorkshopPaymentRepository;
import uz.dual.paymejava.repository.WorkshopRepository;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerformTransactionMethod {

    private final PayComPaymentRepository payComPaymentRepository;

    private final WorkshopPaymentRepository workshopPaymentRepository;

    private final WorkshopRepository workshopRepository;

    public PerformTransactionResult transaction(PerformTransaction performTransaction){
        Optional<PayComPayment> comPaymentOptional = payComPaymentRepository.getByPayComId(performTransaction.getId());

        if(comPaymentOptional.isEmpty()){
            throw new TransactionNotFoundException("transaction not found!");
        }
        PayComPayment saved = comPaymentOptional.get();
        if(comPaymentOptional.get().getPayComState() ==PayComTransactionState.CREATED.getCode()){
            PayComPayment payComPayment = changePayComPaymentState(comPaymentOptional.get());
            saved = payComPaymentRepository.save(payComPayment);
            WorkshopPayment workshopPayment = generatorNewWorkshopPayment(saved);
            workshopPaymentRepository.save(workshopPayment);
            Optional<Workshop> workshop = workshopRepository.findById(saved.getWorkshopId());
            if(Objects.isNull(workshop.get().getAccountBalance())){
                workshop.get().setAccountBalance(workshopPayment.getAmount());
            }else{
                workshop.get().setAccountBalance(workshop.get().getAccountBalance().add(workshopPayment.getAmount()));
            }
            workshopRepository.save(workshop.get());
        }

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

    private WorkshopPayment generatorNewWorkshopPayment(PayComPayment payment){
        WorkshopPayment workshopPayment = new WorkshopPayment();

        workshopPayment.setWorkshopId(payment.getWorkshopId());
        workshopPayment.setPaymentMediator(PaymentMediator.PAYME);
        workshopPayment.setAmount(payment.getAmount());
        workshopPayment.setCreatedDate(payment.getCreatedDate());
        workshopPayment.setCreatedDateMillisecond(payment.getCreatedDateMillisecond());
        workshopPayment.setPayComTransactionId(payment.getPayComId());

        return workshopPayment;
    }
}
