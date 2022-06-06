package peaksoft.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Course;
import peaksoft.models.Group;
import peaksoft.services.MyService;

/**
 * @author Muhammed Toichubai
 */
@Controller
@RequestMapping("/group/{courseId1}")
@AllArgsConstructor
public class GroupController {

    private final MyService<Group> groupService;

    private final MyService<Course> courseService;


    @GetMapping
    public String getAllGroup(@PathVariable("courseId1") Long id, Model model) {
        model.addAttribute("groups", groupService.findAll(id));
        model.addAttribute("groupId", id);
        return "groups/getAllGroup";
    }

    @GetMapping("/addGroup")
    public String saveGroup(Model model) {
        model.addAttribute("group1", new Group());
        return "groups/addGroup";
    }

    @PostMapping("saveGroup")
    public String add(@ModelAttribute("group") Group group) {

        Course course = courseService.getById(group.getCourseId());

        group.setCourse(course);

        
        groupService.save(group);

        return "redirect:/group/{courseId1}" ;
    }

    @DeleteMapping("/deleteGroup/{idDeleteGroup}")
    public String deleteGroup(@PathVariable("idDeleteGroup") Long id) {
        groupService.delete(id);
        return "redirect:/group/{courseId1}";
    }

    @GetMapping("/updateGroup/{id}")
    public String editGroup(Model model, @PathVariable("id") Long id) {
        model.addAttribute("updateGro", groupService.getById(id));
        return "groups/updateGroup";
    }
    @PatchMapping("/{id}")
    public String updateGroup(@ModelAttribute("group1") Group group, @PathVariable("id") Long id) {
        groupService.update(id, group);
        return "redirect:/group/{courseId1}";
    }

}
