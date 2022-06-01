package peaksoft.repositories;

import org.springframework.stereotype.Repository;
import peaksoft.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Repository
public class CourseRepository implements MyRepository<Course> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Course save(Course course) {
        entityManager.merge(course);
        return course;
    }

    @Override
    @Transactional
    public List<Course> getAll() {
        return entityManager.createQuery("select co from Course co",Course.class ).getResultList();
    }

    @Override
    @Transactional
    public Course getById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void update(Long id, Course course) {
        Course courseId = getById(id);
        courseId.setCourseName(course.getCourseName());
        courseId.setDuration(course.getDuration());
        entityManager.merge(courseId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(getById(id));

    }

    @Override
    public List<Course> findAll(Long id) {
        return entityManager.createQuery("select c from Course c where c.company.id= :id", Course.class)
                .setParameter("id",id).getResultList();
    }
}
