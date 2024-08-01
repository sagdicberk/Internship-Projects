package com.eventTracking.Task5.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String roles;
}
