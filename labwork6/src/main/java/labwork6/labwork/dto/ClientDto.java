package labwork6.labwork.dto;


public class ClientDto {

    private Integer Client_id;

    private Integer Age;

    private String Name;

    private String Surname;

    public ClientDto(Integer Client_id, Integer Age, String Name, String Surname) {

        this.Client_id = Client_id;
        this.Age = Age;
        this.Name = Name;
        this.Surname = Surname;

    }

    public ClientDto() {

    }

    public Integer getClient_id() {
        return Client_id;
    }

    public Integer getAge() {
        return Age;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setClient_id(Integer client_id) {
        Client_id = client_id;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

}
