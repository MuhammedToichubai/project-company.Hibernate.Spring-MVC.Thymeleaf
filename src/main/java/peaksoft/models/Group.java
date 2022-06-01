package peaksoft.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Group {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator_group")
    @SequenceGenerator(
            name = "id_generator_group",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    private String dateOfStart;

    @Column(name = "date_of_finish")
    private String dateOfFinish;

    @Transient
    private Long courseId;

    @ManyToMany(cascade = {CascadeType.ALL},mappedBy ="groups", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "group")
    @ToString.Exclude
    private List<Student> students;

    public Group(String groupName, String dateOfStart, String dateOfFinish) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
    }

    public Course getCourse() {
        return new Course();
    }

    public void setCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setGroup(this);
    }
}