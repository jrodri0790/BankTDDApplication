package uio.androidbootcamp.bankapplication.entities;

public class Client {
    private String name;
    private String lastName;
    private String id;

    public Client(String name, String lastName, String id) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getId() {
        return this.id;
    }
}
