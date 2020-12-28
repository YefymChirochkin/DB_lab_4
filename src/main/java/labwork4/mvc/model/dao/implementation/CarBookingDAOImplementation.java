package labwork4.mvc.model.dao.implementation;

import labwork4.mvc.DBConnector;
import labwork4.mvc.model.dao.IDAO;
import labwork4.mvc.model.entity.CarBooking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarBookingDAOImplementation implements IDAO<CarBooking> {

    private static final String BOOKING_TABLE = "mydb.Car_Booking";

    private static final String FIND_ALL_QUERY = "SELECT * FROM " + BOOKING_TABLE + " ;";

    private static final String FIND_ONE_QUERY = "SELECT * FROM " + BOOKING_TABLE + " WHERE Car_Booking_id = ?;";

    private static final String CREATE_QUERY = "INSERT INTO " + BOOKING_TABLE +
            " (Date, Time) VALUES (?, ?);";

    private static final String UPDATE_QUERY = "UPDATE " + BOOKING_TABLE +
            " SET Date = ?, Time = ? WHERE Car_Booking_id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + BOOKING_TABLE + " WHERE Car_Booking_id = ?;";

    @Override
    public List<CarBooking> findAll() throws SQLException {

        List<CarBooking> bookings = new ArrayList<>();

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(FIND_ALL_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CarBooking booking = new CarBooking(
                        resultSet.getInt("Car_Booking_id"),
                        resultSet.getString("Date"),
                        resultSet.getString("Time")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;

    }

    @Override
    public CarBooking findOne(Integer id) throws SQLException {

        CarBooking booking = null;

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(FIND_ONE_QUERY)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                booking = new CarBooking(
                        resultSet.getInt("Car_Booking_id"),
                        resultSet.getString("Date"),
                        resultSet.getString("Time")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;

    }

    @Override
    public void create(CarBooking booking) throws SQLException {

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, String.valueOf(booking.getDate()));
            statement.setString(2, String.valueOf(booking.getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Integer id, CarBooking booking) throws SQLException {

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(UPDATE_QUERY)) {

            statement.setInt(1, id);
            statement.setString(2, String.valueOf(booking.getDate()));
            statement.setString(3, String.valueOf(booking.getTime()));

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
