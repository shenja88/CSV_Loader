package by.voluevich.testjob_inventolabs3.service;

import by.voluevich.testjob_inventolabs3.dto.UserDetailsDTO;
import by.voluevich.testjob_inventolabs3.model.User;
import by.voluevich.testjob_inventolabs3.repo.UserRepo;
import by.voluevich.testjob_inventolabs3.util.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserDetailsDTO getUserDetails(String accountName) {
        User user = userRepo.getByAccountName(accountName);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        return DTOMapper.getUserDetailsDTO(user);
    }

    public UserDetailsDTO getUserDetails(long userId) {
        User user = userRepo.getById(userId);
        return DTOMapper.getUserDetailsDTO(user);
    }
}
