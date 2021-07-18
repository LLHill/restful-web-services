package com.example.demo.response;

import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class StudentResponse {

    private String email;

    private String firstName;

    private String lastName;

    private String fullname;

    private String street;

    private String city;

    private List<SubjectResponse> subjectLearning;

    public StudentResponse(Student student){
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.fullname = student.getFirstName()  + ' ' + student.getLastName();

        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();

        if (student.getSubjectLearning() != null){
            subjectLearning = new ArrayList<SubjectResponse>();
            for (Subject subject: student.getSubjectLearning()){
                subjectLearning.add(new SubjectResponse(subject));
            }
        }
    }
}
