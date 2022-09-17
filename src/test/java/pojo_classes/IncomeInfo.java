package pojo_classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncomeInfo {

    private String incomeType;
    private String payrollType;
    private int payrollFrequency;
    private String lastPayrollDate;
}
