package by.voluevich.testjob_inventolabs3.dataloader;

import au.com.bytecode.opencsv.CSVReader;
import by.voluevich.testjob_inventolabs3.model.Department;
import by.voluevich.testjob_inventolabs3.model.Job;
import by.voluevich.testjob_inventolabs3.model.User;
import by.voluevich.testjob_inventolabs3.repo.DepartmentRepo;
import by.voluevich.testjob_inventolabs3.repo.JobRepo;
import by.voluevich.testjob_inventolabs3.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class LoginsCSVLoader implements CSVLoader {
    @Value("${app.resource.file.logins}")
    private String loginsFileName;
    @Value("${app.resource.file.logins.application}")
    private String APPLICATION;
    @Value("${app.resource.file.logins.username}")
    private String USER_NAME;
    @Value("${app.resource.file.logins.is_active}")
    private String IS_ACTIVE;
    @Value("${app.resource.file.logins.job}")
    private String JOB;
    @Value("${app.resource.file.logins.department}")
    private String DEPARTMENT;
    @Value("${app.resource.file.logins.separator}")
    private char SEPARATOR;

    private final DepartmentRepo departmentRepo;
    private final JobRepo jobRepo;
    private final UserRepo userRepo;

    @Autowired
    public LoginsCSVLoader(DepartmentRepo departmentRepo, JobRepo jobRepo, UserRepo userRepo) {
        this.departmentRepo = departmentRepo;
        this.jobRepo = jobRepo;
        this.userRepo = userRepo;
    }


    private final Map<String, Integer> columnPosition = new HashMap<>();

    @Override
    public void startLoading() throws IOException {
        try (CSVReader reader = getCSVReader(loginsFileName, SEPARATOR)) {
            List<String[]> dataRows = reader.readAll();
            dataRows = proceedTabulation(dataRows);

            initDataPosition(dataRows.get(0));
            dataRows.remove(0);

            storingModels(dataRows);
        }
    }

    private void initDataPosition(String[] titleRow) {
        List<String> row = Arrays.stream(titleRow).collect(Collectors.toList());
        columnPosition.put(APPLICATION, row.indexOf(APPLICATION));
        columnPosition.put(USER_NAME, row.indexOf(USER_NAME));
        columnPosition.put(IS_ACTIVE, row.indexOf(IS_ACTIVE));
        columnPosition.put(JOB, row.indexOf(JOB));
        columnPosition.put(DEPARTMENT, row.indexOf(DEPARTMENT));
    }

    private void storingModels(List<String[]> dataRows) {
        dataRows.forEach(row -> {
            Department department = storeDepartment(row[columnPosition.get(DEPARTMENT)]);
            Job job = storeJob(row[columnPosition.get(JOB)]);
            storeUser(row, job, department);
        });
    }

    private Department storeDepartment(String desc) {
        if (!departmentRepo.existsByDescription(desc)) {
            Department department = Department.builder()
                    .description(desc)
                    .build();
            return departmentRepo.save(department);
        }
        return departmentRepo.getByDescription(desc);
    }

    private Job storeJob(String desc) {
        if (!jobRepo.existsByDescription(desc)) {
            Job job = Job.builder()
                    .description(desc)
                    .build();
            return jobRepo.save(job);
        }
        return jobRepo.getByDescription(desc);
    }

    private void storeUser(String[] row, Job job, Department department) {
        User user = User.builder()
                .accountName(row[columnPosition.get(USER_NAME)])
                .application(row[columnPosition.get(APPLICATION)])
                .job(job)
                .department(department)
                .isActive(Boolean.parseBoolean(row[columnPosition.get(IS_ACTIVE)]))
                .build();
        if (!userRepo.existsByAccountName(user.getAccountName())) {
            userRepo.save(user);
        }
    }
}
