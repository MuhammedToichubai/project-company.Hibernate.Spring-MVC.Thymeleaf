package peaksoft.services;

import org.springframework.stereotype.Service;
import peaksoft.models.Student;
import peaksoft.repositories.MyRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Service
public class StudentService implements MyService<Student>{

    private final MyRepository<Student> studentMyRepository;

    public StudentService(MyRepository<Student> studentMyRepository) {
        this.studentMyRepository = studentMyRepository;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return studentMyRepository.save(student);
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        return studentMyRepository.getAll();
    }

    @Override
    @Transactional
    public Student getById(Long id) {
        return studentMyRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id, Student student) {
        studentMyRepository.update(id, student);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        studentMyRepository.delete(id);

    }

    @Override
    @Transactional
    public List<Student> findAll(Long id) {
        return studentMyRepository.findAll(id);
    }
}
