package peaksoft.repositories;

import org.springframework.stereotype.Repository;
import peaksoft.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Repository
public class StudentRepository implements MyRepository<Student> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Student save(Student student) {
        entityManager.merge(student);
        return student;
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        return entityManager.createQuery("select s from Student s",Student.class ).getResultList();
    }

    @Override
    @Transactional
    public Student getById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public void update(Long id, Student student) {
        Student student1 = getById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(getById(id));

    }

    @Override
    public List<Student> findAll(Long id) {
        return entityManager.createQuery("select s from Student s where s.group.id= :id", Student.class)
                .setParameter("id",id).getResultList();
    }
}
