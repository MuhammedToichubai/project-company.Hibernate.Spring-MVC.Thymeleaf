package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Course;
import peaksoft.models.Teacher;
import peaksoft.services.MyService;

/**
 * @author Muhammed Toichubai
 */
@Controller
@RequestMapping("/teacher/{courseId2}")
public class TeacherController {

    private final MyService<Teacher> teacherService;
    private final MyService<Course> courseService;

    @Autowired
    public TeacherController(MyService<Teacher> teacherService, MyService<Course> courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }
    @GetMapping
    public String getAllTeacher(@PathVariable("courseId2") Long id, Model model) {
        model.addAttribute("teachers", teacherService.findAll(id));
        model.addAttribute("courseId", id);
        return "teacher/getTeacher";
    }

    @GetMapping("/addTea")
    public String saveTeacher(Model model) {
        model.addAttribute("teacher1", new Teacher());
        return "teacher/createTeacher";
    }

    @PostMapping("/saveTeacher")
    public String add(@PathVariable("courseId2") Long id,@ModelAttribute("teacher") Teacher teacher) throws Exception {
        if (courseService.getById(id).getTeacher()==null) {
            teacher.setCourse(courseService.getById(teacher.getCourseId()));
            teacherService.save(teacher);
        }else{
            throw new Exception("error one teacher save tru");
        }

        return "redirect:/teacher/{courseId2}";
    }

    @DeleteMapping("/deleteTeacher/{idDeleteTeacher}")
    public String deleteTeacher(@PathVariable("idDeleteTeacher") Long id) {
        teacherService.delete(id);
        return "redirect:/teacher/{courseId2}";
    }
    @GetMapping("/updateTeacher/{id}")
    public String editTeacher(Model model, @PathVariable("id") long id, @PathVariable String courseId2) {
        model.addAttribute("updateTeacher", teacherService.getById(id));
        return "teacher/updateTeacher";
    }

    //
    @PatchMapping("/{id}")
    public String updateTeacher(@ModelAttribute("updateTeacher") Teacher teacher, @PathVariable("id") long id, @PathVariable String courseId2) {
        teacherService.update(id, teacher);
        long courseId = teacherService.getById(id).getCourse().getId();
        return "redirect:/teacher/{courseId2}";
    }
}

