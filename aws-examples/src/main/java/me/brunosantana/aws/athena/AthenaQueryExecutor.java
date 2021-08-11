package me.brunosantana.aws.athena;

import java.sql.*;
import java.util.Properties;

import static me.brunosantana.aws.athena.AthenaConstants.*;

public class AthenaQueryExecutor {

    public void execute(String accessKey, String secretKey){
        Properties props = new Properties();
        Connection conn = null;
        Statement statement = null;
        try{
            setProperties(props, accessKey, secretKey);
            //load driver class
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(CONNECTION_URL, props);
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(QUERY);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            System.out.println(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", "marketplace",
                    "review_id", "product_id", "product_title", "product_category", "vine",
                    "verified_purchase", "review_headline", "review_date"));
            while (rs.next()){
                System.out.println(rs.getString(1) + "\t" +
                        rs.getString(3) + "\t" +
                        rs.getString(4) + "\t" +
                        rs.getString(6) + "\t" +
                        rs.getString(7) + "\t" +
                        rs.getString(11) + "\t" +
                        rs.getString(12) + "\t" +
                        rs.getString(13) + "\t" +
                        rs.getString(15));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{if(conn != null) conn.close();}catch (Exception e){}
            try{if(statement != null) statement.close();}catch (Exception e){}
        }
    }

    private void setProperties(Properties properties, String accessKey, String secretKey){
        //set System properties
        System.setProperty("aws.accessKeyId", accessKey);
        System.setProperty("aws.secretKey", secretKey);
        //set Athena props
        properties.setProperty("S3OutputLocation", S3_OUTPUT_LOCATION);
        properties.setProperty("AwsCredentialsProviderClass", "com.simba.athena.amazonaws.auth.SystemPropertiesCredentialsProvider");
    }

}
