public abstract class BankAccount implements Account {
	protected double balance;
	protected double rate;
	protected long lastRefreshTime;
	protected String name;
	protected String encrypted;

	@Override
	public String changeName(String newName) {
		name = newName;
		return "Done!";
	}

	@Override
	public String changePin(String newPin) {

		encrypted = Encryption.encrypt(newPin);
		return "Done!";
	}

	@Override
	public String changeType(String type) {
		if (type.equalsIgnoreCase("S")) {
			rate = 0.03;
			return "Changed your account to a Saving Account.";
		} else {
			rate = 0.01;
			return "Changed your account to a Checking Account.";
		}
	}

	@Override
	public boolean checkPin(String pin) {
		return (Encryption.encrypt(pin)).equals(encrypted);
	}

	@Override
	public String getBalance() {
		interestCalculator();
		return "" + balance;
	}

	@Override
	public String getEncrypted() {
		return encrypted;
	}

	@Override
	public String getLastRefreshTime() {
		return "" + lastRefreshTime;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getRate() {
		return "" + rate;
	}

	private void interestCalculator() {
		long duration = System.currentTimeMillis() - lastRefreshTime;
		double durationInHour = duration / 1000 / 60 / 60;
		lastRefreshTime = System.currentTimeMillis();
		balance = (balance * Math.pow((1 + rate), durationInHour));
	}

	@Override
	public String makeDeposit(double amount) {
		interestCalculator();
		double old = balance;
		balance += amount;
		return "Done! Your balance was " + old + " and your new balance is "
				+ balance + ".";
	}

	@Override
	public String withdrawMoney(double amount) {
		interestCalculator();
		double old = balance;
		balance -= amount;
		return "Done! Your balance was " + old + " and your new balance is "
				+ balance + ".";
	}

}
