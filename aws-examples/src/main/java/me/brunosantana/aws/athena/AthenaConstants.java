package me.brunosantana.aws.athena;

public class AthenaConstants {

    final static String AWS_ACCESS_KEY = ""; //add access key pois não vou commitar a key
    final static String AWS_SECRET_KEY = ""; //add secret pois não vou commitar o secret
    final static String CONNECTION_URL = "jdbc:awsathena://AwsRegion=us-east-2";
    final static String S3_OUTPUT_LOCATION = "s3://athena-bucket-output-10/output/";
    final static String QUERY = "SELECT * FROM \"athena_db\".\"teste_athena_10\" where identificador = '2';";
    final static String DRIVER_CLASS = "com.simba.athena.jdbc.Driver";

}
