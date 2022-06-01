package peaksoft.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator_course")
    @SequenceGenerator(
            name = "id_generator_course",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @NotEmpty(message = "?")
    private String duration;

    @Transient
    private Long companyId;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "group_course",joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @ToString.Exclude
    private List<Group> groups;

    @OneToOne(cascade = CascadeType.MERGE,mappedBy = "course")
    private Teacher teacher;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    private  Company company;

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public void setGroup(Group group) {
        if (groups==null){
            groups=new ArrayList<>();

        }
        groups.add(group);
    }
}
