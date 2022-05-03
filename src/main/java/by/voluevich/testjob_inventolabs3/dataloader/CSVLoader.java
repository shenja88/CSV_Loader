package by.voluevich.testjob_inventolabs3.dataloader;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface CSVLoader {
    String DATE_FORMAT = "dd.MM.yyyy";

    void  startLoading() throws IOException;

    default List<String[]> proceedTabulation(List<String[]> rows) {
        List<String[]> proceedList = new ArrayList<>();
        rows.forEach(row -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : row) {
                stringBuilder.append(s);
            }
            String[] splitRow = stringBuilder.toString().split("\t");
            if (splitRow.length > 1) {
                proceedList.add(splitRow);
            }
        });
        return proceedList;
    }

    default CSVReader getCSVReader(String fileName, char separator) throws FileNotFoundException {
        return new CSVReader(
                new FileReader("src/main/resources/" + fileName),
                separator);
    }

    default LocalDate getTime(String time) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    default double getDouble(String value) {
        String refactorDouble = value.replace(',', '.');
        return Double.parseDouble(refactorDouble);
    }
}
