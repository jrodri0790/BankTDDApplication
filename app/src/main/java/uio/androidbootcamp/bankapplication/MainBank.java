package uio.androidbootcamp.bankapplication;

import uio.androidbootcamp.bankapplication.entities.AccountBank;
import uio.androidbootcamp.bankapplication.entities.Client;
import uio.androidbootcamp.bankapplication.entities.CurrentAccount;
import uio.androidbootcamp.bankapplication.exceptions.*;
import uio.androidbootcamp.bankapplication.services.PrinterBank;
import uio.androidbootcamp.bankapplication.entities.SavingsAccount;
import uio.androidbootcamp.bankapplication.services.ServiceBankAdmin;

import java.util.Scanner;

public class MainBank {

    static Scanner scanner = new Scanner(System.in);
    static ServiceBankAdmin serviceBankAdmin = new ServiceBankAdmin();


    public static void printMenu(){

        String opcMain = "Select an option:";
        String opcCreateClient = "1: Create client";
        String opcCreateAccount = "2: Create account";
        String opcAddAccountToClient = "3: Add account to client";
        String opcDoDeposit = "4: Deposit";
        String opcDoWithdraw = "5: Withdraw";
        String opcTransfer = "6: Transfer";
        String opcPrint = "7: Print clients and accounts information";
        String opcPrintFreeAccounts = "8: Print free accounts information";
        String opcSalir = "9: Exit";

        System.out.println(opcMain);
        System.out.println(opcCreateClient);
        System.out.println(opcCreateAccount);
        System.out.println(opcAddAccountToClient);
        System.out.println(opcDoDeposit);
        System.out.println(opcDoWithdraw);
        System.out.println(opcTransfer);
        System.out.println(opcPrint);
        System.out.println(opcPrintFreeAccounts);
        System.out.println(opcSalir);
    }




    private static void catchMenuOption(int selectedOption) throws ClientNotFoundException, AccountBankNotFoundException, NegativeValuesException, ValueUpperBalanceException, ValueUpper2000Exception, ValueUpper1000Exception {

        switch (selectedOption){
            case 1:
                createClient();
                break;
            case 2:
                createAccounts();
                break;
            case 3:
                addAccountToClient();
                break;
            case 4:
                deposit();
                break;
            case 5:
                withdraw();
                break;
            case 6:
                System.out.println("transfer();");
                break;
            case 7:
                showClientInfo();
                break;
            case 8:
                showAvailableAccountInfo();
                break;
            case 9: System.exit(0);
        }
    }


    private static void withdraw() throws ClientNotFoundException, AccountBankNotFoundException, ValueUpper2000Exception, ValueUpperBalanceException, NegativeValuesException, ValueUpper1000Exception {
        String idClient = "";
        String idCuenta ="";
        String depositQuantity = "";
        AccountBank accountBankResult = null;
        System.out.println("Digite id del dueno de la cuenta");
        idClient = scanner.next();
        System.out.println("Digite id de la cuenta");
        idCuenta = scanner.next();
        AccountBank accountBank = serviceBankAdmin.searchAccountOfClientGivenAccountId(idClient, idCuenta);
        System.out.println("Digite la cantidad a retirar");
        depositQuantity = scanner.next();
        if(accountBank instanceof CurrentAccount){
            accountBankResult = serviceBankAdmin.withdrawFromCurrentAccount(Double.valueOf(depositQuantity),(CurrentAccount)accountBank);
            serviceBankAdmin.searchClient(idClient).getAccountsBank().remove(accountBank);
            serviceBankAdmin.searchClient(idClient).getAccountsBank().add(accountBankResult);
        }
        if(accountBank instanceof SavingsAccount){
            accountBankResult = serviceBankAdmin.withdrawFromSavingAccount(Double.valueOf(depositQuantity), (SavingsAccount)accountBank);
            serviceBankAdmin.searchClient(idClient).getAccountsBank().remove(accountBank);
            serviceBankAdmin.searchClient(idClient).getAccountsBank().add(accountBankResult);
        }
    }

