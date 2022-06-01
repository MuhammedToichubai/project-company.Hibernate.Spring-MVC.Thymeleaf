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
@Transactional
public class GroupService implements MyService<Group>{

    private MyRepository<Group> groupMyRepository;

    @Override
    public Group save(Group group) {
        return groupMyRepository.save(group);
    }

    @Override
    public List<Group> getAll() {
        return groupMyRepository.getAll();
    }

    @Override
    public Group getById(Long id) {
        return groupMyRepository.getById(id);
    }

    @Override
    public void update(Long id, Group group) {
      groupMyRepository.update(id, group);
    }

    @Override
    public void delete(Long id) {
        groupMyRepository.delete(id);

    }

    @Override
    public List<Group> findAll(Long id) {
        return groupMyRepository.findAll(id);
    }
}
