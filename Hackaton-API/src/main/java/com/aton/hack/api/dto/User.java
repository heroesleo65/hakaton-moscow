package com.aton.hack.api.dto;

import lombok.Data;

@Data
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String fullName;
}
