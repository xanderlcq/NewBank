import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Data {
	public static ArrayList<BankAccount> load() {
		ArrayList<BankAccount> list = new ArrayList<BankAccount>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("database.txt"));
			while(true){
				String line = reader.readLine();
				if(line == null)
					break;
				String[] temp = line.split(" ");
				String name = temp[0];
				String encrypted = temp[1];
				Double balance = Double.parseDouble(temp[2]);
				Double rate = Double.parseDouble(temp[3]);
				long lastRefreshTime = Long.parseLong(temp[4]);
				
				if(rate == 0.03){
					list.add(new SavingAccount(name, encrypted, balance,lastRefreshTime, false));
				}else{
					list.add(new CheckingAccount(name, encrypted, balance,lastRefreshTime, false));
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void store(ArrayList<BankAccount> list) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("database.txt"));
			for (int i = 0; i < list.size(); i++) {
				String str = list.get(i).getName() + " "
						+ list.get(i).getEncrypted() + " "
						+ list.get(i).getBalance() + " "
						+ list.get(i).getRate() + " "
						+ list.get(i).getLastRefreshTime();
				pw.println(str);

			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
