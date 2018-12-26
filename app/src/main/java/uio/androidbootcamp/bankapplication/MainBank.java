package uio.androidbootcamp.bankapplication;

import java.util.Scanner;

public class MainBank {


    public static void printMenu(){

        String opcMain = "Select an option:";
        String opcCreateClient = "1: Create client";
        String opcCreateAccount = "2: Create account";
        String opcAddAccountToClient = "3: Add account to client";
        String opcDoDeposit = "4: Deposit";
        String opcDoWithdraw = "5: Withdraw";
        String opcTransfer = "6: Transfer";
        String opcPrint = "7: Print clients and accounts information";
        String opcSalir = "8: Exit";

        System.out.println(opcMain);
        System.out.println(opcCreateClient);
        System.out.println(opcCreateAccount);
        System.out.println(opcAddAccountToClient);
        System.out.println(opcDoDeposit);
        System.out.println(opcDoWithdraw);
        System.out.println(opcTransfer);
        System.out.println(opcPrint);
        System.out.println(opcSalir);
    }



    public static void catchMenuOption(int selectedOption){

        switch (selectedOption){
            case 1:
                System.out.println("Opcion 1");
                break;
            case 2:
                System.out.println("Opcion 2");
                break;
            case 3:
                System.out.println("Opcion 3");
                break;
            case 4:
                System.out.println("Opcion 4");
                break;
            case 5:
                System.out.println("Opcion 5");
                break;
            case 6:
                System.out.println("Opcion 6");
                break;
            case 7:
                System.out.println("Opcion 7");
                break;
            case 8: System.exit(0);
        }
    }


    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String selectedOption ="";
        do{
            printMenu();
            selectedOption = scanner.next();
            System.out.println("selectedOption: " + selectedOption);
            catchMenuOption(Integer.valueOf(selectedOption));
        }while(!selectedOption.equals("8"));
    }
}
