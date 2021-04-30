package strategies;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class CriptografarSenha implements IStrategy<String, String> {
	public String processa(String senha) {
		try {
			MessageDigest message = MessageDigest.getInstance("MD5");		
			message.update(senha.getBytes(), 0, senha.length());
		
			return new BigInteger(1, message.digest()).toString(16);
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
