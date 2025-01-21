package com.eath.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private Date expirationDate;
}
