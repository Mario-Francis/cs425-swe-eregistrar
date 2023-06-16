package com.mariofc.eregistrar.controllers;

import com.mariofc.eregistrar.models.Student;
import com.mariofc.eregistrar.services.StudentService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/students"})
public class StudentsController {
    private StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "")
    public String index(@RequestParam(required = false, defaultValue = "") String searchParam, Model model) {
        var students = studentService.search(searchParam);
        model.addAttribute("students", students);
        model.addAttribute("searchParam", searchParam);

        return "students/index";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        var student = new Student();
        model.addAttribute("student", student);
        return "students/register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute @Valid Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("== Errors ==");
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("error", bindingResult.getAllErrors());
            return "students/register";
        }
        var savedStudent = studentService.create(student);
        System.out.println(savedStudent);
        return "redirect:/students";
    }

    @GetMapping(value = "/{studentId}/edit")
    public String edit(@PathVariable("studentId") Integer studentId, Model model) {
        System.out.println(studentId);
        var studentOpt = studentService.get(studentId);
        if (studentOpt.isPresent()) {
            var student = studentOpt.get();
            model.addAttribute("student", student);
            return "students/edit";
        } else {
            return "redirect:/students";
        }
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute @Valid Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("== Errors ==");
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("error", bindingResult.getAllErrors());
            return "students/edit";
        }
        var savedStudent = studentService.update(student);
        System.out.println(savedStudent);
        return "redirect:/students";
    }

    @GetMapping(value = "/{studentId}/delete")
    public String delete(@PathVariable("studentId") Integer studentId) {
        System.out.println(studentId);
        var studentOpt = studentService.get(studentId);
        if (studentOpt.isPresent()) {
            studentService.delete(studentId);
        }
        return "redirect:/students";
    }
}
