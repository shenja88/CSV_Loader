package by.voluevich.testjob_inventolabs3.dataloader;

import by.voluevich.testjob_inventolabs3.exception.InitDataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class DBInitializer {
    private final List<CSVLoader> loaderList;

    @PostConstruct
    public void initLoaders() throws InitDataException {
        for (CSVLoader loader : loaderList) {
            try {
                loader.startLoading();
            } catch (IOException e) {
                throw new InitDataException(loader.getClass().getSimpleName(), e.getClass().getSimpleName(), "Fail loading file. Check application.properties.");
            }
        }
    }
}
