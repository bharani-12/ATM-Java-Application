import java.util.*;
public class CustomerDB {
    private  String accountNumber;
    private String customerName;
    private String pinNumber;
    private int accountBalance;


    public CustomerDB(){

    }

    public CustomerDB(String accountNumber, String customerName, String pinNumber, int accountBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.pinNumber = pinNumber;
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void withdraw(String accountNumber, int amount, TreeMap<String, CustomerDB> db){
         db.get(accountNumber).setAccountBalance(getAccountBalance()-amount);
    }

    public void deposit(String accountNumber, int amount, TreeMap<String, CustomerDB> db){
        db.get(accountNumber).setAccountBalance(getAccountBalance()+amount);
    }
}
