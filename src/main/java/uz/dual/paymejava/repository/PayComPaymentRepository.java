package uz.dual.paymejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.entity.WorkshopPayment;

import java.util.Optional;

public interface PayComPaymentRepository extends JpaRepository<PayComPayment, Long> {

    Optional<PayComPayment> getByPayComId(String payComId);
}
