package uio.androidbootcamp.bankapplication.entities;

public class PrinterBank {
    public static String printClientInfo(Client client) {
         StringBuilder result = new StringBuilder(client.getName() + ", " + client.getLastName() + ", " + client.getId());
         for (AccountBank accountBank: client.getAccountsBank()){
             result.append(", ").append(printAccountInfo(accountBank));
         }
        return result.toString();

    }

    public static String printAccountInfo(AccountBank accountBank) {
        return "Cuenta: "+accountBank.getId()+", Saldo: "+accountBank.getBalance();
    }
}
