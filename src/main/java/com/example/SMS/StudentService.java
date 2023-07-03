package com.example.SMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired private StudentRepository repo;

    public List<Student> listAll(){
        return (List<Student>) repo.findAll();
    }

    public void saveStudent(Student student) {
        repo.save(student);
    }

    public Student get(Integer id) throws StudentNotFoundException {
        Optional<Student> result=repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new StudentNotFoundException("ID provided has no match"+id);
    }

    public void delete(Integer id) throws StudentNotFoundException {
        Long count= repo.countById(id);
        if(count==null||count==0){
            throw new StudentNotFoundException("Student with id "+ id +" not registered");
        }
        repo.deleteById(id);
    }
}
