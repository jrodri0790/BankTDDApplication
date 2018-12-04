package uio.androidbootcamp.bankapplication.entities;

import java.util.List;

public class Client {
    public List<AccountBank> listAccounsBank;
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

    public void addOneAccount(AccountBank account) {
        this.listAccounsBank.add(account);
    }
}
