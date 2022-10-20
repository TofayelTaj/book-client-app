package com.example.bookapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtResponse {

    private String jwtToken;
    private String message;
}
