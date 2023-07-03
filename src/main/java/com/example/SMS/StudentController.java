package com.example.SMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class StudentController {
    @Autowired private StudentService service;

    @GetMapping("/students")
    public String showStudentList(Model model){
       List<Student> listStudents=service.listAll();
       model.addAttribute("listStudents",listStudents);
       return "students";
    }
    @GetMapping("/newstudent")
    public String showForm(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("pageTitle","Student Registration");
        return "studentform";
    }

    @PostMapping("/register")
    public String saveStudent(Student student, RedirectAttributes ra){
       service.saveStudent(student);
       ra.addFlashAttribute("message","Student registered successfully");
       return "redirect:/students";
    }
    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable("id")Integer id,Model model, RedirectAttributes ra){
       try{
           Student student=service.get(id);
           model.addAttribute("student",student);
           model.addAttribute("pageTitle","Edit Student(ID: "+id+")");

           return "studentform";
       }catch(StudentNotFoundException e){
           ra.addFlashAttribute("message","Student not registered");
           return "redirect:/students";
       }
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id")Integer id, RedirectAttributes ra){
        try{
           service.delete(id);
           ra.addFlashAttribute("message","Student ID: "+id+" deleted");
        }catch(StudentNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/students";
    }

}
