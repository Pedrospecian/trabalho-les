package facades;


import dao.GrupoPrecificacaoDAO;
import model.EntidadeDominio;
import model.GrupoPrecificacao;
import strategies.ValidarCampos;
import utils.Campo;
import utils.Log;
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
	
	public String insert(GrupoPrecificacao grupoprecificacao, String usuarioResponsavel) {
		try {
			GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();
			dao.insert(grupoprecificacao);

			Log log = new Log(usuarioResponsavel + " (admin)",
							 "GrupoPrecificacao {id: " + grupoprecificacao.getId() +
							 			  ", nome: " + grupoprecificacao.getNome() + 
							 			  ", porcentagem: " + grupoprecificacao.getPorcentagem() + 
							 			  ", status: " + grupoprecificacao.getStatus() + 
							 "}",
							 "Inserção");
        	log.registrar();

			return "Grupo de Precificação inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(GrupoPrecificacao grupoprecificacao, String usuarioResponsavel) {
		GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();

		dao.delete(grupoprecificacao.getId());

		return "Grupo de Precificação com sucesso!";
	}

	public String update(GrupoPrecificacao grupoprecificacao, String usuarioResponsavel) {
		GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();
		dao.update(grupoprecificacao);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "GrupoPrecificacao {id: " + grupoprecificacao.getId() +
							 			  ", nome: " + grupoprecificacao.getNome() + 
							 			  ", porcentagem: " + grupoprecificacao.getPorcentagem() + 
							 			  ", status: " + grupoprecificacao.getStatus() + 
							 "}",
							 "Alteração");
		log.registrar();

		return "Grupo de Precificação com sucesso!";
	}

	public String updateStatus(GrupoPrecificacao grupoprecificacao, String usuarioResponsavel) {
		GrupoPrecificacaoDAO dao = new GrupoPrecificacaoDAO();
		dao.updateStatus(grupoprecificacao);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "GrupoPrecificacao {id: " + grupoprecificacao.getId() +
							 			  ", status: " + grupoprecificacao.getStatus() + 
							 "}",
							 "Alteração de status");
		log.registrar();

		return "Status de Grupo de Precificação alterado com sucesso!";
	}
}
