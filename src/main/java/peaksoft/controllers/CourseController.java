package peaksoft.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Company;
import peaksoft.models.Course;
import peaksoft.services.MyService;

/**
 * @author Muhammed Toichubai
 */
@Controller
@AllArgsConstructor
@RequestMapping("/course/{companyId}")
public class CourseController {
    private final MyService<Course> courseService;
    private final MyService<Company> companyService;


    @GetMapping
    public String getAllCourse(@PathVariable("companyId") Long id, Model model) {
        model.addAttribute("courses", courseService.findAll(id));
        model.addAttribute("idCompany", id);
        return "course/getAllCourse";
    }

    @GetMapping("/addNew")
    public String saveCourse(Model model) {
        model.addAttribute("course1", new Course());
        return "course/addCourse";
    }

    @PostMapping("/saveCourse")
    public String add(@ModelAttribute("course") Course course) {
        try {
            course.setCompany(companyService.getById(course.getCompanyId()));
            if (course.getCompanyId() == null) {
                throw new Exception();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        courseService.save(course);
        return "redirect:/course/{companyId}";

    }

    @DeleteMapping("/deleteCourse/{idDeleteCourse}")
    public String deleteCourse(@PathVariable("idDeleteCourse") Long id) {
        courseService.delete(id);
        return "redirect:/course/{companyId}";
    }

    @GetMapping("/updateCourse/{id}")
    public String editCourse(Model model, @PathVariable("id") Long id) {
        Course course = courseService.getById(id);
        model.addAttribute("updateCourse", course);
        return "course/updateCourse";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("updateCourse") Course course, @PathVariable("id") Long id) {
        course.setCompany(courseService.getById(id).getCompany());
        courseService.update(id, course);
        long companyId = companyService.getById(id).getId();
        return "redirect:/course/{companyId}";
    }

}