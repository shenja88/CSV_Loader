package by.voluevich.testjob_inventolabs3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InitDataException extends Exception {
    private String file;
    private String exception;
    private String desc;
}
