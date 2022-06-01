package peaksoft.repositories;

import org.springframework.stereotype.Repository;
import peaksoft.models.Course;
import peaksoft.models.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Repository
@Transactional
public class GroupRepository implements MyRepository<Group>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Group save(Group group) {
        entityManager.merge(group);
        return group;
    }

    @Override
    @Transactional
    public List<Group> getAll() {
        return entityManager.createQuery("select cr from Group cr",Group.class ).getResultList();
    }

    @Override
    @Transactional
    public Group getById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    @Transactional
    public void update(Long id, Group group) {
        Group courseId = getById(id);
        courseId.setGroupName(group.getGroupName());
        courseId.setDateOfStart(group.getDateOfStart());
        courseId.setDateOfFinish(group.getDateOfFinish());
        entityManager.merge(courseId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("delete from Group where id=:id").setParameter("id",id).executeUpdate();

    }

    @Override
    @Transactional
    public List<Group> findAll(Long id) {
        return entityManager.find(Course.class, id).getGroups();
    }
}
