package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouse.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByActiveIsTrue();
    Optional<Category> findByIdAndActiveIsTrue(Integer id);
    boolean existsByNameAndParentCategoryId(String name, Integer parentCategory_id);
}
