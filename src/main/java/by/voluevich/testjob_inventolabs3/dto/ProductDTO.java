package by.voluevich.testjob_inventolabs3.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {
    private Long id;
    private String description;
    private String measuringUnits;
}
