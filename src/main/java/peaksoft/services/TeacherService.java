package peaksoft.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.models.Teacher;
import peaksoft.repositories.MyRepository;
import peaksoft.repositories.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Service
@AllArgsConstructor
public class TeacherService implements MyService<Teacher> {

    private final MyRepository<Teacher> teacherRepository;

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    public List<Teacher> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Teacher getById(Long id) {
        return teacherRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id, Teacher teacher) {
        teacherRepository.update(id, teacher);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        teacherRepository.delete(id);
    }

    @Override
    @Transactional
    public List<Teacher> findAll(Long id) {
        return teacherRepository.findAll(id);
    }
}
