package ru.urfu.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.urfu.dto.Age;
import ru.urfu.dto.Company;
import ru.urfu.dto.Country;
import ru.urfu.dto.Human;
import ru.urfu.dto.Industry;
import ru.urfu.dto.Networth;
import ru.urfu.dto.Rank;
import ru.urfu.parser.model.Forbes;

import java.util.List;

@Mapper
public interface MapperForbs {

    MapperForbs INSTANCE = Mappers.getMapper(MapperForbs.class);

    @Mapping(target = "rankId", source = "rank", qualifiedByName = "toInt")
    @Mapping(target = "id", ignore = true)
    Rank toRank(Forbes forbes);

    List<Rank> toRankList(List<Forbes> forbesList);

    @Mapping(target = "id", ignore = true)
    Human toHuman(Forbes forbes);

    List<Human> toHumanList(List<Forbes> forbesList);

    @Mapping(target = "networth", source = "networth", qualifiedByName = "toDouble")
    @Mapping(target = "id", ignore = true)
    Networth toNetworth(Forbes forbes);

    List<Networth> toNetworthList(List<Forbes> forbesList);

    @Mapping(target = "age", source = "age", qualifiedByName = "toInt")
    @Mapping(target = "id", ignore = true)
    Age toAge(Forbes forbes);

    List<Age> toAgeList(List<Forbes> forbesList);

    @Mapping(target = "countryName", source = "country")
    @Mapping(target = "id", ignore = true)
    Country toCountry(Forbes forbes);

    List<Country> toCountryList(List<Forbes> forbesList);

    @Mapping(target = "name", source = "source")
    @Mapping(target = "id", ignore = true)
    Company toCompany(Forbes forbes);

    List<Company> toCompanyList(List<Forbes> forbesList);

    @Named("toInt")
    default Integer toInt(String str) {
        return Integer.parseInt(str.trim());
    }

    @Named("toDouble")
    default double toDouble(String str) {
        return Double.parseDouble(str.trim());
    }


    @Mapping(target = "industryName", source = "industry")
    @Mapping(target = "id", ignore = true)
    Industry toIndustry(Forbes forbesList);
    List<Industry> toIndustryList(List<Forbes> forbesList);
}
