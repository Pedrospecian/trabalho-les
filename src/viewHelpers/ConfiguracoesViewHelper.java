package viewHelpers;

import javax.servlet.http.HttpServletRequest;

import model.EntidadeDominio;
import utils.Campo;

public class ConfiguracoesViewHelper implements IViewHelper<EntidadeDominio>{
	public Campo[] alterarCampos(HttpServletRequest req) {
        Campo[] campos = new Campo[5];

		campos[0] = new Campo(1, req.getParameter("numerosVendaInativacaoAutomatica"), true, "", true, "numerosVendaInativacaoAutomatica");
		campos[1] = new Campo(1, req.getParameter("diasInativacaoAutomatica"), true, "", true, "diasInativacaoAutomatica");
		campos[2] = new Campo(1, req.getParameter("diasPermanenciaCarrinho"), true, "", true, "diasPermanenciaCarrinho");
		campos[3] = new Campo(1, req.getParameter("diasPermanenciaBloqueioItemCarrinho"), true, "", true, "diasPermanenciaBloqueioItemCarrinho");
		campos[4] = new Campo(1, req.getParameter("cepOrigem"), true, "", true, "cepOrigem");

		return campos;
    }

    public Campo[] alterarStatusCampos(HttpServletRequest req) {
    	return null;
    }

    public Campo[] cadastroCampos(HttpServletRequest req) {
    	return null;
    }

    public Campo[] listagemCampos(HttpServletRequest req) {
    	return null;
    }
    
    public EntidadeDominio instancia(Campo[] campos) {
    	return null;
    }

}