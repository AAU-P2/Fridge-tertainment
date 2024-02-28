package fridge.tertainment.sqlConnector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//Class to test datacase/code connection. Connection parameters are specified in db.properties file.

public class SqlConnectorTest {

    public static Properties properties = new Properties();
    public static Connection connection = new Connection() {
        
    };

    public static void main(String[] args) {
        getConnectionProperties();
        initConnection(properties);
        closeConnection(connection);;
    }

    public static final void getConnectionProperties(){
        try (InputStream inputStream = new FileInputStream("app/src/main/java/fridge/tertainment/rsc/db.properties")){

            properties.load(inputStream);

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void initConnection(Properties properties){
        try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection=DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
                connection.close();
            }
            catch(Exception e){
                e.printStackTrace();    
             }
        }
    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}