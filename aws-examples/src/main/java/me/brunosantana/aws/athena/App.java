package me.brunosantana.aws.athena;

public class App {

    public static void main(String[] args) {
        AthenaQueryExecutor athenaQueryExecutor = new AthenaQueryExecutor();
        athenaQueryExecutor.execute();
    }

}
