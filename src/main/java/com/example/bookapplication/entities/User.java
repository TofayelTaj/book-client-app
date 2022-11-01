package com.example.bookapplication.entities;


import com.example.bookapplication.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


}
