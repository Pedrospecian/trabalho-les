package strategies;

import model.Telefone;

public class ValidacaoTelefones implements IStrategy<Boolean, Telefone[]> {
	public Boolean processa(Telefone[] obj) {
        for(int i = 0; i < obj.length; i++) {
           if (obj[i].getDdd().trim().equals("")
            || obj[i].getNumero().trim().equals("")
            || !obj[i].getDdd().matches("^[0-9]+$")
            || !obj[i].getNumero().matches("^[0-9]{8,9}$")
            || obj[i].getTipoTelefone() == null
            || obj[i].getTipoTelefone().getId() <= 0) {
            System.out.println(obj[i].getNumero());
            return false;
           }

           if (obj[i].getTipoTelefone().getId() == 2 && obj[i].getNumero().length() == 9 && obj[i].getNumero().charAt(0) != '9' ) {
            System.out.println(obj[i].getNumero());
            System.out.println((obj[i].getTipoTelefone().getId() == 2)  + "&&" + (obj[i].getNumero().length() == 9) + "&&" + (obj[i].getNumero().charAt(0) != '9') );
            System.out.println(obj[i].getNumero().charAt(0));
            return false;
           }

           if (obj[i].getTipoTelefone().getId() == 1 && obj[i].getNumero().length() != 8) {
            System.out.println(obj[i].getNumero());
            return false;
           }
        }

        return true;
	}

}
