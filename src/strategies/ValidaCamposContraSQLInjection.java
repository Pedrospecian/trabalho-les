package strategies;

import utils.Campo;

public class ValidaCamposContraSQLInjection implements IStrategy<Boolean, Campo[]> {

	public Boolean processa(Campo[] campos) {
		if(campos.length < 0) return false;
		for(int i=0;i<campos.length;i++) {
			if(campos[i].getValor() != null && !campos[i].getValor().trim().equals("")) {
				if(
				    campos[i].getValor().toUpperCase().contains("DELETE") ||
				    campos[i].getValor().toUpperCase().contains("TRUNCATE") ||
				    campos[i].getValor().toUpperCase().contains("DROP") ||
				    campos[i].getValor().toUpperCase().contains("USE")
				) {
					return false;
				}
			}
		}
		return true;
	}

}
