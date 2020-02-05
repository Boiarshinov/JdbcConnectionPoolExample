package dev.boiarshinov.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import dev.boiarshinov.model.Nonogram;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NonogramDAO {
    ComboPooledDataSource connectionPool;

    public NonogramDAO(ComboPooledDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    public boolean add(Nonogram nonogram){
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO nonograms (width, height, name) VALUES (?, ?, ?)");
            preparedStatement.setByte(1, nonogram.getWidth());
            preparedStatement.setByte(2, nonogram.getHeight());
            preparedStatement.setString(3, nonogram.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Nonogram> getAll(){
        List<Nonogram> list = new ArrayList<>();

        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM nonograms");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                byte width = resultSet.getByte("width");
                byte height = resultSet.getByte("height");
                Date creationTime = new Date(resultSet.getTimestamp("creation_time").getTime());

                Nonogram nonogram = new Nonogram(id, name, width, height, creationTime);
                list.add(nonogram);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteById(int id){
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM nonograms WHERE id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private Connection getConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        return connection;
    }
}