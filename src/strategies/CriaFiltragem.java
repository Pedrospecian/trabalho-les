package strategies;

import java.util.ArrayList;
import utils.Campo;

public class CriaFiltragem implements IStrategy<String, Campo[]> {
	public String processa(Campo[] campos) {
		if (campos == null) return "";
		
		try {
			String filtro = "";
			ValidaCamposContraSQLInjection v0 = new ValidaCamposContraSQLInjection();
			ValidaArrayInteiraComNulls v1 = new ValidaArrayInteiraComNulls();
			
			if(v1.processa(campos)) {
		  		if(v0.processa(campos)) {
		  			filtro = "where ";
		
		  			ArrayList<String> arrayFiltros = new ArrayList<String>();

		  			System.out.println(campos.length);

		  			for (int i = 0; i < campos.length; i++) {
		  				System.out.println(campos[i].getValor());
		  				if (campos[i].getValor() != null && !campos[i].getValor().trim().equals("") && campos[i].getTipo() != 999) {
		  					String nomeTabela = "";

		  					/*if (!campos[i].getNome().contains("clientes.")) {
		  						nomeTabela = "";
		  					}*/

				  			switch(campos[i].getTipo()) {
				  				//int e long
					            case 1:  
					        		arrayFiltros.add(nomeTabela + campos[i].getNome() + " = " + campos[i].getValor()); 
					                break;
					            //double
					            case 2:   
					        		arrayFiltros.add(nomeTabela + campos[i].getNome() + " = " + campos[i].getValor()); 
					                break;
					            case 3:
					            	arrayFiltros.add(nomeTabela + campos[i].getNome() + " = '" + campos[i].getValor() + "'"); 
					            	break;
					            default:
					            	arrayFiltros.add(nomeTabela + campos[i].getNome() + " LIKE '%" + campos[i].getValor() + "%'"); 
					            	break;
				  			}
				  		}

		  			}
		  			
		  			String[] filtros = new String[arrayFiltros.size()];
		  			
		  			for(int i = 0; i < filtros.length; i++) {
		  				filtros[i] = arrayFiltros.get(i);
		  			}
		
		  			filtro += String.join(" and ", filtros);
		  		}
	  		}
			
			if(filtro.equals("where ")) filtro = "";
	
			return filtro;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
