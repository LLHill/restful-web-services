package com.example.demo.response;

import com.example.demo.entity.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {

    private String subjectName;

    private Double markOBtained;

    SubjectResponse(Subject subject){
        this.subjectName = subject.getSubjectName();
        this.markOBtained = subject.getMarkObtained();
    }
}
