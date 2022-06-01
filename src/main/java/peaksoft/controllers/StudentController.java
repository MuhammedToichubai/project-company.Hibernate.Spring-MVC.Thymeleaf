package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Group;
import peaksoft.models.Student;
import peaksoft.services.MyService;

/**
 * @author Muhammed Toichubai
 */
@Controller
@RequestMapping("student/{groupId}")
public class StudentController {

    private final MyService<Student> studentService;
    private final MyService<Group> groupService;

    @Autowired
    public StudentController(MyService<Student> studentService, MyService<Group> groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String getAllStudent(@PathVariable("groupId") Long id, Model model) {
        model.addAttribute("students", studentService.findAll(id));
        model.addAttribute("groupId", id);
        return "student/students";
    }

    @GetMapping("/addStudent")
    public String saveStudent(Model model) {
        model.addAttribute("student1", new Student());
        return "student/createStudent";
    }

    @PostMapping("/saveStudent")
    public String addStudent(@ModelAttribute("student") Student student) {
        student.setGroup(groupService.getById(student.getGroupId()));
        studentService.save(student);
        return "redirect:/student/{groupId}";
    }

    @GetMapping("/updateStudent/{id}")
    public String editStudent(Model model, @PathVariable("id") Long id) {
        model.addAttribute("updateStu", studentService.getById(id));
        return "student/updateStudents";
    }

    @PatchMapping("/{id}")
    public String updateStudent(@ModelAttribute("student1") Student student, @PathVariable("id") Long id) {
        Long groupId = studentService.getById(id).getGroup().getId();
        studentService.update(id,student);
        return "redirect:/student/ "+groupId;
    }

    @DeleteMapping("/deleteStudent/{idDeleteStudent}")
    public String deleteStudent(@PathVariable("idDeleteStudent") Long id) {
        studentService.delete(id);
        return "redirect:/student/{groupId}";
    }
}

