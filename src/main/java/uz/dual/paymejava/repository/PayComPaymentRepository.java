package uz.dual.paymejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.dual.paymejava.entity.PayComPayment;
import uz.dual.paymejava.entity.WorkshopPayment;

import java.util.List;
import java.util.Optional;

public interface PayComPaymentRepository extends JpaRepository<PayComPayment, Long> {

    Optional<PayComPayment> getByPayComId(String payComId);

    @Query(value = "SELECT pay.* FROM tets_pay_com_payment pay WHERE pay.created_date_millisecond >= :from AND pay.created_date_millisecond <= :to AND pay.reason IS NULL", nativeQuery = true)
    List<PayComPayment> getTimeInterval(@Param("from")Long from, @Param("to")Long to);
}
