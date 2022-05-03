package by.voluevich.testjob_inventolabs3.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class PostingDTO {
    private Long id;
    private long uuid;
    private LocalDate contractDate;
    private String userName;
    private boolean isAuthorize;
    private List<ProductItemDTO> productItems;
}
