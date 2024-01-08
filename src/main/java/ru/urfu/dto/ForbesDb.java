package ru.urfu.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForbesDb {
    private long id;
    private int rankId;
    private int ageId;
    private int companyId;
    private int countryId;
    private int humanId;
    private int industryId;
    private int networthId;
}
