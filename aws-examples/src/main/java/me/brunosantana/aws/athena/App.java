package me.brunosantana.aws.athena;

public class App {

    //Passar accessKey e secretKey via argumentos do programa
    public static void main(String[] args) {
        AthenaQueryExecutor athenaQueryExecutor = new AthenaQueryExecutor();
        athenaQueryExecutor.execute(args[0], args[1]);
    }

}
