package com.raphael.itoosabi.data.models;

import com.raphael.itoosabi.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User extends Auditable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    private String uploadedPhotoUrl;

    private String verificationCode;
    private Boolean isVerified;
//    private String passwordResetCode;
//    private String passwordResetToken;
//    private String passwordResetTokenExpiry;
//    private String verificationExpiry;
//    private String passwordResetExpiry;
//    private String verificationExpiryDate;
//    private String passwordResetExpiryDate;


    @OneToOne
    private Address address;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    private String confirmPassword;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;


}
