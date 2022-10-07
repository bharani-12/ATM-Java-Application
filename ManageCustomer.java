import java.util.TreeMap;

public class ManageCustomer {

    public boolean validAccountNumber(String accountNumber, TreeMap<String, CustomerDB> db){
        if(accountNumber.equals(db.get(accountNumber).getAccountNumber())){
            return true;
        }
        return false;
    }
    public boolean validPinNumber(String accountNumber, String pinNumber, TreeMap<String, CustomerDB> db){
        if(pinNumber.equals(db.get(accountNumber).getPinNumber())){
            return true;
        }
        return false;
    }


    public void transferAmount(String fromAccountNumber, String toAccountNumber, int amount, TreeMap<String, CustomerDB> db){
        db.get(fromAccountNumber).withdraw(fromAccountNumber, amount, db);
        db.get(toAccountNumber).deposit(toAccountNumber, amount, db);
    }
}
