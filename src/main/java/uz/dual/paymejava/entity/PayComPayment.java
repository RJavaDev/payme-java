package uz.dual.paymejava.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.dual.paymejava.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tets_pay_com_payment")
public class PayComPayment extends BaseEntity {

    private String payComId;

    @Column(name = "workshop_id")
    private Long workshopId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop_id", insertable = false, updatable = false)
    private Workshop workshop;

    private BigDecimal amount;

    private long createdDateMillisecond = 0L;

    private long performDateMillisecond = 0L;

    private long cancelDateMillisecond = 0L;

    private LocalDateTime createdDate = LocalDateTime.now();

    private Integer payComState;

    private Integer reason;
}
