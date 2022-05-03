package by.voluevich.testjob_inventolabs3.repo;

import by.voluevich.testjob_inventolabs3.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PostingRepo extends JpaRepository<Posting, Long> {

    boolean existsByUuid(long uuid);

    Posting getByUuid(long uuid);

    List<Posting> getAllByContractDateBetween(LocalDate start, LocalDate end);

    List<Posting> getAllByUserName(String userName);
}
