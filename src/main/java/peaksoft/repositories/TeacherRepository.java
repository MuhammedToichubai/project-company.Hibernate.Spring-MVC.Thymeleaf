package peaksoft.repositories;

import org.springframework.stereotype.Repository;
import peaksoft.models.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Repository
public class TeacherRepository implements MyRepository<Teacher> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        return entityManager.merge(teacher);
    }

    @Override
    public List<Teacher> getAll() {

        return entityManager.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    @Override
    public Teacher getById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    @Transactional
    public void update(Long id, Teacher teacher) {
        Teacher teacher1 = getById(id);
        teacher1.setEmail(teacher.getEmail());
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setLastName(teacher.getLastName());
        entityManager.merge(teacher1);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public List<Teacher> findAll(Long id) {

        return entityManager.createQuery("select  teachers from Teacher teachers where" +
                " teachers.course.id=:id", Teacher.class).setParameter("id", id).getResultList();
    }
}
