package labwork5.mvc.model.dao.implementation;

import labwork5.mvc.DBConnector;
import labwork5.mvc.HibernateUtil;
import labwork5.mvc.model.dao.IDAO;
import labwork5.mvc.model.entity.CarBooking;
import labwork5.mvc.model.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDAOImplementation implements IDAO<Client> {

    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static final String CLIENT_TABLE = "mydb.Client";

    private static final String FIND_ALL_QUERY = "SELECT * FROM " + CLIENT_TABLE+ " ;";

    @Override
    public List<Client> findAll() throws SQLException {

        List<Client> clients = new ArrayList<>();

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(FIND_ALL_QUERY)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("Client_id"),
                        resultSet.getInt("Age"),
                        resultSet.getString("Name"),
                        resultSet.getString("Surname")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;

    }

    @Override
    public Client findOne(Integer id) throws SQLException {

        Client client = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            client = session.get(Client.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;

    }

    @Override
    public void create(Client client) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Integer id, Client client) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.delete(client);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
