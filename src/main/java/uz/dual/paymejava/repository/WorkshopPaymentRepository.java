package uz.dual.paymejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dual.paymejava.entity.WorkshopPayment;

public interface WorkshopPaymentRepository extends JpaRepository<WorkshopPayment, Long> {

}
