package viewHelpers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import model.Livro;
import utils.ResultadosBusca;


public class GrupoPrecificacaoViewHelper {
	public static void blockIfUnauthorized(HttpServletRequest req, HttpServletResponse resp) {
		//redireciona o usuario caso ele nao esteja logado como admin
	}
	public static Campo[] getListagemGrupoPrecificacao(HttpServletRequest req) {
		Campo[] campos = new Campo[3];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", false, "nome");
		campos[1] = new Campo(2, req.getParameter("porcentagem"), true, "", false, "porcentagem");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", false, "status");		

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

	public static String[] createLinksPaginacao(Campo campo, ResultadosBusca resultadosBusca) {
        int resultadosPorPagina = GrupoPrecificacaoViewHelper.getResultadosPorPagina(campo);
        int linksPaginacaoCount = GrupoPrecificacaoViewHelper.linksPaginacaoCount(resultadosBusca, resultadosPorPagina);

        return GrupoPrecificacaoViewHelper.getLinksPaginacao(linksPaginacaoCount, resultadosPorPagina);
    }

	public static int getResultadosPorPagina(Campo campo) {
		if (campo == null || campo.getValor() == null || campo.getValor().equals("") || campo.getValor().matches("^[0-9]+$") == false) {
            return 10;
        } else {
        	return Integer.parseInt(campo.getValor());
        }
	}

	public static int linksPaginacaoCount(ResultadosBusca resultadosBusca, int resultadosPorPagina) {
		return (int)Math.ceil((double)resultadosBusca.getContagemTotal() / (double)resultadosPorPagina);
	}

	public static String[] getLinksPaginacao(int linksPaginacaoCount, int resultadosPorPagina) {
		String[] linksPaginacao = new String[linksPaginacaoCount];

        for (int i = 0; i < linksPaginacao.length; i++) {
        	linksPaginacao[i] = "paginaAtual=" + (i + 1) + "&resultadosPorPagina=" + resultadosPorPagina;
        }

        return linksPaginacao;
	}

	
}