    private static void deposit() throws ClientNotFoundException, AccountBankNotFoundException, NegativeValuesException {
        String idClient = "";
        String idCuenta ="";
        String depositQuantity = "";
        AccountBank accountBankResult = null;
        System.out.println("Digite id del dueno de la cuenta");
        idClient = scanner.next();
        System.out.println("Digite id de la cuenta");
        idCuenta = scanner.next();
        AccountBank accountBank = serviceBankAdmin.searchAccountOfClientGivenAccountId(idClient, idCuenta);
        System.out.println("Digite la cantidad a depositar");
        depositQuantity = scanner.next();
        if(accountBank instanceof CurrentAccount){
            accountBankResult = serviceBankAdmin.depositToCurrentAccount(Integer.valueOf(depositQuantity),(CurrentAccount)accountBank);
            serviceBankAdmin.searchClient(idClient).getAccountsBank().remove(accountBank);
            serviceBankAdmin.searchClient(idClient).getAccountsBank().add(accountBankResult);
        }
        if(accountBank instanceof SavingsAccount){
            accountBankResult = serviceBankAdmin.depositToSavingAccount(Integer.valueOf(depositQuantity), (SavingsAccount)accountBank);
            serviceBankAdmin.searchClient(idClient).getAccountsBank().remove(accountBank);
            serviceBankAdmin.searchClient(idClient).getAccountsBank().add(accountBankResult);
        }
    }

    private static void addAccountToClient() throws ClientNotFoundException, AccountBankNotFoundException {
        String idClient = "";
        String idCuenta ="";
        System.out.println("Digite id del cliente a quien desea asociar una cuenta");
        idClient = scanner.next();
        System.out.println("Digite id de la cuenta que desea asociar");
        idCuenta = scanner.next();
        serviceBankAdmin.addAccountToClient(serviceBankAdmin.searchClient(idClient), serviceBankAdmin.searchAccountBank(idCuenta));
    }

    private static void  showAvailableAccountInfo() {
        System.out.println("Los cuentas disponibles son:");
        for(CurrentAccount currentAccount: serviceBankAdmin.getCurrentAccounts()){
            System.out.println(PrinterBank.printAccountInfo(currentAccount));
        }
        for(SavingsAccount savingsAccount: serviceBankAdmin.getSavingAccounts()){
            System.out.println(PrinterBank.printAccountInfo(savingsAccount));
        }
    }

    private static void createAccounts() {
        String idAccount = "";
        String type = "";
        System.out.println("Opcion 2");
        System.out.println("Seleccione 1 para cuenta de ahorros o 2 para cuenta corriente");
        type = scanner.next();
        int typeInteger = Integer.parseInt(type);
        if (typeInteger < 1 || typeInteger > 2) {
            System.out.println("Opcion no valida");
        }else{
            System.out.println("Ingrese el numero de la cuenta");
            createAccount(typeInteger);
        }
    }

    private static void createAccount(int option){
        String idAccount = scanner.next();
        if(option == 1){
            serviceBankAdmin.createSavingAccount(idAccount);
        }else{
            serviceBankAdmin.createCurrentAccount(idAccount);
        }
        System.out.println("Cuenta creada");
    }

    private static void showClientInfo() {
        System.out.println("Los datos de los clientes son:");
        for(Client client: serviceBankAdmin.getClients()){
            System.out.println(PrinterBank.printClientInfo(client));
        }
    }

    private static void createClient() {
        String name = "";
        String lastName = "";
        String identification = "";
        System.out.println("Ingrese los datos del cliente: nombre");
        name = scanner.next();
        System.out.println("Ingrese los datos del cliente: apellido");
        lastName = scanner.next();
        System.out.println("Ingrese los datos del cliente: identificacion");
        identification = scanner.next();
        serviceBankAdmin.createClient(name, lastName, identification);
        System.out.println("El usuario ha sido guardado.");
    }


    public static void main(String[] args) throws AccountBankNotFoundException, ClientNotFoundException, NegativeValuesException, ValueUpper2000Exception, ValueUpperBalanceException, ValueUpper1000Exception {
        String selectedOption ="";
        do{
            printMenu();
            selectedOption = scanner.next();
            System.out.println("selectedOption: " + selectedOption);
            catchMenuOption(Integer.valueOf(selectedOption));
        }while(!selectedOption.equals("9"));
    }
}
