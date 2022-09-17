package pojo_classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmploymentInfo {
    private String employerName;
    private String hireDate;
}
