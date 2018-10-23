package application.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    List<T> getAll() throws SQLException;

    T add(T obj) throws SQLException;

    boolean update(T obj) throws SQLException;

    void removeById(int id) throws SQLException;

    T getById(int id);
}
