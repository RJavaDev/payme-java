package uz.dual.paymejava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.dual.paymejava.constantes.WorkshopTariff;
import uz.dual.paymejava.entity.base.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "test_workshop")
public class Workshop extends BaseEntity {

    private String name;

    private Long userId;
    @Column(name = "account_balance", precision = 19, scale = 4)
    private BigDecimal accountBalance = BigDecimal.ONE;

    @Enumerated(EnumType.STRING)
    @Column(length = 32, columnDefinition = "varchar(32) default 'STANDARD'")
    private WorkshopTariff tariff;
}
