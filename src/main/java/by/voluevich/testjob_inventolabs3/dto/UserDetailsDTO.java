package by.voluevich.testjob_inventolabs3.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDTO {
    private Long id;
    private String accountName;
    private String application;
    private String jobTitle;
    private String departmentTitle;
    private boolean isActive;
}
