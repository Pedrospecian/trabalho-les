package facades;

import dao.ConfiguracoesDAO;
import model.Configuracao;

import java.util.ArrayList;

import strategies.ValidarCampos;
import strategies.CriptografarSenha;
import utils.Campo;
import utils.ResultadosBusca;

public class FachadaConfiguracoes implements IFachada<Configuracao, Campo[]> {
	public boolean validarCampos(Campo[] campos) {
		ValidarCampos validarCampos = new ValidarCampos();

		boolean camposValidos = true;

		for(int i = 0; i < campos.length; i++) {
			if (validarCampos.processa(campos[i]) == false) {
				camposValidos = false;
			}
		}
		return camposValidos;
	}

	public ResultadosBusca select(Campo[] campos) {
		ConfiguracoesDAO dao = new ConfiguracoesDAO();

		dao.select(campos);
		ArrayList<Configuracao> arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public String insert(Configuracao configuracao) {
		return "";
	}

	public String delete(Configuracao configuracao) {
		return "";
	}

	public String update(Configuracao configuracao) {
		return "";
	}

	public String updateConfiguracoes(Configuracao[] configuracoes) {
		ConfiguracoesDAO dao = new ConfiguracoesDAO();
		dao.updateConfiguracoes(configuracoes);

		return "Configuracoes alteradas com sucesso!";
	}

	public void resetarConfiguracoes() {
		ConfiguracoesDAO dao = new ConfiguracoesDAO();
		dao.resetarConfiguracoes();
	}

}
