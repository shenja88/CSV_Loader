package by.voluevich.testjob_inventolabs3.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("by/voluevich/testjob_inventolabs3/model")
@ComponentScan(basePackages = "by.voluevich.testjob_inventolabs3")
public class DBConfiguration {
}
