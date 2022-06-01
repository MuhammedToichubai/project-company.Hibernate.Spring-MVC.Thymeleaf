package peaksoft.repositories;



import java.util.List;

/**
 * @author Muhammed Toichubai
 */
public interface MyRepository<T>{

    T save(T t);

    List<T> getAll();

    T getById(Long id);

    void update(Long id, T t);

    void delete(Long id);

    List<T>findAll(Long id);
}
