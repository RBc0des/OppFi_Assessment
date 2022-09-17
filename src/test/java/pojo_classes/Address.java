package pojo_classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String streetAddress;
    private String city;
    private String zip;
    private String countryCode;
}
