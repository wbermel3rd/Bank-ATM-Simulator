public class SavingsAccount extends BankAccount{
	public SavingsAccount() {
		super();
	}
	public SavingsAccount(double bal, int accNum, int pinNum) {
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
