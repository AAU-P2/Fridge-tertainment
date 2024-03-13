package fridge.tertainment.sqlConnector;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDTO1Repository<dto1> {
    dto1 Get(int id) throws SQLException;
    boolean Update(dto1 dto) throws SQLException;
    boolean Delete(dto1 dto) throws SQLException;
    boolean Delete(int id) throws SQLException;

    boolean Create(dto1 dto) throws SQLException;

    ArrayList<dto1> GetAll() throws SQLException;
}
