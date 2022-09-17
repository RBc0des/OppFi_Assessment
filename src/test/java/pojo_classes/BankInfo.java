package pojo_classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankInfo {

    private String bankName;
    private String abaRoutingNumber;
    private String accountNumber;
    private int accountType;
    private int accountLength;
}
