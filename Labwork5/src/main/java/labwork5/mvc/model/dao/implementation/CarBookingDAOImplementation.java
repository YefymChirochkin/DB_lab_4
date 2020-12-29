package labwork5.mvc.model.dao.implementation;

import labwork5.mvc.DBConnector;
import labwork5.mvc.HibernateUtil;
import labwork5.mvc.model.dao.IDAO;
import labwork5.mvc.model.entity.CarBooking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CarBookingDAOImplementation implements IDAO<CarBooking> {

    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static final String BOOKING_TABLE = "mydb.Car_Booking";

    private static final String FIND_ALL_QUERY = "SELECT * FROM " + BOOKING_TABLE + " ;";

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

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            booking = session.get(CarBooking.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return booking;

    }

    @Override
    public void create(CarBooking booking) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(booking);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Integer id, CarBooking booking) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(booking);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            CarBooking booking = session.get(CarBooking.class, id);
            if (booking != null) {
                session.delete(booking);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
