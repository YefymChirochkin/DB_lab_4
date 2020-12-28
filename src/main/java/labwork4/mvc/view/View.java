package labwork4.mvc.view;

import labwork4.mvc.controller.implementation.*;
import labwork4.mvc.model.entity.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;


public class View {

    private final DriverController driverController = new DriverController();
    private final CarBookingController bookingController = new CarBookingController();
    private final ClientController clientController = new ClientController();

    private final LinkedHashMap<String, Printable> menu = new LinkedHashMap<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    public View() {

        System.out.println("Welcome user!\n");

        menu.put("11", this::getAllDrivers);
        menu.put("12", this::getDriverById);
        menu.put("13", this::createDriver);
        menu.put("14", this::updateDriver);
        menu.put("15", this::deleteDriverById);

        menu.put("21", this::getAllClients);
        menu.put("22", this::getClientById);
        menu.put("23", this::createClient);
        menu.put("24", this::updateClient);
        menu.put("25", this::deleteClientById);

        menu.put("31", this::getAllBookings);
        menu.put("32", this::getBookingById);
        menu.put("33", this::createBooking);
        menu.put("34", this::updateBooking);
        menu.put("35", this::deleteBookingById);

    }

    public void show() throws SQLException {

        String userChoice = "";
        System.out.println("Choose table you want to work with: ");
        System.out.println("1 - driver");
        System.out.println("2 - client");
        System.out.println("3 - Car_Booking");
        System.out.println("\nEnter table: ");
        userChoice += SCANNER.next();
        System.out.println();
        System.out.println("Choose crud option you want to use on this table: ");
        System.out.println("1 - get all records from table");
        System.out.println("2 - get one record by its id");
        System.out.println("3 - create record");
        System.out.println("4 - update record");
        System.out.println("5 - delete record");
        System.out.println("\nEnter option: ");
        userChoice += SCANNER.next();
        menu.get(userChoice).print();
        System.out.println();
        exitProgramOrContinue();
        show();

    }

    private void exitProgramOrContinue() {
        System.out.println("\n\nDo you want to continue: y/n");
        String answer = SCANNER.next();
        if (answer.equals("y")) {
            System.out.println('\n');
        } else if (answer.equals("n")) {
            System.exit(0);
        } else {
            System.out.println("Enter \"y\" (as yes) or \"n\" (as no) please");
            exitProgramOrContinue();
        }
    }

    private void getAllClients() throws SQLException {
        List<Client> clients = clientController.findAll();
        System.out.println("Here are your clients:\n");
        for (Client client : clients) {
            System.out.println(client.toString() + "\n");
        }
    }

    private void getClientById() throws SQLException {
        System.out.println("Enter please client id you want to get: ");
        Integer id = SCANNER.nextInt();
        System.out.println("Here is your client: " + clientController.findOne(id).toString());
    }

    private void createClient() throws SQLException {

        System.out.println("Enter client's age: ");
        Integer Age = SCANNER.nextInt();
        System.out.println("Enter client's name: ");
        String Name = SCANNER.next();
        System.out.println("Enter client's surname: ");
        String Surname = SCANNER.next();
        Client client = new Client(Age, Name, Surname);
        clientController.create(client);
        System.out.println("Record created successfully");

    }

    private void updateClient() throws SQLException {

        System.out.println("Enter please client id you want to change: ");
        Integer id = SCANNER.nextInt();
        if (clientController.findOne(id) == null) {
            System.out.println("Record with such id does not exist in this table");
            return;
        }

        System.out.println("Enter client's age: ");
        Integer Age = SCANNER.nextInt();
        System.out.println("Enter client's name: ");
        String Name = SCANNER.next();
        System.out.println("Enter client's surname: ");
        String Surname = SCANNER.next();
        Client client = new Client(Age, Name, Surname);
        clientController.create(client);
        System.out.println("Record created successfully");

        clientController.update(id, client);
        System.out.println("Record updated successfully");

    }

    private void deleteClientById() throws SQLException {

        System.out.println("Enter please client id you want to delete: ");
        Integer id = SCANNER.nextInt();
        if (clientController.findOne(id) == null) {
            System.out.println("Record with such id does not exist in this table");
            return;
        }
        clientController.delete(id);
        System.out.println("Deleted successfully");
    }


    private void getAllBookings() throws SQLException {
        List<CarBooking> bookings = bookingController.findAll();
        System.out.println("Here are your bookings:\n");
        for (CarBooking booking : bookings) {
            System.out.println(booking.toString() + "\n");
        }
    }

