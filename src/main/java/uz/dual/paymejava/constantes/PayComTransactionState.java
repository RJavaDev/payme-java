package uz.dual.paymejava.constantes;

import lombok.Getter;

@Getter
public enum PayComTransactionState {

    CREATED(1),                         // Created transaction {payCom birinchi to'lovni yaratishdagi holati}
    IMPLEMENTATION(2),                  // Implementation transaction {yaratilgan transacksiyani yakunlanishi}
    CREATED_CANCELED(-1),
    IMPLEMENTATION_CANCELED(-2);

    private final int code;
    PayComTransactionState(int code) {
        this.code = code;
    }
}
