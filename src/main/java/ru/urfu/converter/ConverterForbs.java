package ru.urfu.converter;

import lombok.Data;
import ru.urfu.dto.Age;
import ru.urfu.dto.Company;
import ru.urfu.dto.Country;
import ru.urfu.dto.Human;
import ru.urfu.dto.Industry;
import ru.urfu.dto.Networth;
import ru.urfu.dto.Rank;
import ru.urfu.parser.model.Forbes;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ConverterForbs {

    public static List<Forbes> forbes = null;
    public static List<Age> ages = null;
    public static List<Company> companies = null;
    public static List<Country> countries = null;
    public static List<Human> humans = null;
    public static List<Industry> industries = null;
    public static List<Networth> networths = null;
    public static List<Rank> ranks = null;

    public static void startConverting(List<Forbes> forbes) {
        ConverterForbs.forbes = forbes;

        ConverterForbs.ranks = MapperForbs.INSTANCE.toRankList(forbes).stream()
                .distinct().collect(Collectors.toList());

        ConverterForbs.humans = MapperForbs.INSTANCE.toHumanList(forbes).stream()
                .distinct().collect(Collectors.toList());

        ConverterForbs.ages = MapperForbs.INSTANCE.toAgeList(forbes).stream()
                .distinct().collect(Collectors.toList());

        ConverterForbs.networths = MapperForbs.INSTANCE.toNetworthList(forbes).stream()
                .distinct().collect(Collectors.toList());

        ConverterForbs.countries = MapperForbs.INSTANCE.toCountryList(forbes).stream()
                .distinct().collect(Collectors.toList());

        ConverterForbs.companies = MapperForbs.INSTANCE.toCompanyList(forbes).stream().distinct()
                .collect(Collectors.toList());

        ConverterForbs.industries = MapperForbs.INSTANCE.toIndustryList(forbes).stream().distinct()
                .collect(Collectors.toList());
    }
}
