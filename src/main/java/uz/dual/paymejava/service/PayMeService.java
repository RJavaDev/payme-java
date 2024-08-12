package uz.dual.paymejava.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.dual.paymejava.constantes.PayMeMethod;
import uz.dual.paymejava.dto.PayMeRequestMethod;

import java.io.IOException;

public interface PayMeService {

    String payMeControl(PayMeRequestMethod method) throws IOException;
}
