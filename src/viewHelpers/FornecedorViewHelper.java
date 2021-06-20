package viewHelpers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.Campo;
import model.Pais;
import model.Estado;
import model.Fornecedor;
import model.FuncaoEndereco;
import model.Cidade;
import model.Bairro;
import model.Endereco;
import model.EntidadeDominio;
import model.Documento;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoLogradouro;
import model.TipoResidencia;

public class FornecedorViewHelper implements IViewHelper<EntidadeDominio>{
	public Campo[] listagemCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		String resultadosPorPagina = "10";

		if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
			resultadosPorPagina = req.getParameter("resultadosPorPagina");
		}

		campos[0] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[1] = new Campo(0, req.getParameter("email"), true, "", true, "email");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "status");
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

	public Campo[] alterarCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4]; 

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("nome"), true, "", true, "nome");
		campos[2] = new Campo(7, req.getParameter("email"), true, "", true, "email");
		campos[3] = new Campo(1, req.getParameter("status"), true, "", true, "status");

		return campos;
	}
	
	public EntidadeDominio instancia(Campo[] campos) {
    	return null;
    }

    public Endereco instanciaEndereco(Campo[] campos) {
    	Pais pais = new Pais(Long.parseLong(campos[14].getValor()), null, null);
		Estado estado = new Estado(0, null, campos[13].getValor(), pais);
		Cidade cidade = new Cidade(0, null, campos[12].getValor(), estado);
		Bairro bairro = new Bairro(0, null, campos[11].getValor(), cidade);
		TipoEndereco tipoEndereco = new TipoEndereco(Long.parseLong(campos[6].getValor()), null, null, null);

		TipoResidencia tipoResidencia = new TipoResidencia(Long.parseLong(campos[16].getValor()), null, null, null);
		FuncaoEndereco funcaoEndereco = new FuncaoEndereco(Long.parseLong(campos[17].getValor()), null, null, null);
		TipoLogradouro tipoLogradouro = new TipoLogradouro(Long.parseLong(campos[18].getValor()), null, null, null);

		Endereco endereco = new Endereco(
			0,
			null,
			campos[8].getValor(),
			campos[9].getValor(),
			campos[7].getValor(),
			campos[10].getValor(),
			bairro,
			tipoEndereco,
			campos[15].getValor(),
			tipoResidencia,
			funcaoEndereco,
			tipoLogradouro,
			campos[19].getValor());

		return endereco;
    }

    public Documento instanciaDocumento(Campo[] campos) {
    	try {
	    	Documento documento = new Documento(
				        					(long)1,
				        					null,
				        					campos[4].getValor(),
				        					new SimpleDateFormat("yyyy-MM-dd").parse(campos[5].getValor()),
				        					new TipoDocumento(Long.parseLong(campos[3].getValor()), null, null, null)
			);
			return documento;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public Fornecedor instanciaFornecedor(Campo[] campos, Documento documento, Endereco endereco) {
    	String nome = campos[0].getValor();
		String email = campos[1].getValor();
        int status = Integer.parseInt(campos[2].getValor());
        Fornecedor fornecedor = new Fornecedor((long)1, new Date(), nome, email, status, documento, endereco);
        return fornecedor;
    }
}