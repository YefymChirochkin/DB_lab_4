package labwork4.mvc.model.dao.implementation;

import labwork4.mvc.DBConnector;
import labwork4.mvc.model.dao.IDAO;
import labwork4.mvc.model.entity.CarBooking;
import labwork4.mvc.model.entity.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDAOImplementation implements IDAO<Client> {

    private static final String CLIENT_TABLE = "mydb.Client";

    private static final String FIND_ALL_QUERY = "SELECT * FROM " + CLIENT_TABLE + " ;";

    private static final String FIND_ONE_QUERY = "SELECT * FROM " + CLIENT_TABLE + " WHERE Client_id = ?;";

    private static final String CREATE_QUERY = "INSERT INTO " + CLIENT_TABLE +
            " (Age, Name, Surname) VALUES (?, ?, ?);";

    private static final String UPDATE_QUERY = "UPDATE " + CLIENT_TABLE +
            " SET Age = ?, Name = ?, Surname = ? WHERE Client_id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + CLIENT_TABLE + " WHERE Client_id = ?;";

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

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(FIND_ONE_QUERY)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = new Client(
                        resultSet.getInt("Client_id"),
                        resultSet.getInt("Age"),
                        resultSet.getString("Name"),
                        resultSet.getString("Surname")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;

    }

    @Override
    public void create(Client client) throws SQLException {

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, client.getAge());
            statement.setString(2, String.valueOf(client.getName()));
            statement.setString(3, String.valueOf(client.getSurname()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Integer id, Client client) throws SQLException {

        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(UPDATE_QUERY)) {

            statement.setInt(1, id);
            statement.setInt(2, client.getAge());
            statement.setString(3, String.valueOf(client.getName()));
            statement.setString(4, String.valueOf(client.getSurname()));

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
