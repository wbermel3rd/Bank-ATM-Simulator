import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ATM {

	public ATM(User anUser) throws FileNotFoundException, IOException {
		client = anUser;
		f = new File("BankClients.txt");
		if(!f.exists())
			f.createNewFile();
		in = new Scanner(System.in);
		consoleState = 0;
		quit = false;
		accounts = new ArrayList<String>();
	}
	
	public void start() throws IOException {
		
		
		try {
			while(quit == false) {
				while(consoleState == 0) {
					reader = new BufferedReader(new FileReader(f));
					System.out.print("Enter account number or enter N to create a new account: ");
					String str = in.next();
					if(str.equalsIgnoreCase("N")) {
						consoleState = 5;
						break;
					}
					String line = "";
					currentAccNum = Integer.parseInt(str);
					while(true) {
						if((line = reader.readLine()) != null) {
							if(line.substring(0, 5).equals(str)) {
								consoleState = 1;
								break;
							}
						} else{
							System.out.print("Invalid account number. Please try again. \n");
							break;
						}	
						
					}
					
				}

				reader.close();
				while(consoleState == 1) {
					System.out.print("\nPlease enter pin number: ");	
					int newPin = in.nextInt();
					if(checkPin(currentAccNum, newPin)) {
						System.out.print("Pin accepted.");
						consoleState = 2;
					} else 
						System.out.print("Invalid pin number, please try again. \n"); 
				}
				
				while(consoleState == 2) {
					System.out.println();
					if(client.getType(currentAccNum).equals(client.getChecking(currentAccNum))) {
						System.out.print("Balance of checking account: " + client.getType(currentAccNum).getBalance());
						this.currentAccount = client.getChecking(currentAccNum);
						
					} else {
						System.out.println();
						System.out.print("Balance of savings account: " + client.getType(currentAccNum).getBalance());
						this.currentAccount = client.getSavings(currentAccNum);
						
					}
					
					System.out.println();
					System.out.print("Please select an option: A = Withdraw  B = Deposit  C = Exit \n");
					String entrant = in.next();
					if(entrant.equalsIgnoreCase("C")) {
						System.out.print("Returning to the main menu.");
						System.out.print("\n\n\n");
						consoleState = 0;
					} else if(entrant.equalsIgnoreCase("A")) {
						System.out.println();
						System.out.print("How much would you like to withdraw?");
						double tempWithdrawal = in.nextDouble();
						if(this.currentAccount.sufficientFunds(tempWithdrawal))
							client.withdraw(this.currentAccount, tempWithdrawal);
						else
							System.out.print("Insufficient funds");
							
					} else if(entrant.equalsIgnoreCase("B")) {
						System.out.println();
						System.out.print("How much would you like to deposit?");
						double tempDeposit = in.nextDouble();
						client.deposit(this.currentAccount, tempDeposit);
					} else {
						System.out.print("Invalid input. Please try again.");
					}
				}	
				
				while(consoleState == 5) {
					int newAcc;
					System.out.println();
					System.out.print("Generating bank account number... \n");
					
					System.out.print("Enter desired 4 digit pin number: ");
					int newPin = in.nextInt();
					client.setPin(newPin);
					newAcc = (int) ((Math.random() * 89999) + 10000);
					while(accounts.contains("" + newAcc))
						newAcc = (int) ((Math.random() * 89999) + 10000);
					
					System.out.print("Checking account number: " + newAcc + "\n");
					addAccount(newAcc, newPin);
					client.setCheckingAccount(newAcc);	
					
					newAcc = (int) ((Math.random() * 99999) + 10000);
					while(accounts.contains("" + newAcc))
						newAcc = (int) ((Math.random() * 99999) + 10000);
					
					System.out.print("Savings account number: " + newAcc + "\n");
					addAccount(newAcc, newPin);
					client.setSavingsAccount(newAcc);
					
					consoleState = 0;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			reader.close();
			writer.close();
		}
	}
	
	
	
	public void addAccount(int accNum, int pinNum) throws IOException {
		
		try {
			String temp = ("" + accNum + " " + pinNum + "\n");
			String accountList = "";
			String str;
			reader = new BufferedReader(new FileReader(f));
			while((str = reader.readLine()) != null)
				accountList += str + "\n";
			
			accountList += temp;
			reader.close();
			writer = new FileWriter(f);
			writer.write(accountList);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
			writer.close();
		}
	}
	
	public boolean checkPin(int enteredAccNum, int enteredPinNum) {
		if(client.getType(enteredAccNum).getPin() == enteredPinNum)
			return true;
		return false;
	}
	
	private ArrayList<String> accounts;
	private int currentAccNum;
	private BankAccount currentAccount;
	private Scanner in;
	private BufferedReader reader;
	private FileWriter writer;
	private boolean quit;
	private int consoleState;
	private File f;
	private User client;
}
