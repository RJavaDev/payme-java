package uz.dual.paymejava.dto.request;

import lombok.Data;
import uz.dual.paymejava.constantes.PayMeMethod;

@Data
public class PayMeRequestMethod {
    private PayMeMethod method;
    private Object params;
}
