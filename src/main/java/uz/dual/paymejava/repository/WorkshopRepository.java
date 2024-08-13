package uz.dual.paymejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dual.paymejava.entity.Workshop;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
