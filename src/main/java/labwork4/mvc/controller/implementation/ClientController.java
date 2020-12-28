package labwork4.mvc.controller.implementation;

import labwork4.mvc.controller.IController;
import labwork4.mvc.model.dao.implementation.ClientDAOImplementation;
import labwork4.mvc.model.entity.Client;

import java.sql.SQLException;
import java.util.List;


public class ClientController implements IController<Client> {

    private final ClientDAOImplementation clientDAO = new ClientDAOImplementation();

    @Override
    public List<Client> findAll() throws SQLException {
        return clientDAO.findAll();
    }

    @Override
    public Client findOne(Integer id) throws SQLException {
        return clientDAO.findOne(id);
    }

    @Override
    public void create(Client client) throws SQLException {
        clientDAO.create(client);
    }

    @Override
    public void update(Integer id, Client client) throws SQLException {
        if (clientDAO.findOne(id) == null) {
            System.out.println("Client with such id does not exist");
        } else {
            clientDAO.update(id, client);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        if (clientDAO.findOne(id) == null) {
            System.out.println("Client with such id does not exist");
        } else {
            clientDAO.delete(id);
        }
    }
}
