package uz.dual.paymejava.dto.request;

import lombok.Data;
import uz.dual.paymejava.constantes.PaymentType;

@Data
public class Account {

    private Long workshopId;
    private PaymentType paymentType;
}
