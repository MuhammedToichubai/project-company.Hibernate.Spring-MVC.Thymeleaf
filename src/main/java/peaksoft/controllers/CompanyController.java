package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Company;
import peaksoft.services.MyService;

/**
 * @author Muhammed Toichubai
 */
@Controller
@RequestMapping("/api")
public class CompanyController {

      private final MyService<Company> companyService;
    @Autowired
    public CompanyController(MyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/save")
    public String save(Company company){
        companyService.save(company);
        return "redirect:/api";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("company1", new Company());
        return "company/addCompany";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        companyService.delete(id);
        return "redirect:/api";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("company")Company company, @PathVariable("id") Long id){
        companyService.update(id, company);
       return "redirect:/api" ;
    }

    @GetMapping("update/{id}")
    public String edit(@PathVariable Long id,Model model){
        model.addAttribute("company", companyService.getById(id));
        return "company/updateCompany";
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("getAllCompany", companyService.getAll());
        return "company/getAllCompany";
    }
}
