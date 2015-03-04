import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	public static String encrypt(String input) {
		String original = input;
		MessageDigest md;
		StringBuffer sb = null;
		try {
			md = MessageDigest.getInstance("SHA-1");

			md.update(original.getBytes());
			byte[] digest = md.digest();
			sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b));
			}
			
		} catch (NoSuchAlgorithmException e) {
			System.out.print("Encryption erroe");
			e.printStackTrace();
		}
		return (sb.toString());
	}
}
