package peaksoft.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import peaksoft.models.Company;
import peaksoft.repositories.MyRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Muhammed Toichubai
 */
@Service
public class CompanyService implements MyService<Company>{

    private final MyRepository<Company> companyRepo;

    public CompanyService(@Qualifier("companyRepository") MyRepository myRepository) {
        this.companyRepo = myRepository;
    }

    @Override
    @Transactional
    public Company save(Company company) {
        companyRepo.save(company);
        return company;
    }

    @Override
    @Transactional
    public List<Company> getAll() {
        return companyRepo.getAll();
    }

    @Override
    @Transactional
    public Company getById(Long id) {
        return (Company) companyRepo.getById(id);
    }

    @Override
    public void update(Long id, Company company) {
        companyRepo.update(id, company);

    }

    @Override
    public void delete(Long id) {
        companyRepo.delete(id);
    }

    @Override
    public List<Company> findAll(Long id) {
        return null;
    }
}

