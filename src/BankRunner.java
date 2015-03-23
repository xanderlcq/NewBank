import java.util.ArrayList;

import acm.io.IODialog;
import acm.program.*;

@SuppressWarnings("serial")
public class BankRunner extends ConsoleProgram {

	ArrayList<BankAccount> list = Data.load();
	int operatingIndex = 0;

	@Override
	public void run() {
		setSize(600, 600);
		level1UI();

	}

	private void level1UI() {
		Data.store(list);
		println();
		println("1----Register a new account.");
		println("2----Log in to your account.");
		println("3----Exit the program");
		int input = readInt("What do you want to do? ");
		println();
		while (input < 1 || input > 3) {
			input = readInt("Invalid input, what do you want to do? ");
		}
		if (input == 1) {
			String name = readLine("Account Name: ");
			String pin = "" + readInt("Pin: ");
			double initialDeposit = readDouble("Initial Deposit: ");
			int type = readInt("1----Saving Account  2----Checking Account : ");
			while (true) {
				if (type == 1) {
					if (list.size() == 0) {
						list.add(new SavingAccount(name, pin, initialDeposit,
								0, true));
						println("Success!");

					} else {
						for (int k = 0; k < list.size(); k++) {
							if(name.compareTo(list.get(k).getName()) == 0){
								println("There is aready an account under this name!");
								break;
							}
							if (name.compareTo(list.get(k).getName()) < 0) {
								list.add(k, new SavingAccount(name, pin,
										initialDeposit, 0, true));
								println("Success!");
								break;
							}

						}
					}
					break;
				} else if (type == 2) {
					if (list.size() == 0) {
						list.add(new CheckingAccount(name, pin, initialDeposit,
								0, true));
						println("Success!");

					} else {
						for (int k = 0; k < list.size(); k++) {
							if(name.compareTo(list.get(k).getName()) == 0){
								println("There is aready an account under this name!");
								break;
							}
							if (name.compareTo(list.get(k).getName()) <= 0) {
								list.add(k, new CheckingAccount(name, pin,
										initialDeposit, 0, true));
								println("Success!");
								break;
							}

						}
					}
					break;
				} else {
					println("Please enter a correct input!");
					type = readInt("1----Saving Account  2----Checking Account : ");
				}
			}
			level1UI();
		} else if (input == 2) {
			String name = readLine("Name: ");
			String pin = "" + readInt("Pin: ");
			for (int i = 0; i < list.size(); i++) {
				if (name.equalsIgnoreCase(list.get(i).getName())) {
					while (!list.get(i).checkPin(pin)) {
						println("Pin incorrect!");
						pin = "" + readInt("Pin: ");
					}
					println("Success!");
					operatingIndex = i;
					level2UI();
				}
			}
			println("There isn't an account under this name! Please create one!");
			level1UI();
		} else {
			Data.store(list);
			IODialog confirm = new IODialog();
			if (confirm.readBoolean("Are you sure you want to exit?")) {
				IODialog seeYou = new IODialog();
				seeYou.println("Thank you for using this bank!-----Xander Li");
				System.exit(0);
			}
		}

	}

	private void level2UI() {
		Data.store(list);
		println();
		println("1----Check your balance");
		println("2----Make a deposit");
		println("3----Withdraw some money");
		println("4----Transfer some money");
		println("5----Manage your account");
		println("6----Log out");
		int input = readInt("What do you want to do? ");
		println();
		while (input < 1 || input > 6) {
			input = readInt("Invalid input, what do you want to do? ");
		}
		if (input == 1) {
			println(list.get(operatingIndex).getBalance());
			level2UI();
		} else if (input == 2) {
			double amount = readDouble("How much do you want to add to your account? ");
			println(list.get(operatingIndex).makeDeposit(amount));
			level2UI();
		} else if (input == 3) {
			double amount = readDouble("How much do you want to withdraw? ");
			println(list.get(operatingIndex).withdrawMoney(amount));
			level2UI();
		} else if (input == 4) {
			double amount = readDouble("How much do you want to transfer? ");
			String dest = readLine("What's reciever's account name? ");
			//
			boolean temp = true;
			while (temp) {
				for (int i = 0; i < list.size(); i++) {
					if (dest.equalsIgnoreCase(list.get(i).getName())) {
						list.get(i).makeDeposit(amount);
						println(list.get(operatingIndex).withdrawMoney(amount));
						temp = false;
					}
				}
				if (temp)
					dest = readLine("Reciever account doesn't exist!  Reciever:");
			}
			level2UI();
		} else if (input == 5) {
			level3UI();
		} else {
			operatingIndex = 0;
			level1UI();
		}

	}

	private void level3UI() {
		Data.store(list);
		println();
		println("1----Change your pin");
		println("2----Change your account owner's name");
		println("3----Change your account type");
		println("4----Close your account");
		println("5----Back");
		int input = readInt("What do you want to do? ");
		println();
		while (input < 1 || input > 5) {
			input = readInt("Invalid input, what do you want to do? ");
		}
		if (input == 1) {
			String oldPin = "" + readInt("What's your current pin? ");
			while (!list.get(operatingIndex).checkPin(oldPin)) {
				oldPin = "" + readInt("Current Pin Incorrect!  Pin: ");
			}
			String newPin = "" + readInt("What's your new pin? ");
			println(list.get(operatingIndex).changePin(newPin));
			println("Please log in again!");
			operatingIndex = 0;
			level1UI();
		} else if (input == 2) {
			String newOwner = readLine("What's your new owner name? ");
			println(list.get(operatingIndex).changeName(newOwner));
			println("Please log in again!");
			operatingIndex = 0;
			level1UI();
		} else if (input == 3) {
			println();
			println("1---Change to Saving Account");
			println("2---Change to Checking Account");
			println("3---Back");
			int input2 = readInt("What do you want to do: ");
			println();
			while (input2 < 1 || input2 > 3) {
				input2 = readInt("Invalid input: ");
			}
			if (input2 == 1) {
				println(list.get(operatingIndex).changeType("S"));
				level3UI();
			} else if (input2 == 2) {
				println(list.get(operatingIndex).changeType("C"));
				level3UI();
			} else {
				level3UI();
			}
		} else if (input == 4) {
			String pin = "" + readInt("Pin: ");
			IODialog confirm = new IODialog();
			if (confirm
					.readBoolean("Are you sure you want to close this account?")) {
				while (list.get(operatingIndex).checkPin(pin)) {
					pin = "" + readInt("Pin Incorrect!  Pin: ");
				}
				list.remove(operatingIndex);
				println("Account Deleted!");
				level1UI();
			}
		} else if (input == 5) {
			level2UI();
		}
	}

}
