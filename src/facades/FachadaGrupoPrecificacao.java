package facades;


import dao.GrupoPrecificacaoDAO;
import model.EntidadeDominio;
import model.GrupoPrecificacao;
import strategies.ValidarCampos;
import utils.Campo;
import utils.ResultadosBusca;
import java.util.ArrayList;

public class FachadaGrupoPrecificacao implements IFachada< GrupoPrecificacao, Campo[]> {

	public boolean validarCampos(Campo[] campos) {
		ValidarCampos validarCampos = new ValidarCampos();

		boolean camposValidos = true;

		for(int i = 0; i < campos.length; i++) {
			if (validarCampos.processa(campos[i]) == false) {
				System.out.println(campos[i].getValor());
				System.out.println(campos[i].getMensagemErro());
				camposValidos = false;
			}
		}
		return camposValidos;
	}

	public ResultadosBusca select(Campo[] campos) {
		GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();

		dao.select(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public GrupoPrecificacao selectSingle(long id) {
		GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();
		dao.selectSingle(id);
		return dao.selectSingleVal;
	}

	
	public String insert(GrupoPrecificacao grupoprecificacao) {
		try {
			GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();
			dao.insert(grupoprecificacao);

			return "Grupo de Precifica��o inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de valida��o. Tente novamente.";
		}
	}

	public String delete(GrupoPrecificacao grupoprecificacao) {
		GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();

		dao.delete(grupoprecificacao.getId());

		return "Grupo de Precifica��o com sucesso!";
	}

	public String update(GrupoPrecificacao grupoprecificacao) {
		GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();
		dao.update(grupoprecificacao);

		return "Grupo de Precifica��o com sucesso!";
	}

	public String updateStatus(GrupoPrecificacao grupoprecificacao) {
		GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();
		dao.updateStatus(grupoprecificacao);

		return "Status de Grupo de Precifica��o alterado com sucesso!";
	}
}
