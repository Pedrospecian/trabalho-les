package viewHelpers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.Campo;
import model.Pais;
import model.Estado;
import model.FuncaoEndereco;
import model.Cidade;
import model.Bairro;
import model.Endereco;
import model.Documento;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoLogradouro;
import model.TipoResidencia;
import utils.ResultadosBusca;

public class FornecedorViewHelper {
	public static Campo[] getListagemFornecedorCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[5];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(0, req.getParameter("email"), true, "", true, "email");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");
		campos[3] = new Campo(999, req.getParameter("paginaAtual"), true, "", true, "paginaAtual");
		campos[4] = new Campo(999, req.getParameter("resultadosPorPagina"), true, "", true, "resultadosPorPagina");

		return campos;
	}

	public static Campo[] getAlterarFornecedorStatusActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}

	public static Campo[] getCadastroFornecedorActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[20];

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(7, req.getParameter("email"), true, "", true, "email");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		campos[3] = new Campo(1, req.getParameter("tipoDocumento"), true, "", true, "tipoDocumento");
		campos[4] = new Campo(0, req.getParameter("documento"), true, "", true, "documento");
		campos[5] = new Campo(3, req.getParameter("dataValidade"), true, "", true, "dataValidade");

		campos[6] = new Campo(1, req.getParameter("tipoEndereco"), true, "", false, "tipoEndereco");
		campos[7] = new Campo(0, req.getParameter("cep"), true, "", false, "cep");
		campos[8] = new Campo(0, req.getParameter("logradouro"), true, "", false, "logradouro");
		campos[9] = new Campo(0, req.getParameter("numero"), true, "", false, "numero");
		campos[10] = new Campo(0, req.getParameter("complemento"), true, "", false, "complemento");
		campos[11] = new Campo(0, req.getParameter("bairro"), true, "", false, "bairro");
		campos[12] = new Campo(0, req.getParameter("cidade"), true, "", false, "cidade");
		campos[13] = new Campo(0, req.getParameter("estado"), true, "", false, "estado");
		campos[14] = new Campo(1, req.getParameter("pais"), true, "", false, "pais");	
		campos[15] = new Campo(0, req.getParameter("nomeEndereco"), true, "", false, "nomeEndereco");	
		campos[16] = new Campo(1, req.getParameter("tipoResidencia"), true, "", false, "tipoResidencia");
		campos[17] = new Campo(1, req.getParameter("funcaoEndereco"), true, "", false, "funcaoEndereco");
		campos[18] = new Campo(1, req.getParameter("tipoLogradouro"), true, "", false, "tipoLogradouro");
		campos[19] = new Campo(0, req.getParameter("observacoes"), true, "", false, "observacoes");

		return campos;
	}

	public static Campo[] getAlterarFornecedorActionCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4]; 

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(7, req.getParameter("email"), true, "", true, "email");
		campos[3] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}
}