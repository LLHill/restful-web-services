package com.example.demo.service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.CreateSubjectRequest;
import com.example.demo.request.InQueryRequest;
import com.example.demo.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest){
        Student student = new Student(createStudentRequest);

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        address = addressRepository.save(address);

        student.setAddress(address);

        List<Subject> subjectList = new ArrayList<Subject>();
        if (createStudentRequest.getSubjectLearning() != null){
            for (CreateSubjectRequest createSubjectRequest :
                    createStudentRequest.getSubjectLearning()){
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarkObtained(createSubjectRequest.getMarkObtained());

                subjectList.add(subject);
            }
        }

        subjectRepository.saveAll(subjectList);
        student.setSubjectLearning(subjectList);
        student = studentRepository.save(student);
        return student;
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest){
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();
        student.setFirstName(updateStudentRequest.getFirstName());
        student = studentRepository.save(student);
        return student;
    }

    public String deleteStudent (long id){
        studentRepository.deleteById(id);
        return "Student has been deleted successfully";
    }

    public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest){
        List<Student> studentList = new ArrayList<Student>();
        Collections.sort(studentRepository.findByFirstNameIn(inQueryRequest.getFirstName()), new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        return studentList;
    }

    public Student getByFirstNameAndLastName(String firstName, String lastName){
        return studentRepository.getByLastNameAndFirstName(firstName, lastName);
    }

    public List<Student> getByCity(String city){
        return studentRepository.findByAddressCity(city);
    }
}
