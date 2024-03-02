package fridge.tertainment.sqlConnector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//Class to test datacase/code connection. Connection parameters are specified in db.properties file.

public class DatabaseConnection {


    public DatabaseConnection() throws Exception {
        getConnectionProperties();
        initConnection(properties);
    }

    public static Properties properties = new Properties();

    public static final void getConnectionProperties() {
        try (InputStream inputStream = new FileInputStream("app/src/main/java/fridge/tertainment/rsc/db.properties")) {

            properties.load(inputStream);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection initConnection(Properties properties) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
                    return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
