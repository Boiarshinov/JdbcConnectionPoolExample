package dev.boiarshinov.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import dev.boiarshinov.model.Nonogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NonogramDAO implements DAO<Nonogram>{
    Logger logger = LoggerFactory.getLogger(NonogramDAO.class);
    ComboPooledDataSource connectionPool;

    public NonogramDAO(ComboPooledDataSource connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void add(Nonogram nonogram) throws DaoException {
        logger.debug("Adding nonogram named \"{}\" to DB", nonogram.getName());
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO nonograms (width, height, name) VALUES (?, ?, ?)");
            statement.setByte(1, nonogram.getWidth());
            statement.setByte(2, nonogram.getHeight());
            statement.setString(3, nonogram.getName());
            statement.executeUpdate();
            logger.debug("Nonogram \"{}\" added to DB", nonogram.getName());
        } catch (SQLException e){
            logger.error("Can't add nonogram to DB", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Nonogram> getAll() throws DaoException {
        List<Nonogram> list = new ArrayList<>();
        logger.debug("Getting all nonograms from DB");

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
            logger.debug("Extraction complete with number of nonograms = {}", list.size());

            return list;

        } catch (SQLException e){
            logger.error("Can't get all Nonograms from DB", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {
        logger.debug("Deleting nonogram with id = {}", id);
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM nonograms WHERE id = ?");
            statement.setInt(1, id);
            boolean successStatus = statement.execute();
            logger.debug("Deleting complete with status = {}", successStatus);

        } catch (SQLException e){
            logger.error("Can't delete nonogram by id from DB", e);
            throw new DaoException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        logger.debug("Getting connection from pool");
        return connectionPool.getConnection();
    }
}
