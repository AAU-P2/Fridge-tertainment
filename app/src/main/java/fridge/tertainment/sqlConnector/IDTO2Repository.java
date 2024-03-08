package fridge.tertainment.sqlConnector;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDTO2Repository<dto2> {
    dto2 Get(int id1, int id2) throws SQLException;
    boolean Update(dto2 dto) throws SQLException;
    boolean Delete(dto2 dto) throws SQLException;
    boolean Delete(int id1, int id2) throws SQLException;

    ArrayList<dto2> GetAll() throws SQLException;

    /**
     * Implementations of GetAllById can vary
     * @param id1 can be null or a number corresponding to some entities in the repository
     * @param id2 can be null or a number corresponding to some entities in the repository
     * @return Return a list of entities, should be identical to GetAll() if neither id1 or id2 is set, should be identical to Get() if both are set
     */
    ArrayList<dto2> GetAllById(Integer id1, Integer id2) throws SQLException;
}
