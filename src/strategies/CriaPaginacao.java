package strategies;

import utils.Campo;

public class CriaPaginacao implements IStrategy<String, Campo[]> {
	public String processa(Campo[] campos) {
		try {
			int limit = 10;
			int offset = 0;

			for (int i = 0; i < campos.length; i++) {
				if (campos[i].getNome().equals("paginaAtual") && campos[i].getValor() != null) {
					offset = Math.max(0, Integer.parseInt(campos[i].getValor()) - 1);
				}

				if (campos[i].getNome().equals("resultadosPorPagina") && campos[i].getValor() != null) {
					limit = Math.max(1, Integer.parseInt(campos[i].getValor()));
				}
			}
			return " limit " + limit + " offset " + (offset * limit);
		} catch(Exception e) {
			e.printStackTrace();
			return " limit 10 offset 0";
		}
	}
}
