package viewHelpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import utils.ResultadosBusca;

public class PaginacaoViewHelper {
	public static String[] createLinksPaginacao(Campo campo, ResultadosBusca resultadosBusca) {
        int resultadosPorPagina = PaginacaoViewHelper.getResultadosPorPagina(campo);
        int linksPaginacaoCount = PaginacaoViewHelper.linksPaginacaoCount(resultadosBusca, resultadosPorPagina);

        return PaginacaoViewHelper.getLinksPaginacao(linksPaginacaoCount, resultadosPorPagina);
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