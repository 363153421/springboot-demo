package com.wkf.tablesaw;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.BubblePlot;
import tech.tablesaw.plotly.components.Figure;

import java.io.IOException;

/**
 * @author wuKeFan
 * @date 2024-06-13 09:57:07
 */
public class BubbleExample {

    public static void main(String[] args) throws IOException {

        Table wines = Table.read().csv("D:/gitProject/springboot-demo/tablesaw/src/main/resources/data/tornadoes_1950-2014.csv");

        Table champagne =
                wines.where(
                        wines
                                .stringColumn("wine type")
                                .isEqualTo("Champagne & Sparkling")
                                .and(wines.stringColumn("region").isEqualTo("California")));

        Figure figure =
                BubblePlot.create(
                        "Average retail price for champagnes by year and rating",
                        champagne, // table namex
                        "highest pro score", // x variable column name
                        "year", // y variable column name
                        "Mean Retail" // bubble size
                );

        Plot.show(figure);
    }

}
