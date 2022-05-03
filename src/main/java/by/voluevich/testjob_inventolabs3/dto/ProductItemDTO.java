package by.voluevich.testjob_inventolabs3.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProductItemDTO {
    private Long id;
    private LocalDate postingDate;
    private ProductDTO product;
    private int quantity;
    private double amount;
    private String currency;
    private int itemPosition;
}
