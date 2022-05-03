package by.voluevich.testjob_inventolabs3.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String accountName;
    private String application;
    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(accountName, user.accountName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName);
    }
}
