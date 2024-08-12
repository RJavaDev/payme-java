package uz.dual.paymejava.model;

import lombok.Data;

@Data
public class CancelTransaction {

    private String id;

    private Integer reason;
}
