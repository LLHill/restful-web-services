package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.InQueryRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("getAll")
    public List<StudentResponse> getAllStudent(){
        List<Student> studentList = studentService.getAllStudents();
        List<StudentResponse> studentResponselist = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student ->{
            studentResponselist.add(new StudentResponse(student));
        });

        return studentResponselist;
    }

    @PostMapping("create")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest){
        Student student = studentService.createStudent(createStudentRequest);

        return new StudentResponse(student);
    }

    @PutMapping("update")
    public StudentResponse updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest){
        Student student = studentService.updateStudent(updateStudentRequest);
        return new StudentResponse(student);
    }
    @DeleteMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest){
        List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
        List<StudentResponse> studentResponsesList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student ->{
            studentResponsesList.add(new StudentResponse(student));
        });
        return studentResponsesList;
    }

    @GetMapping("get/{firstName}/{lastName}")
    public StudentResponse getByFirstNameAndLastName(@PathVariable("firstName") String firstName,
                                                     @PathVariable("lastName") String lastName){
        Student student = studentService.getByFirstNameAndLastName(firstName, lastName);
        return new StudentResponse(student);
    }

    @GetMapping("getByCity/{city}")
    public List<StudentResponse> getByCity(@PathVariable("city") String city){
        List<Student> studentList = studentService.getByCity(city);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student ->{
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }
}
