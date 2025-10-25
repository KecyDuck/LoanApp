package com.example.loanapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDTO {
    private String id;
    private String userName;
    private LocalDate userBOD;
    private String email;
    private String phoneNumber;
    private String address;
}
