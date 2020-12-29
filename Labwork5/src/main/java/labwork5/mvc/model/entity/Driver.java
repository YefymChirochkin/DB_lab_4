package labwork5.mvc.model.entity;


import javax.persistence.*;


@Entity
@Table(name = "Driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Driver_id")
    private Integer Driver_id;
    @Column(name = "Age")
    private Integer Age;
    @Column(name = "Driving_experience")
    private Integer Driving_experience;
    @Column(name = "License_number")
    private Integer License_number;
    @Column(name = "Category")
    private String Category;
    @Column(name = "Total_number_of_trips")
    private Integer Total_number_of_trips;
    @Column(name = "Driver_rating_Average_driver_rating")
    private Integer Driver_rating_Average_driver_rating;

    public Driver(
            Integer Driver_id, Integer Age, Integer Driving_experience, Integer License_number, String Category, Integer Total_number_of_trips, Integer Driver_rating_Average_driver_rating
    ) {

        this.Driver_id = Driver_id;
        this.Age = Age;
        this.Driving_experience = Driving_experience;
        this.License_number = License_number;
        this.Category = Category;
        this.Total_number_of_trips = Total_number_of_trips;
        this.Driver_rating_Average_driver_rating = Driver_rating_Average_driver_rating;

    }

    public Driver(
            Integer Age, Integer Driving_experience, Integer License_number, String Category, Integer Total_number_of_trips, Integer Driver_rating_Average_driver_rating
    ) {
        this(null, Age, Driving_experience, License_number, Category, Total_number_of_trips, Driver_rating_Average_driver_rating);
    }

    public Driver() {

    }

    public Integer getDriver_id() {
        return this.Driver_id;
    }

    public Integer getAge() {
        return this.Age;
    }

    public Integer getDriving_experience() {
        return this.Driving_experience;
    }

    public Integer getLicense_number() {
        return this.License_number;
    }

    public String getCategory() {
        return this.Category;
    }

    public Integer getTotal_number_of_trips() {
        return this.Total_number_of_trips;
    }

    public Integer getDriver_rating_Average_driver_rating() {
        return this.Driver_rating_Average_driver_rating;
    }

    @Override
    public String toString() {
        return "Driver {\n" +
                "\tDriver_id: " + this.Driver_id + ",\n" +
                "\tAge: " + this.Age + ",\n" +
                "\tDriving_experience: " + this.Driving_experience + ",\n" +
                "\tLicense_number: " + this.License_number + ",\n" +
                "\tCategory: " + this.Category + ",\n" +
                "\tTotal_number_of_trips: " + this.Total_number_of_trips + ",\n" +
                "\tDriver_rating_Average_driver_rating: " + this.Driver_rating_Average_driver_rating + "\n" +
                '}';
    }

}
