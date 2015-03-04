public class SavingAccount extends BankAccount {
	public SavingAccount(String name, String pin, double deposit,
			long lastRefreshTime, boolean isNew) {
		if (isNew) {
			this.name = name;
			this.encrypted = Encryption.encrypt(pin);
			this.balance = deposit;
			this.rate = 0.03;
			this.lastRefreshTime = System.currentTimeMillis();

		} else {
			this.name = name;
			this.encrypted = pin;
			this.balance = deposit;
			this.rate = 0.03;
			this.lastRefreshTime = lastRefreshTime;

		}
	}
}
