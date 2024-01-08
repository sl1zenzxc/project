package ru.urfu.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.jdbc.ScriptRunner;
import ru.urfu.converter.ConverterForbs;
import ru.urfu.dto.Age;
import ru.urfu.dto.Company;
import ru.urfu.dto.Country;
import ru.urfu.dto.ForbesDb;
import ru.urfu.dto.Human;
import ru.urfu.dto.Industry;
import ru.urfu.dto.Networth;
import ru.urfu.dto.Rank;
import ru.urfu.parser.model.Forbes;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class DataBase {

    private static final String SCRIPT_FILE_PATH = "src/main/java/ru/urfu/db/script/create_tables.sql";

    private String url;

    public void createTablesInDB() {
        try (Reader reader = new FileReader(SCRIPT_FILE_PATH)) {
            ScriptRunner scriptRunner = new ScriptRunner(getConn());
            scriptRunner.runScript(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void save() {
        fillingTableAge();
        fillingTableCompany();
        fillingTableCountry();
        fillingTableHuman();
        fillingTableIndustry();
        fillingTableNetworth();
        fillingTableRank();
        saveForbs(fillingTableForbs());
    }

    private Connection getConn() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void fillingTableAge() {
        try (PreparedStatement preparedStatement = getConn().prepareStatement("INSERT INTO `Age` (`age`) VALUES\n" +
                "(?);")) {
            List<Age> ages = ConverterForbs.ages;
            if (ages != null) {
                for (int i = 0; i < ages.size(); i++) {
                    preparedStatement.setInt(1, ages.get(i).getAge());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillingTableCompany() {
        try (PreparedStatement preparedStatement = getConn().prepareStatement("INSERT INTO `Company` (`name`) VALUES\n" +
                "(?);")) {
            List<Company> companies = ConverterForbs.companies;
            if (companies != null) {
                for (int i = 0; i < companies.size(); i++) {
                    preparedStatement.setString(1, companies.get(i).getName());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillingTableCountry() {
        try (PreparedStatement preparedStatement = getConn().prepareStatement("INSERT INTO `Country` (`name`) VALUES\n" +
                "(?);")) {
            List<Country> countries = ConverterForbs.countries;
            if (countries != null) {
                for (int i = 0; i < countries.size(); i++) {
                    preparedStatement.setString(1, countries.get(i).getCountryName());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillingTableHuman() {
        try (PreparedStatement preparedStatement = getConn().prepareStatement("INSERT INTO `Human` (`name`) VALUES\n" +
                "(?);")) {
            List<Human> humans = ConverterForbs.humans;
            if (humans != null) {
                for (int i = 0; i < humans.size(); i++) {
                    preparedStatement.setString(1, humans.get(i).getName());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillingTableIndustry() {
        try (PreparedStatement preparedStatement = getConn().prepareStatement("INSERT INTO `Industry` (`name`) VALUES\n" +
                "(?);")) {
            List<Industry> industries = ConverterForbs.industries;
            if (industries != null) {
                for (int i = 0; i < industries.size(); i++) {
                    preparedStatement.setString(1, industries.get(i).getIndustryName());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillingTableNetworth() {
        try (PreparedStatement preparedStatement = getConn().prepareStatement("INSERT INTO `Networth` (`name`) VALUES\n" +
                "(?);")) {
            List<Networth> networths = ConverterForbs.networths;
            if (networths != null) {
                for (int i = 0; i < networths.size(); i++) {
                    preparedStatement.setDouble(1, networths.get(i).getNetworth());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillingTableRank() {
        try (PreparedStatement preparedStatement = getConn().prepareStatement("INSERT INTO `Rank` (`rankId`) VALUES\n" +
                "(?);")) {
            List<Rank> ranks = ConverterForbs.ranks;
            if (ranks != null) {
                for (int i = 0; i < ranks.size(); i++) {
                    preparedStatement.setInt(1, ranks.get(i).getRankId());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveForbs(List<ForbesDb> forbesDbs) {
        try (PreparedStatement preparedStatement = getConn()
                .prepareStatement("INSERT INTO `Forbes` (`rank_id`, " +
                        "`age_id`, `company_id`, `country_id`, `human_id`, `industry_id`, `networth_id`) VALUES\n" +
                        "(?,?,?,?,?,?,?);")) {
            if (forbesDbs != null) {
                for (int i = 0; i < forbesDbs.size(); i++) {
                    preparedStatement.setInt(1, forbesDbs.get(i).getRankId());
                    preparedStatement.setInt(2, forbesDbs.get(i).getAgeId());
                    preparedStatement.setInt(3, forbesDbs.get(i).getCompanyId());
                    preparedStatement.setInt(4, forbesDbs.get(i).getCountryId());
                    preparedStatement.setInt(5, forbesDbs.get(i).getHumanId());
                    preparedStatement.setInt(6, forbesDbs.get(i).getIndustryId());
                    preparedStatement.setInt(7, forbesDbs.get(i).getNetworthId());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ForbesDb> fillingTableForbs() {
        List<Forbes> forbesList = ConverterForbs.forbes;
        List<ForbesDb> forbesDbs = new ArrayList<>();

        String ageQuery = "SELECT * FROM Age age WHERE age.age = ?";
        String companyQuery = "SELECT * FROM Company company WHERE company.name = ?";
        String countryQuery = "SELECT * FROM Country country WHERE country.name = ?";
        String humanQuery = "SELECT * FROM Human human WHERE human.name = ?";
        String industryQuery = "SELECT * FROM Industry industry WHERE industry.name = ?";
        String networthQuery = "SELECT * FROM Networth networth WHERE networth.name = ?";
        String rankQuery = "SELECT * FROM Rank rank WHERE rank.rankId = ?";

        try (Connection connection = getConn()) {
            for (Forbes forbes : forbesList) {
                try (PreparedStatement ageStatement = connection.prepareStatement(ageQuery);
                     PreparedStatement companyStatement = connection.prepareStatement(companyQuery);
                     PreparedStatement countryStatement = connection.prepareStatement(countryQuery);
                     PreparedStatement humanStatement = connection.prepareStatement(humanQuery);
                     PreparedStatement industryStatement = connection.prepareStatement(industryQuery);
                     PreparedStatement networthStatement = connection.prepareStatement(networthQuery);
                     PreparedStatement rankStatement = connection.prepareStatement(rankQuery)) {

                    ageStatement.setString(1, forbes.getAge());
                    companyStatement.setString(1, forbes.getSource());
                    countryStatement.setString(1, forbes.getCountry());
                    humanStatement.setString(1, forbes.getName());
                    industryStatement.setString(1, forbes.getIndustry());
                    networthStatement.setString(1, forbes.getNetworth());
                    rankStatement.setString(1, forbes.getRank());

                    try (ResultSet resultSetAge = ageStatement.executeQuery();
                         ResultSet resultSetCompany = companyStatement.executeQuery();
                         ResultSet resultSetCountry = countryStatement.executeQuery();
                         ResultSet resultSetHuman = humanStatement.executeQuery();
                         ResultSet resultSetIndustry = industryStatement.executeQuery();
                         ResultSet resultSetNetworth = networthStatement.executeQuery();
                         ResultSet resultSetRank = rankStatement.executeQuery()) {

                        ageStatement.clearBatch();
                        companyStatement.clearBatch();
                        countryStatement.clearBatch();
                        humanStatement.clearBatch();
                        industryStatement.clearBatch();
                        networthStatement.clearBatch();
                        rankStatement.clearBatch();
                        ForbesDb forbesSave = ForbesDb.builder()
                                .ageId(resultSetAge.getInt("id"))
                                .companyId(resultSetCompany.getInt("id"))
                                .countryId(resultSetCountry.getInt("id"))
                                .humanId(resultSetHuman.getInt("id"))
                                .industryId(resultSetIndustry.getInt("id"))
                                .networthId(resultSetNetworth.getInt("id"))
                                .rankId(resultSetRank.getInt("id"))
                                .build();
                        forbesDbs.add(forbesSave);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return forbesDbs;
    }

    public String getAYoungMillionaireFromFrance() {
        String sqlQuery = "SELECT human.name AS 'name', country.name AS 'country', max(networth.name) AS 'capital' FROM Forbes forbes\n" +
                "JOIN Country country ON forbes.country_id = country.id and country.name = 'France'\n" +
                "JOIN Human human ON forbes.human_id = human.id\n" +
                "JOIN Age age ON forbes.age_id = age.id and age.age < 52\n" +
                "JOIN Networth networth ON forbes.networth_id = networth.id GROUP BY human.name, country.name;";

        try (Statement statement = Objects.requireNonNull(getConn()).createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double capital = resultSet.getDouble("capital");
                return String.format("Самый молодой человек из Франции с капиталом, превышающим 10 млрд: %s\nС капиталом: %.2f млрд", name, capital);
            } else {
                return "Нет данных о молодых миллиардерах из Франции с капиталом более 10 млрд.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNameAndCompanyBusinessmanFromUSA() {
        String sqlQuery = "SELECT human.name AS 'name',\n" +
                "       max(networth.name) AS 'capital',\n" +
                "       company.name AS 'company'\n" +
                "FROM Forbes forbes\n" +
                "    JOIN Human human ON forbes.human_id = human.id\n" +
                "    JOIN Company company ON forbes.company_id = company.id\n" +
                "    JOIN Country country ON forbes.country_id = country.id and country.name = 'United States'\n" +
                "    JOIN Industry industry ON forbes.industry_id = industry.id and industry.name = 'Energy '\n" +
                "    JOIN Networth networth ON forbes.networth_id = networth.id;";

        try (Statement statement = Objects.requireNonNull(getConn()).createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String company = resultSet.getString("company");
                return String.format("Бизнесмен из США с максимальным капиталом, %s\nКомпания %s", name, company);
            } else {
                return "Нет данных о молодых миллиардерах из Франции с капиталом более 10 млрд.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}