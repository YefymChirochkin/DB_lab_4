package labwork5.mvc.model.entity;


import javax.persistence.*;


@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Client_id")
    private Integer Client_id;
    @Column(name = "Age")
    private Integer Age;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Surname")
    private String Surname;

    public Client(Integer Client_id, Integer Age, String Name, String Surname) {

        this.Client_id = Client_id;
        this.Age = Age;
        this.Name = Name;
        this.Surname = Surname;

    }

    public Client(Integer Age, String Name, String Surname) {
        this(null, Age, Name, Surname);
    }

    public Client() {

    }

    public Integer getClient_id() {
        return this.Client_id;
    }

    public Integer getAge() {
        return this.Age;
    }

    public String getName() {
        return this.Name;
    }

    public String getSurname() {
        return this.Surname;
    }

    @Override
    public String toString() {
        return "Client {\n" +
                "\tClient_id: " + this.Client_id + ",\n" +
                "\tAge: " + this.Age + ",\n" +
                "\tName: " + this.Name + ",\n" +
                "\tSurname: " + this.Surname + ",\n" +
                '}';
    }

}
