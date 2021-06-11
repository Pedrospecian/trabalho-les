package viewHelpers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.Campo;
import model.Pais;
import model.Estado;
import model.Cidade;
import model.Bairro;
import model.Endereco;
import model.Documento;
import model.TipoDocumento;
import model.TipoEndereco;
import utils.ResultadosBusca;

public class CupomDescontoViewHelper {
	public static Campo[] getListagemCuponsCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[6];

		String resultadosPorPagina = "10";

		if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
			resultadosPorPagina = req.getParameter("resultadosPorPagina");
		}

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", false, "nome");
		campos[1] = new Campo(1, req.getParameter("valor"), true, "", false, "valor");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", false, "status");
		campos[3] = new Campo(3, req.getParameter("dataInicio"), true, "", false, "dataInicio");
		campos[4] = new Campo(3, req.getParameter("dataFim"), true, "", false, "dataFim");
		campos[5] = new Campo(999, resultadosPorPagina, true, "", true, "resultadosPorPagina");

		return campos;
	}

	public static Campo[] getListagemCuponsTrocaCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[5];

		String resultadosPorPagina = "10";

		if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
			resultadosPorPagina = req.getParameter("resultadosPorPagina");
		}

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", false, "nome");
		campos[1] = new Campo(1, req.getParameter("valor"), true, "", false, "valor");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", false, "status");
		campos[3] = new Campo(999, resultadosPorPagina, true, "", true, "resultadosPorPagina");
		campos[4] = new Campo(1, req.getParameter("id"), true, "", false, "idUsuario");

		return campos;
	}

	public static Campo[] getAlterarCupomStatusActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public static Campo[] getCadastroCupomActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[5];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(1, req.getParameter("valor"), true, "", true, "valor");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[3] = new Campo(3, req.getParameter("dataInicio"), true, "", true, "dataInicio");
		campos[4] = new Campo(3, req.getParameter("dataFim"), true, "", true, "dataFim");
		
		return campos;
	}

	public static Campo[] getAlterarCupomActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[6]; 

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(2, req.getParameter("valor"), true, "", true, "valor");
		campos[3] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[4] = new Campo(3, req.getParameter("dataInicio"), true, "", true, "dataInicio");
		campos[5] = new Campo(3, req.getParameter("dataFim"), true, "", true, "dataFim");

		return campos;
	}

}