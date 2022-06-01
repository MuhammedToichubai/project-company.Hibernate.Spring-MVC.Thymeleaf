package peaksoft.services;

import org.springframework.stereotype.Service;
import peaksoft.models.Student;
import peaksoft.repositories.MyRepository;

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
    public Student save(Student student) {
        return studentMyRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentMyRepository.getAll();
    }

    @Override
    public Student getById(Long id) {
        return studentMyRepository.getById(id);
    }

    @Override
    public void update(Long id, Student student) {
        studentMyRepository.update(id, student);
    }

    @Override
    public void delete(Long id) {
        studentMyRepository.delete(id);

    }

    @Override
    public List<Student> findAll(Long id) {
        return studentMyRepository.findAll(id);
    }
}
