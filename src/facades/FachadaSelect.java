package facades;

import dao.SelectDAO;

import java.util.ArrayList;

public class FachadaSelect {
	public ArrayList getOpcoesSelect(int numero) {
		SelectDAO dao = new SelectDAO();
		dao.getOpcoesSelect(numero);
		return dao.selectVals;
	}
}
