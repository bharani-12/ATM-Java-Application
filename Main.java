import java.util.*;
public class Main {
    public static void main(String[] args) {
        try(Scanner scanner=new Scanner(System.in);) {
            AtmDB atmDatabase=new AtmDB();
            ManageAtm handleAtm=new ManageAtm();
            ManageCustomer handleCustomer=new ManageCustomer();

            TreeMap<String, CustomerDB> db=new TreeMap<>();
            int[] notes=new int[]{2000, 500, 100};
            ManageDenomination Denomination=new ManageDenomination();
            while (true){
                int option=0;
                System.out.println("Choose any Option(Before ATM Withdraw...You need to Load ATM)\n1. Load ATM\n2. Withdraw From ATM\n3. Check ATM Balance\n4. Create Account\n5. Transfer\n6. Check Account Balance\n7. Display All Customer Details\n8. Deposit\n9. End");
                option=scanner.nextInt();
                System.out.println();
                scanner.nextLine();
                switch (option){
                    case 1:
                    {
                        System.out.println("--------    Load ATM    --------");
                        System.out.println("Enter the Denomination to deposit(2000:10, 500:5) : ");
                        String[] denominations=scanner.nextLine().split(",");
                        int flag=1;
                        for(String seperate:denominations){
                            String[] values=seperate.split(":");
                            int amount=Integer.valueOf(values[0].trim());
                            int denomination=Integer.valueOf(values[1]);
                            if(amount<0||denomination<0){
                                System.out.println("Incorrect Deposit amount");
                            }
                            else if (amount==0||denomination==0){
                                System.out.println("Deposit amount cannot be Zero");
                            }
                            else{
                                handleAtm.updateDenomination(amount, denomination, Denomination);
                            }
                        }

                            handleAtm.updateDepositingAmount(atmDatabase, Denomination);


                        System.out.println("Denomination            Number  Value       ");
                        System.out.println("2000                    "+Denomination.getTwoThousand()+"       "+2000*Denomination.getTwoThousand());
                        System.out.println("500                     "+Denomination.getFiveHundred()+"       "+500*Denomination.getFiveHundred());
                        System.out.println("100                     "+Denomination.getOneHundred()+"       "+100*Denomination.getOneHundred());
                        break;
                    }
                    case 2:
                    {
                        System.out.println("--------    Withdraw    --------");

                        System.out.println("Enter the Account Number : ");
                        String accountNumber=scanner.next();
                        System.out.println("Enter the Pin Number : ");
                        String pinNumber=scanner.next();
                        if(handleCustomer.validAccountNumber(accountNumber, db)&&handleCustomer.validPinNumber(accountNumber, pinNumber, db)){
                            System.out.println("Enter the amount to Withdraw : ");
                            int withdrawAmount=scanner.nextInt();
                            if(withdrawAmount<=0||withdrawAmount>atmDatabase.getBalaceAmount()){
                                System.out.println("Incorrect or Insufficient Funds");break;
                            }
                            else if(db.get(accountNumber).getAccountBalance()>10000||db.get(accountNumber).getAccountBalance()<100){
                                System.out.println("Withdraw Amount should maximum 10000 and minimum 100");break;
                            }
                            db.get(accountNumber).withdraw(accountNumber, withdrawAmount, db);
                            int flag=1;
                            int[] dispensingDenominations=handleAtm.dispensingDenomination(notes, withdrawAmount);
                            for(int i=0;i< notes.length;i++){
                                if(dispensingDenominations[i]>0){
                                    flag=handleAtm.reduceDenomination(notes[i], dispensingDenominations[i], Denomination);
                                }
                            }
                            System.out.println();
                            if(flag==1)
                                handleAtm.updateWithdraw(atmDatabase, withdrawAmount);
                            else {
                                System.out.println("No Available Denomination");
                                break;
                            }
                        }
                        else{
                            System.out.println("Invalid Acoount Number or Pin Number");break;
                        }
                        break;
                    }
                    case 3:
                    {
                        int currentAtmBalance=atmDatabase.getBalaceAmount();
                        if(currentAtmBalance<=0){
                            System.out.println("No Fund");
                            continue;
                        }
                        else{
                            System.out.println("-------- Current ATM Balance --------");
                            System.out.println("Denomination            Number  Value       ");
                            System.out.println("2000                    "+Denomination.getTwoThousand()+"       "+2000*Denomination.getTwoThousand());
                            System.out.println("500                     "+Denomination.getFiveHundred()+"       "+500*Denomination.getFiveHundred());
                            System.out.println("100                     "+Denomination.getOneHundred()+"       "+100*Denomination.getOneHundred());
                            System.out.println("Total Amount available in ATM is = Rs. "+atmDatabase.getBalaceAmount());
                        }
                        break;
                    }
                    case 4:
                    {
                        System.out.println("-------- Create Savings Account -------- ");
                        System.out.println("Enter the New Account Number : ");
                        String accountNumber=scanner.nextLine();
                        System.out.println("Enter the Customer Name : ");
                        String customerName=scanner.nextLine();
                        System.out.println("Enter the New Pin Number : ");
                        String pinNumber=scanner.nextLine();
                        System.out.println("Enter the Amount to Deposit : ");
                        int acoountBalance=scanner.nextInt();
                        if(acoountBalance>=500){
                            CustomerDB customerDatabase=new CustomerDB(accountNumber,customerName, pinNumber, acoountBalance);
                            db.put(accountNumber, customerDatabase);
                        }
                        else{
                            System.out.println("Minimum Balance Must be 500 or above");
                            break;
                        }
                        break;
                    }
                    case 5:
                    {
                        System.out.println("--------    Money Transfer    --------");

                        System.out.println("Enter the Account Number : ");
                        String fromAccountNumber=scanner.next();
                        System.out.println("Enter the Pin Number : ");
                        String fromPinNumber=scanner.next();
                        if(handleCustomer.validAccountNumber(fromAccountNumber, db)&&handleCustomer.validPinNumber(fromAccountNumber, fromPinNumber, db)){
                            System.out.println("Enter the Account Number to make Transfer : ");
                            String toAccountNumber=scanner.next();
                            System.out.println("Enter the Amount to Transfer : ");
                            int transferAmount=scanner.nextInt();
                            handleCustomer.transferAmount(fromAccountNumber, toAccountNumber, transferAmount, db);
                        }
                        else{
                            System.out.println("Invalid Account Number or Pin Number");
                            break;
                        }
                        break;
                    }
                    case 6:
                    {
                        System.out.println("--------    Check Account Balance    --------");

                        System.out.println("Enter the Account Number : ");
                        String accountNumber=scanner.next();
                        System.out.println("Enter the Pin Number : ");
                        String pinNumber=scanner.next();
                        if(handleCustomer.validAccountNumber(accountNumber, db)&&handleCustomer.validPinNumber(accountNumber, pinNumber, db)){
                            System.out.println("AccNo  AccountHolder    PinNumber AccountBalance");
                            System.out.println(accountNumber+"        "+db.get(accountNumber).getCustomerName()+"        "+pinNumber+"        "+db.get(accountNumber).getAccountBalance());
                        }
                        else{
                            System.out.println("Invalid Account Number or Pin Number");
                            break;
                        }
                        break;
                    }
                    case 7:
                    {
                        System.out.println("-------- Customer Details ---------");
                        System.out.println("AccNo    Account Holder    Pin Number    Account Balance");
                        for(Map.Entry<String, CustomerDB> entry: db.entrySet()){
                            System.out.println(entry.getValue().getAccountNumber()+"       "+entry.getValue().getCustomerName()+"        "+entry.getValue().getPinNumber()+"        "+entry.getValue().getAccountBalance());
                        }
                        break;
                    }
                    case 8:
                    {
                        System.out.println("-------- Deposit --------");
                        System.out.println("Enter the Account Number : ");
                        String accountNumber=scanner.next();
                        if(handleCustomer.validAccountNumber(accountNumber, db)){
                            System.out.println("Enter the Amount to Deposit : ");
                            int depositAmount=scanner.nextInt();
                            db.get(accountNumber).deposit(accountNumber, depositAmount, db);
                            System.out.println("Your Current Account Balance is Rs. "+db.get(accountNumber).getAccountBalance());
                        }
                        else{
                            System.out.println("Please Enter valid Account Number...");break;
                        }break;
                    }
                    case 9:
                    {
                        System.out.println("Thank You!");
                        System.exit(0);
                    }
                    default:
                    {
                        System.out.println("Please Enter valid option...");
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Something went Wrong , Please check your input ");
        }
    }


}
