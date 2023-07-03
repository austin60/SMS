package com.example.SMS;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value =false)
public class StudentRepoTest {
    @Autowired private StudentRepository repo;

    @Test
    public void testAddNew(){
        Student student=new Student();
        student.setEmail("mark@gmail.com");
        student.setFirstname("Matt");
        student.setLastname("Ogden");
        student.setPassword("54gk@yu");

        Student savedStudent=repo.save(student);

        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);

    }
    @Test
    public  void testListAll(){
        Iterable<Student> students=repo.findAll();
        Assertions.assertThat(students).hasSizeGreaterThan(0);
    }

    @Test
    public void testGet(){
        Integer userId=1;
        Optional<Student> targetStudent=repo.findById(userId);

        Assertions.assertThat(targetStudent).isPresent();
        System.out.println(targetStudent.get());
    }

    @Test
    public void deleteStudent(){
        Integer targetStudent=3;
        repo.deleteById(targetStudent);

        Optional<Student> student=repo.findById(targetStudent);
        Assertions.assertThat(student).isNotPresent();
    }
}
