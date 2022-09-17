package pojo_classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalInfo {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Address address;
    private String mobilePhone;
    private String homePhone;
}
