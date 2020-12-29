package labwork5.mvc.controller.implementation;

import labwork5.mvc.controller.IController;
import labwork5.mvc.model.dao.implementation.DriverDAOImplementation;
import labwork5.mvc.model.entity.Driver;

import java.sql.SQLException;
import java.util.List;


public class DriverController implements IController<Driver> {

    private final DriverDAOImplementation driverDAO = new DriverDAOImplementation();

    @Override
    public List<Driver> findAll() throws SQLException {
        return driverDAO.findAll();
    }

    @Override
    public Driver findOne(Integer id) throws SQLException {
        return driverDAO.findOne(id);
    }

    @Override
    public void create(Driver driver) throws SQLException {
        driverDAO.create(driver);
    }

    @Override
    public void update(Integer id, Driver driver) throws SQLException {
        if (driverDAO.findOne(id) == null) {
            System.out.println("Driver with such id does not exist");
        } else {
            driverDAO.update(id, driver);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        if (driverDAO.findOne(id) == null) {
            System.out.println("Driver with such id does not exist");
        } else {
            driverDAO.delete(id);
        }
    }
}
