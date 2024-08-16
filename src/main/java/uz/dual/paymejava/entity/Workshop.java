package uz.dual.paymejava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import uz.dual.paymejava.entity.base.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "test_workshop")
public class Workshop extends BaseEntity {

    private String name;

    private Long userId;
    @Column(name = "account_balance", precision = 19, scale = 6)
    private BigDecimal accountBalance = BigDecimal.ONE;
}
