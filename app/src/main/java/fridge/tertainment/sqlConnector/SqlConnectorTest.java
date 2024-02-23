package fridge.tertainment.sqlConnector;

import java.sql.*;

public class SqlConnectorTest {

    public static void main(String[] args) {
        try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fridgeDB", "root", "gruppe6aau");
                Statement statement = connection.createStatement();
                ResultSet rSet = statement.executeQuery("SELECT food FROM foodTable;");
                while (rSet.next()) {
                    System.out.println(rSet.getString(1));
                }
                connection.close();
            }
            catch(Exception e){
                System.out.println(e);    
             }
    
}

}