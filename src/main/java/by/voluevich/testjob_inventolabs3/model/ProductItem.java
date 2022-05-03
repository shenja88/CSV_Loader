package by.voluevich.testjob_inventolabs3.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "product_item")
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_date")
    private LocalDate postingDate;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prod_id")
    private Product product;
    private int quantity;
    private double amount;
    private String currency;
    private int itemPosition;
    @ManyToOne(fetch = FetchType.LAZY)
    private Posting posting;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return quantity == that.quantity && Objects.equals(postingDate, that.postingDate) && Objects.equals(product, that.product) && Objects.equals(posting, that.posting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postingDate, product, quantity, posting);
    }
}
