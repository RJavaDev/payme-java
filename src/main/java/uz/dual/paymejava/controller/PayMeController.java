package uz.dual.paymejava.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dual.paymejava.config.PayMeConfig;
import uz.dual.paymejava.dto.request.PayMeRequestMethod;
import uz.dual.paymejava.dto.response.result.Error;
import uz.dual.paymejava.dto.response.result.Result;
import uz.dual.paymejava.exceptions.PayMeUnauthorized;
import uz.dual.paymejava.service.PayMeService;

import java.io.IOException;

@RestController
@RequestMapping("/v1/payme")
@RequiredArgsConstructor
public class PayMeController {

    private final PayMeService payMeService;
    private final PayMeConfig payMeConfig;

    @PostMapping("")
    public ResponseEntity<?> payMeMainControl(@RequestBody PayMeRequestMethod payMeRequest, HttpServletRequest request) throws IOException {
        if (!payMeConfig.isUnauthorized(request.getHeader("Authorization"))) {
            throw new PayMeUnauthorized("Unauthorized");
        }
        return ResponseEntity.ok(payMeService.payMeControl(payMeRequest));
    }
}
