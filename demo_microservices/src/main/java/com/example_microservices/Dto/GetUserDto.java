package com.example_microservices.Dto;

import lombok.Data;

@Data
public class GetUserDto {

    private Long userId;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String email;

    private String address;

}
