package peaksoft.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator_company")
    @SequenceGenerator(
            name = "id_generator_company",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "located_country")
    private String locatedCountry;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "company", orphanRemoval = true)
    @ToString.Exclude
    private List<Course> courses;


    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }
}
