package uz.dual.paymejava.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.dto.response.PayComTransactionResponse;
import uz.dual.paymejava.dto.response.result.BaseResult;
import uz.dual.paymejava.model.*;
import uz.dual.paymejava.dto.request.PayMeRequestMethod;
import uz.dual.paymejava.service.PayMeService;
import uz.dual.paymejava.service.impl.payme.*;

@Service
@RequiredArgsConstructor
public class PayMeServiceImpl implements PayMeService {

    private final ObjectMapper objectMapper;

    private final CheckPerformTransactionMethod checkPerformTransactionMethod;

    private final CreateTransactionMethod createTransactionMethod;

    private final CheckTransactionMethod checkTransactionMethod;

    private final PerformTransactionMethod performTransactionMethod;

    private final CancelTransactionMethod cancelTransactionMethod;

    private final GetStatementMethod getStatementMethod;

    @Override
    public Object payMeControl(PayMeRequestMethod payMeRequest){
        switch (payMeRequest.getMethod()) {
            case CheckPerformTransaction -> {
                CheckPerformTransaction checkPerformTransaction = paramsToObject(payMeRequest.getParams(), CheckPerformTransaction.class);
                return responseWrapper(checkPerformTransactionMethod.transaction(checkPerformTransaction));
            }
            case CreateTransaction -> {
                CreateTransaction createTransaction = paramsToObject(payMeRequest.getParams(), CreateTransaction.class);
                return responseWrapper(createTransactionMethod.transaction(createTransaction));
            }
            case PerformTransaction -> {
                PerformTransaction performTransaction = paramsToObject(payMeRequest.getParams(), PerformTransaction.class);
                return responseWrapper(performTransactionMethod.transaction(performTransaction));
            }
            case CheckTransaction -> {
                CheckTransaction checkTransaction = paramsToObject(payMeRequest.getParams(), CheckTransaction.class);
                return responseWrapper(checkTransactionMethod.transaction(checkTransaction));
            }
            case CancelTransaction -> {
                CancelTransaction cancelTransaction = paramsToObject(payMeRequest.getParams(), CancelTransaction.class);
                return responseWrapper(cancelTransactionMethod.transaction(cancelTransaction));
            }
            case GetStatement -> {
                GetStatement getStatement = paramsToObject(payMeRequest.getParams(), GetStatement.class);
                return responseWrapper(getStatementMethod.transaction(getStatement));
            }
        }
        return null;
    }

    private <T> T paramsToObject(Object obj, Class<T> res){
        return objectMapper.convertValue(obj, res);
    }

    public PayComTransactionResponse responseWrapper(BaseResult result){
        return new PayComTransactionResponse(result);
    }
}
