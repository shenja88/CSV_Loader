package by.voluevich.testjob_inventolabs3.repo;

import by.voluevich.testjob_inventolabs3.model.Posting;
import by.voluevich.testjob_inventolabs3.model.Product;
import by.voluevich.testjob_inventolabs3.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductItemRepo extends JpaRepository<ProductItem, Long> {

    boolean existsByProductAndPostingAndPostingDate(Product product, Posting posting, LocalDate postingDate);

    List<ProductItem> getProductItemByPosting(Posting posting);
}
