public class CheckingAccount extends BankAccount{
    public CheckingAccount() {
            super();
        }
        public CheckingAccount(double bal, int accNum, int pinNum) {
            super(bal, accNum, pinNum);
        }
        
        public void deposit(double amount) {
            super.deposit(amount);
        }
        public void withdraw(double amount) {
            super.withdraw(amount);
        }
        
        public double getBalance() {
            return super.getBalance();
        }
        public int getAccountNum() {
            return super.getAccountNum();
        }
    }
    