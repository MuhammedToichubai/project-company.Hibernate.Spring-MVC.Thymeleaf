package peaksoft.repositories;

import org.springframework.stereotype.Repository;
import peaksoft.models.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Repository
public class CompanyRepository implements MyRepository<Company> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Company save(Company company) {
        entityManager.persist(company);
        return company;
    }

    @Override
    @Transactional
    public List<Company> getAll() {
        return entityManager.createQuery("select c from Company c", Company.class).getResultList();
    }

    @Override
    @Transactional
    public Company getById(Long id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    @Transactional
    public void update(Long id, Company company) {
        Company companyCom = getById(id);
        companyCom.setCompanyName(company.getCompanyName());
        companyCom.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(companyCom);
    }

    @Override
    @Transactional
    public void delete(Long id) {

       entityManager.remove(getById(id));

    }

    @Override
    public List<Company> findAll(Long id) {
        return null;
    }

}



