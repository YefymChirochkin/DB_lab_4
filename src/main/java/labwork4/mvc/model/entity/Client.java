package labwork4.mvc.model.entity;

public class Client {

    private Integer Client_id;
    private Integer Age;
    private String Name;
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
