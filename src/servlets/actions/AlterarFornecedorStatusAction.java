package servlets.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import facades.FachadaFornecedor;
import model.Fornecedor;
import viewHelpers.FornecedorViewHelper;
import viewHelpers.LoginViewHelper;

public class AlterarFornecedorStatusAction extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			resp.setContentType("text/html");		    
			try {
				FornecedorViewHelper vh = new FornecedorViewHelper();
				Campo[] campos = vh.alterarStatusCampos(req);

				FachadaFornecedor fachada = new FachadaFornecedor();

				if(fachada.validarCampos(campos)) {
			        long id = Long.parseLong(campos[0].getValor());
					int status = Integer.parseInt(campos[1].getValor());
		        
		        	Fornecedor fornecedor = new Fornecedor(id, new Date(), "", "", status, null, null);
		        	fachada.updateStatus(fornecedor, LoginViewHelper.getLogInfo(req, resp));

		        	resp.sendRedirect("/trabalho-les/listagemFornecedores");
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
    	}
	}
}
