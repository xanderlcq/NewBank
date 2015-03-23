public interface Account {
	public final String SALT = "xander";
	
	public String changeName(String newName);
	
	public String changePin(String newPin);

	public String changeType(String type);

	public boolean checkPin(String pin);

	public String getBalance();

	public String getEncrypted();

	public String getLastRefreshTime();
	
	public String getName();
	
	public String getRate();
	
	public String makeDeposit(double amount);
	
	public String withdrawMoney(double amount);

}
