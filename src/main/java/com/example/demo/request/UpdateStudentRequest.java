package com.example.demo.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequest {

    @NotNull()
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
