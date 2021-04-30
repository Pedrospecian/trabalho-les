package viewHelpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import utils.ResultadosBusca;

public class ConfiguracoesViewHelper {
	public static Campo[] getAlterarConfiguracoesCampos(HttpServletRequest req) {
        Campo[] campos = new Campo[5];

		campos[0] = new Campo(1, req.getParameter("numerosVendaInativacaoAutomatica"), true, "", true, "numerosVendaInativacaoAutomatica");
		campos[1] = new Campo(1, req.getParameter("diasInativacaoAutomatica"), true, "", true, "diasInativacaoAutomatica");
		campos[2] = new Campo(1, req.getParameter("diasPermanenciaCarrinho"), true, "", true, "diasPermanenciaCarrinho");
		campos[3] = new Campo(1, req.getParameter("diasPermanenciaBloqueioItemCarrinho"), true, "", true, "diasPermanenciaBloqueioItemCarrinho");
		campos[4] = new Campo(1, req.getParameter("cepOrigem"), true, "", true, "cepOrigem");

		return campos;
    }

}