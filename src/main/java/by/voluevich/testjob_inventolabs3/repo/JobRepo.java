package by.voluevich.testjob_inventolabs3.repo;

import by.voluevich.testjob_inventolabs3.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<Job, Long> {

    boolean existsByDescription(String desc);

    Job getByDescription(String desc);
}
