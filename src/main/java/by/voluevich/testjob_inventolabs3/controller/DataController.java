package by.voluevich.testjob_inventolabs3.controller;

import by.voluevich.testjob_inventolabs3.dto.PostingDTO;
import by.voluevich.testjob_inventolabs3.dto.SearchFilter;
import by.voluevich.testjob_inventolabs3.dto.UserDetailsDTO;
import by.voluevich.testjob_inventolabs3.service.PostingsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
@AllArgsConstructor
@Transactional(rollbackFor = SQLException.class)
public class DataController {
    private final PostingsService service;

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<PostingDTO> getById(@PathVariable long uuid) {
        PostingDTO postingDTO = service.getByUuid(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(postingDTO);
    }

    @GetMapping("/byUser/{accountName}")
    public ResponseEntity<List<PostingDTO>> getById(@PathVariable String accountName) {
        List<PostingDTO> postingDTOS = service.getByAccountName(accountName);
        return ResponseEntity.status(HttpStatus.OK).body(postingDTOS);
    }

    @PutMapping("/filter")
    public ResponseEntity<List<PostingDTO>> getById(@RequestBody SearchFilter filter) {
        List<PostingDTO> postingDTOS = service.getAllByFilter(filter);
        if (postingDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(postingDTOS);
    }
}
