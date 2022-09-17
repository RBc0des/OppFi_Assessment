package pojo_classes;


import lombok.Builder;
import lombok.Data;

@Data
@Builder


public class Main_CreateOfferRequest {

    private Boolean isProduction;
    private String language;
    private String currency;
    private String campaignId;
    private String socialSecurityNumber;
    private String leadOfferId;
    private String email;
    private String stateCode;
    private int grossMonthlyIncome;
    private PersonalInfo personalInfo;
    private BankInfo bankInfo;
    private IncomeInfo incomeInfo;
    private EmploymentInfo employmentInfo;
    private int requestedLoanAmount;
}
