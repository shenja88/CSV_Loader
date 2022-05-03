package by.voluevich.testjob_inventolabs3.controller;

import by.voluevich.testjob_inventolabs3.dto.UserDetailsDTO;
import by.voluevich.testjob_inventolabs3.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Transactional(rollbackFor = SQLException.class)
public class UserController {
    private final UserService userService;

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDetailsDTO> getById(@PathVariable long userId) {
        UserDetailsDTO userDTO = userService.getUserDetails(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/accountName/{accountName}")
    public ResponseEntity<UserDetailsDTO> getById(@PathVariable String accountName) {
        UserDetailsDTO userDTO = userService.getUserDetails(accountName);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
}
