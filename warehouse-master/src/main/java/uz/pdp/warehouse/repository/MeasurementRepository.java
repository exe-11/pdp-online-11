package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouse.entity.Measurement;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    boolean existsByName(String name);
    List<Measurement> findAllByActiveIsTrue();
    Optional<Measurement> findByIdAndActiveIsTrue(Integer id);
}
