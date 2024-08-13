package uz.dual.paymejava.service;

import uz.dual.paymejava.dto.request.PayMeRequestMethod;

import java.io.IOException;

public interface PayMeService {

    Object payMeControl(PayMeRequestMethod payMeRequest) throws IOException;
}
