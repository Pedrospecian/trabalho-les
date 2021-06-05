package facades;

import dao.CupomDescontoDAO;
import model.CupomDesconto;

import java.util.ArrayList;

import strategies.ValidarCampos;
import utils.Campo;
import utils.Log;
import utils.ResultadosBusca;

public class FachadaCupomDesconto implements IFachada<CupomDesconto, Campo[]> {

	public boolean validarCampos(Campo[] campos) {
		ValidarCampos validarCampos = new ValidarCampos();

		boolean camposValidos = true;

		for(int i = 0; i < campos.length; i++) {
			if (validarCampos.processa(campos[i]) == false) {
				System.out.println("ERRO");
				System.out.println(campos[i].getNome());
				System.out.println(campos[i].getValor());
				System.out.println(campos[i].getMensagemErro());
				camposValidos = false;
			}
		}
		return camposValidos;
	}

	public ResultadosBusca select(Campo[] campos) {
		CupomDescontoDAO dao = new CupomDescontoDAO();

		dao.select(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}
		
	public String insert(CupomDesconto cupom, String usuarioResponsavel) {
		try {
			CupomDescontoDAO dao = new CupomDescontoDAO();
			dao.insert(cupom);

			Log log = new Log(usuarioResponsavel + " (admin)",
							 "CupomDesconto {id: " + cupom.getId() +
							 			  ", nome: " + cupom.getNome() + 
							 			  ", valor: " + cupom.getValor() + 
							 			  ", status: " + cupom.getStatus() + 
							 			  ", dataInicio: " + cupom.getDataInicio() + 
							 			  ", dataFim: " + cupom.getDataFim() + 
							 "}",
							 "Inserção");
        	log.registrar();

			return "Cupom inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(CupomDesconto cupom, String usuarioResponsavel) {
		CupomDescontoDAO dao = new CupomDescontoDAO();

		dao.delete(cupom.getId());

		return "Cupom excluído com sucesso!";
	}

	public String update(CupomDesconto cupom, String usuarioResponsavel) {
		CupomDescontoDAO dao = new CupomDescontoDAO();
		dao.update(cupom);

		Log log = new Log(usuarioResponsavel + " (admin)",
						 "CupomDesconto {id: " + cupom.getId() +
							 			  ", nome: " + cupom.getNome() + 
							 			  ", valor: " + cupom.getValor() + 
							 			  ", status: " + cupom.getStatus() + 
							 			  ", dataInicio: " + cupom.getDataInicio() + 
							 			  ", dataFim: " + cupom.getDataFim() + 
						 "}",
						 "Alteração");
    	log.registrar();

		return "Cupom alterado com sucesso!";
	}
	
	public String updateStatus(CupomDesconto cupom, String usuarioResponsavel) {
		CupomDescontoDAO dao = new CupomDescontoDAO();
		dao.updateStatus(cupom);
		Log log = new Log(usuarioResponsavel + " (admin)",
						 "CupomDesconto {id: " + cupom.getId() +
						 			  ", status: " + cupom.getStatus() +
						 "}",
						 "Alteração de status");
    	log.registrar();

		return "Status de cupom alterado com sucesso!";
	}

	public CupomDesconto selectSingle(long id) {
		CupomDescontoDAO dao = new CupomDescontoDAO();
		dao.selectSingle(id);
		return dao.selectSingleVal;
	}

}
