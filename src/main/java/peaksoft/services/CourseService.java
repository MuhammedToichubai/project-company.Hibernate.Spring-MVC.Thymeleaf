package peaksoft.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.models.Course;
import peaksoft.repositories.MyRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Service
@AllArgsConstructor
public class CourseService implements MyService<Course> {

    private final MyRepository<Course> courseRepo;

    @Override
    @Transactional
    public Course save(Course course) {
        courseRepo.save(course);
        return course;
    }

    @Override
    @Transactional
    public List<Course> getAll() {
        return courseRepo.getAll();
    }

    @Override
    @Transactional
    public Course getById(Long id) {
        return courseRepo.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id, Course course) {
        courseRepo.update(id, course);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        courseRepo.delete(id);
    }

    @Override
    @Transactional
    public List<Course> findAll(Long id) {
        return courseRepo.findAll(id);
    }
}
