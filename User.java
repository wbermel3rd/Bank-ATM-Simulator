public class User {
	public User() {
		pin = 0;
		checkingAccount = new CheckingAccount();
		savingsAccount = new SavingsAccount();
	}
	
	public void deposit(BankAccount account, double amount) {
		account.deposit(amount);
	}
	public void withdraw(BankAccount account, double amount) {
		account.withdraw(amount);
	}
	
	public CheckingAccount getChecking(int accNum) {
		if(accNum == this.checkingAccount.getAccountNum())
			return this.checkingAccount;
		else 
			return null;
	}
	public SavingsAccount getSavings(int accNum) {
		if(accNum == this.savingsAccount.getAccountNum())
			return this.savingsAccount;
		else 
			return null;
	}
	public BankAccount getType(int accNum) {
		if(accNum == this.savingsAccount.getAccountNum())
			return this.savingsAccount;
		else 
			return this.checkingAccount;
	}
	public double getBalance(BankAccount account) {
		return account.getBalance();
	}
	
	public void setCheckingAccount(int accountNum) {
		this.checkingAccount = new CheckingAccount(0, accountNum, pin);
	}
	public void setSavingsAccount(int accountNum) {
		this.savingsAccount = new SavingsAccount(0, accountNum, pin);
	}
	public void setPin(int newPin) {
		this.pin = newPin;
	}
	
	
	private int pin;
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
}
