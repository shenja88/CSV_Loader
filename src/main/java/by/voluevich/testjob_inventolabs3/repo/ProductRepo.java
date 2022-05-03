package by.voluevich.testjob_inventolabs3.repo;

import by.voluevich.testjob_inventolabs3.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

    boolean existsByDescription(String desc);

    Product getByDescription(String desc);
}
