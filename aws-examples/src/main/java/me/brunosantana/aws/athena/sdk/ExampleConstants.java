package me.brunosantana.aws.athena.sdk;

public class ExampleConstants {

    public static final int CLIENT_EXECUTION_TIMEOUT = 100000;
    public static final String ATHENA_OUTPUT_BUCKET = "s3://athena-bucket-output-10/output/"; // change the Amazon S3 bucket name to match your environment
    //  Demonstrates how to query a table with a comma-separated value (CSV) table.  For information, see
    //https://docs.aws.amazon.com/athena/latest/ug/work-with-data.html
    public static final String ATHENA_SAMPLE_QUERY = "SELECT * FROM teste_athena_10 where identificador = '2';"; // change the Query statement to match your environment
    public static final long SLEEP_AMOUNT_IN_MS = 1000;
    public static final String ATHENA_DEFAULT_DATABASE = "athena_db"; // change the database to match your database

}
