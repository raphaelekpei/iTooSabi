package com.raphael.itoosabi.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RegisterUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String middleName;

    private String password;
    private String email;

    private String phoneNumber;

    private MultipartFile photo;

    private Integer houseNumber;
    private String city;
    private String street;
    private String state;
    private String country;
    private String landmark;
    private String zipCode;


}