    private void getBookingById() throws SQLException {
        System.out.println("Enter please booking id you want to get: ");
        Integer id = SCANNER.nextInt();
        System.out.println("Here is your booking: " + bookingController.findOne(id).toString());
    }

    private void createBooking() throws SQLException {

        System.out.println("Enter bookings's date: ");
        String date = SCANNER.next();
        System.out.println("Enter booking's time: ");
        String time = SCANNER.next();
        CarBooking booking = new CarBooking(date, time);
        bookingController.create(booking);
        System.out.println("Record created successfully");

    }

    private void updateBooking() throws SQLException {

        System.out.println("Enter please booking id you want to change: ");
        Integer id = SCANNER.nextInt();
        if (bookingController.findOne(id) == null) {
            System.out.println("Record with such id does not exist in this table");
            return;
        }

        System.out.println("Enter bookings's date: ");
        String date = SCANNER.next();
        System.out.println("Enter booking's time: ");
        String time = SCANNER.next();
        CarBooking booking = new CarBooking(date, time);
        bookingController.create(booking);

        bookingController.update(id, booking);
        System.out.println("Record updated successfully");

    }

    private void deleteBookingById() throws SQLException {

        System.out.println("Enter please booking id you want to delete: ");
        Integer id = SCANNER.nextInt();
        if (bookingController.findOne(id) == null) {
            System.out.println("Record with such id does not exist in this table");
            return;
        }
        bookingController.delete(id);
        System.out.println("Deleted successfully");
    }


    private void getAllDrivers() throws SQLException {
        List<Driver> drivers = driverController.findAll();
        System.out.println("Here are your drivers:\n");
        for (Driver driver : drivers) {
            System.out.println(driver.toString() + "\n");
        }
    }

    private void getDriverById() throws SQLException {
        System.out.println("Enter please driver id you want to get: ");
        Integer id = SCANNER.nextInt();
        System.out.println("Here is your driver: " + driverController.findOne(id).toString());
    }

    private void createDriver() throws SQLException {

        System.out.println("Enter driver's age: ");
        Integer Age = SCANNER.nextInt();
        System.out.println("Enter driver's experience: ");
        Integer Driving_experience = SCANNER.nextInt();
        System.out.println("Enter driver's license number: ");
        Integer License_number = SCANNER.nextInt();
        System.out.println("Enter driver's category: ");
        String Category = SCANNER.next();
        System.out.println("Enter total number of driver's trips: ");
        Integer Total_number_of_trips = SCANNER.nextInt();
        System.out.println("Enter average driver rating: ");
        Integer Driver_rating_Average_driver_rating = SCANNER.nextInt();
        Driver driver = new Driver(
                Age, Driving_experience, License_number, Category, Total_number_of_trips, Driver_rating_Average_driver_rating
        );
        driverController.create(driver);
        System.out.println("Record created successfully");

    }

    private void updateDriver() throws SQLException {

        System.out.println("Enter please driver id you want to change: ");
        Integer id = SCANNER.nextInt();
        if (driverController.findOne(id) == null) {
            System.out.println("Record with such id does not exist in this table");
            return;
        }

        System.out.println("Enter driver's age: ");
        Integer Age = SCANNER.nextInt();
        System.out.println("Enter driver's experience: ");
        Integer Driving_experience = SCANNER.nextInt();
        System.out.println("Enter driver's license number: ");
        Integer License_number = SCANNER.nextInt();
        System.out.println("Enter driver's category: ");
        String Category = SCANNER.next();
        System.out.println("Enter total number of driver's trips: ");
        Integer Total_number_of_trips = SCANNER.nextInt();
        System.out.println("Enter average driver rating: ");
        Integer Driver_rating_Average_driver_rating = SCANNER.nextInt();
        Driver driver = new Driver(
                Age, Driving_experience, License_number, Category, Total_number_of_trips, Driver_rating_Average_driver_rating
        );

        driverController.update(id, driver);
        System.out.println("Record updated successfully");

    }

    private void deleteDriverById() throws SQLException {

        System.out.println("Enter please driver id you want to delete: ");
        Integer id = SCANNER.nextInt();
        if (driverController.findOne(id) == null) {
            System.out.println("Record with such id does not exist in this table");
            return;
        }
        driverController.delete(id);
        System.out.println("Deleted successfully");
    }

}
