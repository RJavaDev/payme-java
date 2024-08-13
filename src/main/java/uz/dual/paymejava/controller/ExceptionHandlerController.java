package uz.dual.paymejava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.dual.paymejava.dto.response.Error;
import uz.dual.paymejava.dto.response.Result;
import uz.dual.paymejava.exceptions.WorkshopNotFoundException;
import uz.dual.paymejava.exceptions.WrongAmountException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({WrongAmountException.class})
    private ResponseEntity<?> handler(WrongAmountException e) {
        return ResponseEntity.ok(new Result(new Error(-31001, e.getMessage(), "amount")));
    }

    @ExceptionHandler({WorkshopNotFoundException.class})
    private ResponseEntity<?> handler(WorkshopNotFoundException e) {
        return ResponseEntity.ok(new Result(new Error(-31050, e.getMessage(), "workshopId")));
    }
}
