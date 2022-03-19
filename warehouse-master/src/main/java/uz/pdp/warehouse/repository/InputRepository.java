package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouse.entity.Input;

@Repository
public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsByCode(String code);
}
