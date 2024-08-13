package uz.dual.paymejava.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.dto.response.CheckPerformTransactionResponse;
import uz.dual.paymejava.entity.Workshop;
import uz.dual.paymejava.exceptions.WorkshopNotFoundException;
import uz.dual.paymejava.exceptions.WrongAmountException;
import uz.dual.paymejava.model.*;
import uz.dual.paymejava.dto.request.PayMeRequestMethod;
import uz.dual.paymejava.repository.PaymentRepository;
import uz.dual.paymejava.repository.WorkshopRepository;
import uz.dual.paymejava.service.PayMeService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PayMeServiceImpl implements PayMeService {

    private final PaymentRepository paymentRepository;

    private final WorkshopRepository workshopRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Object payMeControl(PayMeRequestMethod payMeRequest){
        switch (payMeRequest.getMethod()) {
            case CheckPerformTransaction -> {
                CheckPerformTransaction checkPerformTransaction = paramsToObject(payMeRequest.getParams(), CheckPerformTransaction.class);
                return checkPerformTransaction(checkPerformTransaction);
            }
            case CreateTransaction -> {
                CreateTransaction createTransaction = paramsToObject(payMeRequest.getParams(), CreateTransaction.class);
                return createTransaction(createTransaction);
            }
            case PerformTransaction -> {
                PerformTransaction performTransaction = paramsToObject(payMeRequest.getParams(), PerformTransaction.class);
                return performTransaction(performTransaction);
            }
            case CheckTransaction -> {
                CheckTransaction checkTransaction = paramsToObject(payMeRequest.getParams(), CheckTransaction.class);
                return payMeMethod(checkTransaction);
            }
            case CancelTransaction -> {
                CancelTransaction cancelTransaction = paramsToObject(payMeRequest.getParams(), CancelTransaction.class);
                return cancelTransaction(cancelTransaction);
            }
            case GetStatement -> {
                GetStatement getStatement = paramsToObject(payMeRequest.getParams(), GetStatement.class);
                return getStatement(getStatement);
            }
        }
        return null;
    }

    public Object checkPerformTransaction(CheckPerformTransaction checkPerformTransaction){
        checkAmount(checkPerformTransaction.getAmount());
        workshopRepository.findById(checkPerformTransaction.getAccount().getWorkshopId()).orElseThrow(() -> new WorkshopNotFoundException("workshop not found!"));

        return new CheckPerformTransactionResponse(Boolean.TRUE);
    }

    public String createTransaction(CreateTransaction createTransaction){
        return createTransaction.toString();
    }

    public String performTransaction(PerformTransaction performTransaction){
        return performTransaction.toString();
    }

    public String payMeMethod(CheckTransaction checkTransaction){
        return checkTransaction.toString();
    }

    public String cancelTransaction(CancelTransaction cancelTransaction){
        return cancelTransaction.toString();
    }

    private String getStatement(GetStatement getStatement){
        return getStatement.toString();
    }

    private <T> T paramsToObject(Object obj, Class<T> res){
        return objectMapper.convertValue(obj, res);
    }


    private void checkAmount(Long amount){
        if(Objects.isNull(amount) || amount < 1){
            throw new WrongAmountException("amount is null or less than one");
        }
    }
}
