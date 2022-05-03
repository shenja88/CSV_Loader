package by.voluevich.testjob_inventolabs3.repo;

import by.voluevich.testjob_inventolabs3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByAccountName(String accountName);

    User getByAccountName(String accountName);
}
