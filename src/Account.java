public interface Account {
	public final String SALT = "xander";
	
	public String getName();
	
	public String getBalance();

	public String makeDeposit(double amount);

	public String withdrawMoney(double amount);

	public String changePin(int oldPin, int newPin);

	public String changeName(String newName);

	public String changeType(String type);
	
	public String getRate();
	
	public String getLastRefreshTime();
	
	public String getEncrypted();
	
	public boolean checkPin(String pin);

}
