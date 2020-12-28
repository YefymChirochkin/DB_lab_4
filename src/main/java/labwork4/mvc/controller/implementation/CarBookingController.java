package labwork4.mvc.controller.implementation;

import labwork4.mvc.controller.IController;
import labwork4.mvc.model.dao.implementation.CarBookingDAOImplementation;
import labwork4.mvc.model.entity.CarBooking;

import java.sql.SQLException;
import java.util.List;


public class CarBookingController implements IController<CarBooking> {

    private final CarBookingDAOImplementation bookingDAO = new CarBookingDAOImplementation();

    @Override
    public List<CarBooking> findAll() throws SQLException {
        return bookingDAO.findAll();
    }

    @Override
    public CarBooking findOne(Integer id) throws SQLException {
        return bookingDAO.findOne(id);
    }

    @Override
    public void create(CarBooking booking) throws SQLException {
        bookingDAO.create(booking);
    }

    @Override
    public void update(Integer id, CarBooking booking) throws SQLException {
        if (bookingDAO.findOne(id) == null) {
            System.out.println("Booking with such id does not exist");
        } else {
            bookingDAO.update(id, booking);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        if (bookingDAO.findOne(id) == null) {
            System.out.println("Booking with such id does not exist");
        } else {
            bookingDAO.delete(id);
        }
    }

}
