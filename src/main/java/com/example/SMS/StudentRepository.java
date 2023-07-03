package com.example.SMS;

import com.example.SMS.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer >{
    public Long countById(Integer id);
}
