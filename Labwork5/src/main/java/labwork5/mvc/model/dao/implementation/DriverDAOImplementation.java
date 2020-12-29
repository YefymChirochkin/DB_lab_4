package labwork5.mvc.model.dao.implementation;

import labwork5.mvc.DBConnector;
import labwork5.mvc.HibernateUtil;
import labwork5.mvc.model.dao.IDAO;
import labwork5.mvc.model.entity.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DriverDAOImplementation implements IDAO<Driver> {

    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static final String DRIVER_TABLE = "mydb.driver";

    private static final String FIND_ALL_QUERY = "SELECT * FROM " + DRIVER_TABLE + " ;";

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

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            driver = session.get(Driver.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;

    }

    @Override
    public void create(Driver driver) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Integer id, Driver driver) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Driver driver = session.get(Driver.class, id);
            if (driver != null) {
                session.delete(driver);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
