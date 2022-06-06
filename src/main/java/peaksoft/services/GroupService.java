package peaksoft.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.models.Course;
import peaksoft.models.Group;
import peaksoft.repositories.MyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @author Muhammed Toichubai
 */
@Service
@AllArgsConstructor
public class GroupService implements MyService<Group>{

    private MyRepository<Group> groupMyRepository;

    @Override
    @Transactional
    public Group save(Group group) {
        return groupMyRepository.save(group);
    }

    @Override
    @Transactional
    public List<Group> getAll() {
        return groupMyRepository.getAll();
    }

    @Override
    @Transactional
    public Group getById(Long id) {
        return groupMyRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id, Group group) {
      groupMyRepository.update(id, group);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        groupMyRepository.delete(id);

    }

    @Override
    @Transactional
    public List<Group> findAll(Long id) {
        return groupMyRepository.findAll(id);
    }
}
