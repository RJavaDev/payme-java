package uz.dual.paymejava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.dual.paymejava.constantes.PaymentMediator;
import uz.dual.paymejava.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "test_payment")
public class WorkshopPayment extends BaseEntity {

    @Column(name = "workshop_id")
    private Long workshopId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop_id", insertable = false, updatable = false)
    private Workshop workshop;

    @Enumerated(EnumType.STRING)
    private PaymentMediator paymentMediator;

    private BigDecimal amount;

    private Long createdDateMillisecond;

    private LocalDateTime createdDate = LocalDateTime.now();
}
