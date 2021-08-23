package me.brunosantana.aws.athena.sdk2;

import me.brunosantana.aws.athena.sdk.ExampleConstants;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.athena.AthenaClient;
import software.amazon.awssdk.services.athena.model.*;
import software.amazon.awssdk.services.athena.paginators.GetQueryResultsIterable;

import java.util.List;

public class CreateSelectAndDrop {

    private static final String ATHENA_DATABASE = "teste";
    private static final String ATHENA_OUTPUT_BUCKET = "s3://athena-bucket-output-10/output/";
    private static final String CREATE_TABLE_DDL = "CREATE EXTERNAL TABLE IF NOT EXISTS income (" +
            "  kpiName STRING," +
            "  kpiValue DECIMAL(2,1)," +
            "  frequency STRING, " +
            "  customerId STRING" +
            "  )" +
            "  ROW FORMAT DELIMITED" +
            "  FIELDS TERMINATED BY '\\;'" +
            "  LINES TERMINATED BY '\n'" +
            "  LOCATION 's3://income-bruno/income/'" +
            "  TBLPROPERTIES ('skip.header.line.count'='1');";
    private static final String SELECT_QUERY = "select count(*) as \"qtde_para_cobrar\" from income " +
            "where kpiname = 'long_enough_for_KPIs' and kpivalue = 1.0;";
    private static final String DROP_TABLE_QUERY = "DROP TABLE income;";

    public static void main(String[] args) throws InterruptedException {

        AthenaClient athenaClient = AthenaClient.builder()
                .region(Region.US_EAST_2)
                .build();

        String queryExecutionId = submitAthenaQuery(athenaClient, CREATE_TABLE_DDL);
        waitForQueryToComplete(athenaClient, queryExecutionId);

        queryExecutionId = submitAthenaQuery(athenaClient, SELECT_QUERY);
        waitForQueryToComplete(athenaClient, queryExecutionId);
        int resultCount = processResultRows(athenaClient, queryExecutionId);
        System.out.println("Count: " + resultCount);

        queryExecutionId = submitAthenaQuery(athenaClient, DROP_TABLE_QUERY);
        waitForQueryToComplete(athenaClient, queryExecutionId);

        athenaClient.close();
    }

    // Submits a sample query to Amazon Athena and returns the execution ID of the query
    public static String submitAthenaQuery(AthenaClient athenaClient, String query) {

        try {

            // The QueryExecutionContext allows us to set the database
            QueryExecutionContext queryExecutionContext = QueryExecutionContext.builder()
                    .database(ATHENA_DATABASE).build();

            // The result configuration specifies where the results of the query should go
            ResultConfiguration resultConfiguration = ResultConfiguration.builder()
                    .outputLocation(ATHENA_OUTPUT_BUCKET)
                    .build();

            StartQueryExecutionRequest startQueryExecutionRequest = StartQueryExecutionRequest.builder()
                    .queryString(query)
                    .queryExecutionContext(queryExecutionContext)
                    .resultConfiguration(resultConfiguration)
                    .build();

            StartQueryExecutionResponse startQueryExecutionResponse = athenaClient.startQueryExecution(startQueryExecutionRequest);
            return startQueryExecutionResponse.queryExecutionId();

        } catch (AthenaException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return "";
    }

    // Wait for an Amazon Athena query to complete, fail or to be cancelled
    public static void waitForQueryToComplete(AthenaClient athenaClient, String queryExecutionId) throws InterruptedException {
        GetQueryExecutionRequest getQueryExecutionRequest = GetQueryExecutionRequest.builder()
                .queryExecutionId(queryExecutionId).build();

        GetQueryExecutionResponse getQueryExecutionResponse;
        boolean isQueryStillRunning = true;
        while (isQueryStillRunning) {
            getQueryExecutionResponse = athenaClient.getQueryExecution(getQueryExecutionRequest);
            String queryState = getQueryExecutionResponse.queryExecution().status().state().toString();
            if (queryState.equals(QueryExecutionState.FAILED.toString())) {
                throw new RuntimeException("The Amazon Athena query failed to run with error message: " + getQueryExecutionResponse
                        .queryExecution().status().stateChangeReason());
            } else if (queryState.equals(QueryExecutionState.CANCELLED.toString())) {
                throw new RuntimeException("The Amazon Athena query was cancelled.");
            } else if (queryState.equals(QueryExecutionState.SUCCEEDED.toString())) {
                isQueryStillRunning = false;
            } else {
                // Sleep an amount of time before retrying again
                Thread.sleep(ExampleConstants.SLEEP_AMOUNT_IN_MS);
            }
            System.out.println("The current status is: " + queryState);
        }
    }

    // This code retrieves the results of a query
    public static int processResultRows(AthenaClient athenaClient, String queryExecutionId) {

        int resultCount = 0;

        // Max Results can be set but if its not set,
        // it will choose the maximum page size
        GetQueryResultsRequest getQueryResultsRequest = GetQueryResultsRequest.builder()
                .queryExecutionId(queryExecutionId)
                .build();

        GetQueryResultsIterable getQueryResultsResults = athenaClient.getQueryResultsPaginator(getQueryResultsRequest);

        for (GetQueryResultsResponse result : getQueryResultsResults) {
            List<Row> row = result.resultSet().rows();
            resultCount = processRow(row);
            break;
        }

        return resultCount;
    }

    private static int processRow(List<Row> row) {

        return Integer.parseInt(row.get(1).data().get(0).varCharValue());

        /*for (Row myRow : row) {
            List<Datum> allData = myRow.data();
            for (Datum data : allData) {
                System.out.println("The value of the column is "+data.varCharValue());
            }
        }*/
    }
}
