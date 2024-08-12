package uz.dual.paymejava.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.constantes.PayMeMethod;
import uz.dual.paymejava.model.*;
import uz.dual.paymejava.dto.PayMeRequestMethod;
import uz.dual.paymejava.service.PayMeService;

@Service
@RequiredArgsConstructor
public class PayMeServiceImpl implements PayMeService {

    private final ObjectMapper objectMapper;

    @Override
    public String payMeControl(PayMeRequestMethod method){
        switch (method.getMethod()) {
            case CheckPerformTransaction -> {
                CheckPerformTransaction checkPerformTransaction = paramsToObject(method.getParams(), CheckPerformTransaction.class);
                return checkPerformTransaction(checkPerformTransaction);
            }
            case CreateTransaction -> {
                CreateTransaction createTransaction = paramsToObject(method.getParams(), CreateTransaction.class);
                return createTransaction(createTransaction);
            }
            case PerformTransaction -> {
                PerformTransaction performTransaction = paramsToObject(method.getParams(), PerformTransaction.class);
                return performTransaction(performTransaction);
            }
            case CheckTransaction -> {
                CheckTransaction checkTransaction = paramsToObject(method.getParams(), CheckTransaction.class);
                return payMeMethod(checkTransaction);
            }
            case CancelTransaction -> {
                CancelTransaction cancelTransaction = paramsToObject(method.getParams(), CancelTransaction.class);
                return cancelTransaction(cancelTransaction);
            }
            case GetStatement -> {
                GetStatement getStatement = paramsToObject(method.getParams(), GetStatement.class);
                return getStatement(getStatement);
            }
        }
        return null;
    }

    public String checkPerformTransaction(CheckPerformTransaction checkPerformTransaction){
        return checkPerformTransaction.toString();
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
}
