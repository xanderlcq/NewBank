public class CheckingAccount extends BankAccount {
	public CheckingAccount(String name, String pin, double deposit,
			long lastRefreshTime, boolean isNew) {
		if (isNew) {
			this.name = name;
			this.encrypted = Encryption.encrypt(pin);
			this.balance = deposit;
			this.rate = 0.01;
			this.lastRefreshTime = System.currentTimeMillis();

		} else {
			this.name = name;
			this.encrypted = pin;
			this.balance = deposit;
			this.rate = 0.01;
			this.lastRefreshTime = lastRefreshTime;

		}
	}

}
