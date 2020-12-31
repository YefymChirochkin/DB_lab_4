package labwork5.mvc.model.entity;

import javax.persistence.*;


@Entity
@Table(name = "Car_Booking")
public class CarBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Car_Booking_id")
    private Integer Car_Booking_id;

    @Column(name = "Date")
    private String Date;

    @Column(name = "Time")
    private String Time;

    public CarBooking(Integer Car_Booking_id, String Date, String Time) {

        this.Car_Booking_id = Car_Booking_id;
        this.Date = Date;
        this.Time = Time;

    }

    public CarBooking(String Date, String Time) {

        this(null, Date, Time);

    }

    public CarBooking() {

    }

    public Integer getCar_Booking_id() {
        return this.Car_Booking_id;
    }

    public String getDate() {
        return this.Date;
    }

    public String getTime() {
        return this.Time;
    }

    @Override
    public String toString() {
        return "Car_Booking {\n" +
                "\tCar_Booking_id: " + this.Car_Booking_id + ",\n" +
                "\tDate: " + this.Date + ",\n" +
                "\tTime: " + this.Time + ",\n" +
                '}';
    }

}
