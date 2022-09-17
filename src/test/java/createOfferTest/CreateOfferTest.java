package createOfferTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo_classes.*;


public class CreateOfferTest {

    Response response;
    ObjectMapper objectMapper;
    String socialSecurity;

    @BeforeTest
    public void beforeTest() {
        RestAssured.baseURI = System.getenv("URL");

    }

    @Test
    public void createOffer() throws JsonProcessingException {

        objectMapper = new ObjectMapper();

        Address address = Address.builder()
                .streetAddress("123 Main Street")
                .city("Miami")
                .zip("33125")
                .countryCode("US").build();


        BankInfo bankInfo = BankInfo.builder()
                .bankName("Chase")
                .abaRoutingNumber("123456789")
                .accountNumber("012345789")
                .accountType(1)
                .accountLength(6).build();


        IncomeInfo incomeInfo = IncomeInfo.builder()
                .incomeType("Employment")
                .payrollType("DirectDeposit")
                .payrollFrequency(1)
                .lastPayrollDate("20110516").build();


        PersonalInfo personalInfo = PersonalInfo.builder().
                firstName("Jennifer")
                .lastName("Smith")
                .dateOfBirth("19451009")
                .address(address)
                .mobilePhone("3224340098")
                .homePhone("4523452232").build();


        EmploymentInfo employmentInfo = EmploymentInfo.builder()
                .employerName("ToysRUs")
                .hireDate("20110516").build();

        //..................TC_1 - ACCEPTED LOAN....................


        socialSecurity = "123456780";
        Main_CreateOfferRequest createOfferRequest = Main_CreateOfferRequest.builder()
                .isProduction(false)
                .language("en")
                .currency("USD")
                .campaignId("11-50-newhope")
                .socialSecurityNumber(socialSecurity)
                .leadOfferId("20160912-21EC2020-3AEA-4069-A2DD-08002B30309D")
                .email("test_customer@gmail.com")
                .stateCode("FL")
                .grossMonthlyIncome(100000).personalInfo(personalInfo)
                .bankInfo(bankInfo)
                .incomeInfo(incomeInfo).employmentInfo(employmentInfo).requestedLoanAmount(1500)
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("x-api-key", System.getenv("x-api-key"))
                .body(createOfferRequest)
                .when().post(System.getenv("URL"))
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        String status = response.jsonPath().getString("accepted");
        Assert.assertEquals(status, "true");


        //..................TC_2 - DECLINED LOAN....................


        socialSecurity = "123450000";
        Main_CreateOfferRequest createOfferRequest2 = Main_CreateOfferRequest.builder()
                .isProduction(false)
                .language("en")
                .currency("USD")
                .campaignId("11-50-newhope")
                .socialSecurityNumber(socialSecurity)
                .leadOfferId("20160912-21EC2020-3AEA-4069-A2DD-08002B30309D")
                .email("test_customer@gmail.com")
                .stateCode("FL")
                .grossMonthlyIncome(100000).personalInfo(personalInfo)
                .bankInfo(bankInfo)
                .incomeInfo(incomeInfo).employmentInfo(employmentInfo).requestedLoanAmount(1500)
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("x-api-key", System.getenv("x-api-key"))
                .body(createOfferRequest2)
                .when().post(System.getenv("URL"))
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        status = response.jsonPath().getString("accepted");
        Assert.assertEquals(status, "false");


        //..................TC_3 - MALFORMED REQUEST DATA...........


        Main_CreateOfferRequest createOfferRequest3 = Main_CreateOfferRequest.builder()
                .isProduction(false)
                .currency("USD")
                .campaignId("534535")
                .socialSecurityNumber("------------")
                .leadOfferId("+++++++++")
                .email("O@HOIUHDIHWDL__")
                .stateCode("(&(*^&*&*%^&*%")
                .grossMonthlyIncome(9).personalInfo(personalInfo)
                .bankInfo(bankInfo)
                .incomeInfo(incomeInfo).employmentInfo(employmentInfo).requestedLoanAmount(9)
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("x-api-key", System.getenv("x-api-key"))
                .body(createOfferRequest3)
                .when().post(System.getenv("URL"))
                .then().log().all()
                .extract().response();

        status = response.jsonPath().getString("declined");


    }
}
