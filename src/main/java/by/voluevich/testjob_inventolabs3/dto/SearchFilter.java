package by.voluevich.testjob_inventolabs3.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class SearchFilter {
    private LocalDate start;
    private LocalDate end;
    private boolean isAuthorize;
}
