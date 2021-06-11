package viewHelpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

public class GrupoPrecificacaoViewHelper {
	public static Campo[] getListagemGrupoPrecificacao(HttpServletRequest req) {
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

	public static Campo[] getAlterarGrupoPrecificacaoStatusActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public static Campo[] getCadastroGrupoPrecificacao(HttpServletRequest req) {
		Campo[] campos = new Campo[3];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(2, req.getParameter("porcentagem"), true, "", true, "porcentagem");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		
		return campos;
	}

	public static Campo[] getAlterarGrupoPrecificacaoActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(2, req.getParameter("porcentagem"), true, "", true, "porcentagem");
		campos[3] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public static int getResultadosPorPagina(Campo campo) {
		if (campo == null || campo.getValor() == null || campo.getValor().equals("") || campo.getValor().matches("^[0-9]+$") == false) {
            return 10;
        } else {
        	return Integer.parseInt(campo.getValor());
        }
	}

	public static String[] getLinksPaginacao(int linksPaginacaoCount, int resultadosPorPagina) {
		String[] linksPaginacao = new String[linksPaginacaoCount];

        for (int i = 0; i < linksPaginacao.length; i++) {
        	linksPaginacao[i] = "paginaAtual=" + (i + 1) + "&resultadosPorPagina=" + resultadosPorPagina;
        }

        return linksPaginacao;
	}

	
}