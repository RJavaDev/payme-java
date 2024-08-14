package uz.dual.paymejava.service.impl.payme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.dual.paymejava.dto.request.Account;
import uz.dual.paymejava.dto.response.result.GetStatementResult;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.model.GetStatement;
import uz.dual.paymejava.model.GetStatementElement;
import uz.dual.paymejava.repository.PayComPaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetStatementMethod {

    private final PayComPaymentRepository payComPaymentRepository;

    public GetStatementResult transaction(GetStatement getStatement){

        List<PayComPayment> timeIntervalList = payComPaymentRepository.getTimeInterval(getStatement.getFrom(), getStatement.getTo());

        List<GetStatementElement> payComPaymentStream = timeIntervalList.stream().map(this::convertToGetStatementElement).toList();

        GetStatementResult result = new GetStatementResult();
        result.setTransactions(payComPaymentStream);

        return result;
    }

    private GetStatementElement convertToGetStatementElement(PayComPayment payment) {

        GetStatementElement element = new GetStatementElement();
        Account account = new Account();
        account.setWorkshopId(payment.getWorkshopId());
        element.setAccount(account);
        element.setAmount(payment.getAmount().longValue());
        element.setState(payment.getPayComState());
        element.setCreate_time(payment.getCreatedDateMillisecond());
        element.setPerform_time(payment.getPerformDateMillisecond());
        element.setCancel_time(payment.getCancelDateMillisecond());
        element.setTime(payment.getCreatedDateMillisecond());
        element.setTransaction(payment.getId().toString());
        element.setId(payment.getPayComId());
        element.setReason(payment.getReason());

        return element;
    }
}
