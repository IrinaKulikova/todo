package application.dao;

import application.db.DataSource;
import application.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "taskDAO")
public class TaskDAO implements DAO<Task> {

    private final DataSource dataSource;

    @Autowired(required = true)
    public TaskDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Task> getAll() throws SQLException {
        Connection con = dataSource.getConnection();
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM `tasks`";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                list.add(new Task(result.getInt(1), result.getString(2),
                        result.getString(3), result.getBoolean(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Task add(Task task) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "INSERT INTO `tasks` (`title`, `description`, `done`) VALUE(?,?,?)";
        PreparedStatement stmt = null;
        int id = 0;
        Task curTask = new Task();
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setBoolean(3, task.getDone());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            id = rs.next() ? rs.getInt(1) : id;
            curTask = id > 0 ? getById(id) : curTask;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curTask;
    }

    @Override
    public boolean update(Task task) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "UPDATE `tasks` set `title`=?, `description`=?, `done`=? where `id`=?";
        PreparedStatement stmt = null;
        boolean done = false;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setBoolean(3, task.getDone());
            done = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return done;
    }

    @Override
    public void removeById(int id) throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "DELETE  FROM `tasks` where `id`=?";
        PreparedStatement stmt = null;
        boolean done = false;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            done = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task getById(int id) {
        Connection con = dataSource.getConnection();
        Task task = null;
        String sql = "SELECT * FROM `tasks` WHERE id=?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                task = new Task(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getBoolean(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }
}
