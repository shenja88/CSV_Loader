package by.voluevich.testjob_inventolabs3.repo;

import by.voluevich.testjob_inventolabs3.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    boolean existsByDescription(String desc);

    Department getByDescription(String desc);
}
