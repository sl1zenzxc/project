package ru.urfu.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import ru.urfu.converter.ConverterForbs;
import ru.urfu.db.DataBase;
import ru.urfu.parser.CsvParser;
import ru.urfu.parser.model.Forbes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    private static final CsvParser parser = new CsvParser();

    public static void startApp(String pathToCsv, String pathToDb) {
        var forbesList = parser.parser(pathToCsv);
        if (forbesList == null) {
            throw new RuntimeException("Произошла ошибка парсинга");
        }
        ConverterForbs.startConverting(forbesList);

        DataBase dataBase = new DataBase(pathToDb);
        dataBase.createTablesInDB();
        dataBase.save();
        taskOne();
        taskTwo(pathToDb);
        taskThree(pathToDb);
    }

    private static void taskTwo(String pathToDb) {
        DataBase dataBase = new DataBase(pathToDb);
        String aYoungMillionaireFromFrance = dataBase.getAYoungMillionaireFromFrance();
        System.out.println(aYoungMillionaireFromFrance);
    }

    private static void taskThree(String pathToDb) {
        DataBase dataBase = new DataBase(pathToDb);
        String nameAndCompanyBusinessmanFromUSA = dataBase.getNameAndCompanyBusinessmanFromUSA();
        System.out.println(nameAndCompanyBusinessmanFromUSA);
    }

    private static void taskOne() {
        List<Forbes> forbesList = ConverterForbs.forbes;

        Map<String, Double> totalNetworthByCountry = forbesList.stream()
                .collect(Collectors.groupingBy(Forbes::getCountry, Collectors.summingDouble(forbes -> {
                    return Double.parseDouble(forbes.getNetworth().trim());
                })));

        plotTotalNetworthByCountry(totalNetworthByCountry);
    }

    private static void plotTotalNetworthByCountry(Map<String, Double> totalNetworthByCountry) {
        CategoryDataset dataset = createDataset(totalNetworthByCountry);
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Total Networth by Country");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static CategoryDataset createDataset(Map<String, Double> totalNetworthByCountry) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : totalNetworthByCountry.entrySet()) {
            String country = entry.getKey();
            Double networth = entry.getValue();
            dataset.addValue(networth, "Total Networth", country);
        }

        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "Total Networth by Country",
                "Country",
                "Total Networth",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        Font tickLabelFont = new Font("Arial", Font.PLAIN, 10);
        plot.getDomainAxis().setTickLabelFont(tickLabelFont);
        plot.setRenderer(renderer);

        return chart;
    }
}
