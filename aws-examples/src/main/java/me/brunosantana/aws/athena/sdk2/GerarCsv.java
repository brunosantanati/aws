package me.brunosantana.aws.athena.sdk2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GerarCsv {

    public static void main(String[] args) throws IOException {

        String header = "kpiName;kpiValue;frequency;customerId\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\teste\\income.csv"));
        writer.write(header);

        for (int i = 0; i < 5; i++) {
            writer.append("account_monthsrange;1.0;annual;000000001111111\n");
            writer.append("long_enough_for_KPIs;1.0;annual;000000001111111\n");
            writer.append("movements_last_30_days;3.0;annual;000000001111111\n");
            writer.append("movements_last_90_days;3.0;annual;000000001111111\n");
            writer.append("average_movements_last_90_days;1.0;annual;000000001111111\n");
        }

        writer.close();
    }

}
