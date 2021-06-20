package facades;

import dao.SelectDAO;
import model.EntidadeDominio;
import utils.Campo;
import utils.ResultadosBusca;

import java.util.ArrayList;

public class FachadaSelect implements IFachada<EntidadeDominio, Campo[]>{
	public ArrayList getOpcoesSelect(int numero) {
		SelectDAO dao = new SelectDAO();
		dao.getOpcoesSelect(numero);
		return dao.selectVals;
	}

	public String insert(EntidadeDominio entidadeDominio, String usuarioResponsavel) {
		return "";
	}

	public String delete(EntidadeDominio entidadeDominio, String usuarioResponsavel) {
		return "";
	}

	public String update(EntidadeDominio entidadeDominio, String usuarioResponsavel) {
		return "";
	}

	public ResultadosBusca select(Campo[] campos) {
		return null;
	}
}
