package com.graalvm;


import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

}
