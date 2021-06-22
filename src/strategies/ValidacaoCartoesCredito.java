package strategies;

import model.CartaoCredito;

public class ValidacaoCartoesCredito implements IStrategy<Boolean, CartaoCredito[]> {
	public Boolean processa(CartaoCredito[] obj) {
        for(int i = 0; i < obj.length; i++) {
           if (obj[i].getNome().trim().equals("")
            || !obj[i].getNumero().matches("^[0-9]{16}$")
            || !obj[i].getCvv().matches("^[0-9]{3}$")
            || obj[i].getDataExpiracao() == null
            || obj[i].getBandeira() == null
            || obj[i].getBandeira().getId() <= 0) {
            System.out.println("Erro ao validar cartao!");
            System.out.println(obj[i].getNumero());
            return false;
           }
        }

        return true;
	}

}
