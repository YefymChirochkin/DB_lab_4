package labwork4.mvc.model.dao.implementation;

import labwork4.mvc.DBConnector;
import labwork4.mvc.model.dao.IDAO;
import labwork4.mvc.model.entity.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DriverDAOImplementation implements IDAO<Driver> {

    private static final String DRIVER_TABLE = "mydb.Driver";

    private static final String FIND_ALL_QUERY = "SELECT * FROM " + DRIVER_TABLE + " ;";

    private static final String FIND_ONE_QUERY = "SELECT * FROM " + DRIVER_TABLE + " WHERE Driver_id = ?;";

    private static final String CREATE_QUERY = "INSERT INTO " + DRIVER_TABLE +
            " (Age, Driving_experience, License_number, Category, Total_number_of_trips, Driver_rating_Average_driver_rating) VALUES (?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_QUERY = "UPDATE " + DRIVER_TABLE +
            " SET Age = ?, Driving_experience = ?, License_number = ?, Category = ?, Total_number_of_trips = ?, Driver_rating_Average_driver_rating = ? WHERE Driver_id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + DRIVER_TABLE + " WHERE Driver_id = ?;";

    @Override
    public List<Driver> findAll() throws SQLException {

        List<Driver> drivers = new ArrayList<>();

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(FIND_ALL_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Driver driver = new Driver(
                        resultSet.getInt("Driver_id"),
                        resultSet.getInt("Age"),
                        resultSet.getInt("Driving_experience"),
                        resultSet.getInt("License_number"),
                        resultSet.getString("Category"),
                        resultSet.getInt("Total_number_of_trips"),
                        resultSet.getInt("Driver_rating_Average_driver_rating")
                );
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drivers;
        
    }

    @Override
    public Driver findOne(Integer id) throws SQLException {

        Driver driver = null;

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(FIND_ONE_QUERY)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                driver = new Driver(
                        resultSet.getInt("Driver_id"),
                        resultSet.getInt("Age"),
                        resultSet.getInt("Driving_experience"),
                        resultSet.getInt("License_number"),
                        resultSet.getString("Category"),
                        resultSet.getInt("Total_number_of_trips"),
                        resultSet.getInt("Driver_rating_Average_driver_rating")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;

    }

    @Override
    public void create(Driver driver) throws SQLException {

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, driver.getAge());
            statement.setInt(2, driver.getDriving_experience());
            statement.setInt(3, driver.getLicense_number());
            statement.setString(4, String.valueOf(driver.getCategory()));
            statement.setInt(5, driver.getTotal_number_of_trips());
            statement.setInt(6, driver.getDriver_rating_Average_driver_rating());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Integer id, Driver driver) throws SQLException {

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(CREATE_QUERY)) {

            statement.setInt(1, id);
            statement.setInt(2, driver.getAge());
            statement.setInt(3, driver.getDriving_experience());
            statement.setInt(4, driver.getLicense_number());
            statement.setString(5, String.valueOf(driver.getCategory()));
            statement.setInt(6, driver.getTotal_number_of_trips());
            statement.setInt(7, driver.getDriver_rating_Average_driver_rating());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
