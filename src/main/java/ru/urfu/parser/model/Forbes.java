package ru.urfu.parser.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Forbes {

    @CsvBindByPosition(position = 0)
    private String rank;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private String networth;

    @CsvBindByPosition(position = 3)
    private String age;

    @CsvBindByPosition(position = 4)
    private String country;

    @CsvBindByPosition(position = 5)
    private String source;

    @CsvBindByPosition(position = 6)
    private String industry;
}
