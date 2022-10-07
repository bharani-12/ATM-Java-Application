public class AtmDB{
    private int balaceAmount;
    private  int deposingAmount;
    private int withdrawAmount;

    public AtmDB() {
    }

    public AtmDB(int balaceAmount, int deposingAmount, int withdrawAmount) {
        this.balaceAmount = balaceAmount;
        this.deposingAmount = deposingAmount;
        this.withdrawAmount = withdrawAmount;
    }

    public int getBalaceAmount() {
        return balaceAmount;
    }

    public void setBalaceAmount(int balaceAmount) {
        this.balaceAmount = balaceAmount;
    }

    public int getDeposingAmount() {
        return deposingAmount;
    }

    public void setDeposingAmount(int deposingAmount) {
        this.deposingAmount = deposingAmount;
    }

    public int getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(int withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}
