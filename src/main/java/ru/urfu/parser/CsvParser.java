package ru.urfu.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.urfu.parser.model.Forbes;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public List<Forbes> parser(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            List<Forbes> beans = new CsvToBeanBuilder<Forbes>(fileReader)
                    .withSkipLines(1)
                    .withType(Forbes.class)
                    .build()
                    .parse();
            return new ArrayList<>(beans);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
