package viewHelpers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.GrupoPrecificacao;
import utils.Campo;

public class GrupoPrecificacaoViewHelper implements IViewHelper<GrupoPrecificacao>{
	public Campo[] listagemCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		String resultadosPorPagina = "10";

		if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
			resultadosPorPagina = req.getParameter("resultadosPorPagina");
		}

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", false, "nome");
		campos[1] = new Campo(2, req.getParameter("porcentagem"), true, "", false, "porcentagem");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", false, "status");
		campos[3] = new Campo(999, resultadosPorPagina, true, "", true, "resultadosPorPagina");

		return campos;
	}

	public Campo[] alterarStatusCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public Campo[] cadastroCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[3];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(2, req.getParameter("porcentagem"), true, "", true, "porcentagem");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		
		return campos;
	}

	public Campo[] alterarCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(2, req.getParameter("porcentagem"), true, "", true, "porcentagem");
		campos[3] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public GrupoPrecificacao instancia(Campo[] campos) {
		String nome = campos[0].getValor();
        double valor = Double.parseDouble(campos[1].getValor());
        int status = Integer.parseInt(campos[2].getValor());

    	GrupoPrecificacao gp = new GrupoPrecificacao((long)1, new Date(), nome, valor, status);
    	return gp;
	}

	
}