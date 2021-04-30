package strategies;

import java.util.InputMismatchException;

public class ValidarCPF implements IStrategy<Boolean, String> {
	public Boolean processa(String string) {
        if (string.equals("00000000000") ||
            string.equals("11111111111") ||
            string.equals("22222222222") ||
            string.equals("33333333333") ||
            string.equals("44444444444") ||
            string.equals("55555555555") ||
            string.equals("66666666666") ||
            string.equals("77777777777") ||
            string.equals("88888888888") ||
            string.equals("99999999999") ||
            (string.length() != 11)) {
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {

            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(string.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char)(r + 48);
            } 


            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(string.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                 dig11 = '0';
            } else {
                dig11 = (char)(r + 48);
            }

            if ((dig10 == string.charAt(9)) && (dig11 == string.charAt(10))) {
                return true;
            } else {
               return false; 
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
            return false;
        }
	}

}
