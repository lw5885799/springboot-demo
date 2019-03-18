package com.example.demo.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"id", "password"})
public class User {

    private Integer id;

    private String username;

    private String password;

}
