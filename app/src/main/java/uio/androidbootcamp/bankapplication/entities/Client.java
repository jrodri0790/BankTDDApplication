package uio.androidbootcamp.bankapplication.entities;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private List<AccountBank> accountsBank;
    private String name;
    private String lastName;
    private String id;

    public Client(String name, String lastName, String id) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.accountsBank = new ArrayList<>();
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
        this.accountsBank.add(account);
    }

    public List<AccountBank> getAccountsBank() {
        return this.accountsBank;
    }
}
