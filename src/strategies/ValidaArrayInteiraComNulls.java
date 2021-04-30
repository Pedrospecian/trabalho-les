package strategies;

import utils.Campo;

public class ValidaArrayInteiraComNulls implements IStrategy<Boolean, Campo[]> {

	@Override
	public Boolean processa(Campo[] campos) {
		if(campos.length < 0) return false;
		for(int i=0;i<campos.length;i++) {
			if(campos[i].getTipo() == 999) continue;
			if(campos[i].getValor() != null) return true;
		}
		return false;
	}

}
