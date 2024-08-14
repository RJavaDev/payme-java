package uz.dual.paymejava.service.impl.payme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.constantes.PayComTransactionState;
import uz.dual.paymejava.dto.response.result.CreateTransactionResult;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.exceptions.WorkshopNotFoundException;
import uz.dual.paymejava.model.CreateTransaction;
import uz.dual.paymejava.repository.PayComPaymentRepository;
import uz.dual.paymejava.repository.WorkshopRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateTransactionMethod {

    private final PayComPaymentRepository payComPaymentRepository;

    private final WorkshopRepository workshopRepository;

    public CreateTransactionResult transaction(CreateTransaction createTransaction){

        PayComPayment payComPayment = getPayComPayment(createTransaction);
        workshopRepository.findById(payComPayment.getWorkshopId()).orElseThrow(() -> new WorkshopNotFoundException("workshop not found!"));
        Optional<PayComPayment> comPaymentDB = payComPaymentRepository.getByPayComId(payComPayment.getPayComId());
        if(comPaymentDB.isPresent()){
            return convertToCreateTransactionResponse(comPaymentDB.get());
        }
        PayComPayment saved = payComPaymentRepository.save(payComPayment);

        return convertToCreateTransactionResponse(saved);
    }

    public static CreateTransactionResult convertToCreateTransactionResponse(PayComPayment saved) {

        CreateTransactionResult result = new CreateTransactionResult();

        result.setTransaction(String.valueOf(saved.getId()));
        result.setCreate_time(saved.getCreatedDateMillisecond());
        result.setState(saved.getPayComState());

        return result;
    }

    private static PayComPayment getPayComPayment(CreateTransaction createTransaction) {
        PayComPayment payComPayment = new PayComPayment();

        payComPayment.setPayComId(createTransaction.getId());
        payComPayment.setPayComState(PayComTransactionState.CREATED.getCode());
        payComPayment.setAmount(new BigDecimal(createTransaction.getAmount()));
        payComPayment.setCreatedDateMillisecond(createTransaction.getTime());
        if(Objects.isNull(createTransaction.getAccount())){
            throw new WorkshopNotFoundException("Account not found!");
        }
        payComPayment.setWorkshopId(createTransaction.getAccount().getWorkshopId());
        return payComPayment;
    }
}
