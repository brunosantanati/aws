package me.brunosantana.aws.athena;

import java.sql.*;
import java.util.Properties;

import static me.brunosantana.aws.athena.AthenaConstants.*;

public class AthenaQueryExecutor {

    public void execute(){
        Properties props = new Properties();
        Connection conn = null;
        Statement statement = null;
        try{
            setProperties(props);
            //load driver class
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(CONNECTION_URL, props);
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(QUERY);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            while (rs.next()){
                System.out.println(rs.getString(1) + "\t" +
                        rs.getString(3));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{if(conn != null) conn.close();}catch (Exception e){}
            try{if(statement != null) statement.close();}catch (Exception e){}
        }
    }

    private void setProperties(Properties properties){
        //set System properties
        System.setProperty("aws.accessKeyId", AWS_ACCESS_KEY);
        System.setProperty("aws.secretKey", AWS_SECRET_KEY);
        //set Athena props
        properties.setProperty("S3OutputLocation", S3_OUTPUT_LOCATION);
        properties.setProperty("AwsCredentialsProviderClass", "com.simba.athena.amazonaws.auth.SystemPropertiesCredentialsProvider");
    }

}
