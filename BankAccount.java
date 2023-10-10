public abstract class BankAccount {
	public BankAccount() {
			balance = 0;
			accountNum = 0;
			pin = 0;
	}

	public BankAccount(double bal, int accNum, int pinNum) {
		balance = bal;
		accountNum = accNum;
		pin = pinNum;
	}
	
	public void deposit(double amount) {
		try {
			System.out.print("Depositing: $" + amount);
			double newBalance = balance + amount;
			System.out.println(". new balance is: " + newBalance);
			
			balance = newBalance;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void withdraw(double amount) {
		try {
			System.out.print("Withdrawing: $" + amount);
			double newBalance = balance - amount;
			System.out.println(". New balance is: " + newBalance);
			balance = newBalance;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean sufficientFunds(double amount) {
		if(this.balance - amount >= 0) 
			return true;
		return false;
	}
	
	public double getBalance() {
		return this.balance;
	}
	public int getAccountNum() {
		return this.accountNum;
	}
	public int getPin() {
		return this.pin;
	}
	
	private int pin;
	private int accountNum;
	private double balance;
}
