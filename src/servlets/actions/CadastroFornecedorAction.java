package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaFornecedor;
import model.Fornecedor;
import model.Endereco;
import model.Documento;
import viewHelpers.FornecedorViewHelper;
import viewHelpers.LoginViewHelper;

public class CadastroFornecedorAction extends HttpServlet {
	private static final long serialVersionUID = 12;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 1)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			try {
				FornecedorViewHelper vh = new FornecedorViewHelper();
				Campo[] campos = vh.cadastroCampos(req);

				FachadaFornecedor fachada = new FachadaFornecedor();

				if(fachada.validarCampos(campos)) {
			        Documento documento = vh.instanciaDocumento(campos);
			        Endereco endereco = vh.instanciaEndereco(campos);

			    	Documento[] dArr = new Documento[1];
			    	Endereco[] eArr = new Endereco[1];
			    	dArr[0] = documento;
			    	eArr[0] = endereco;
			    	
			    	if (documento != null
			    	 && fachada.validarDocumentos(dArr)
			    	 && endereco != null
			    	 && fachada.validarEnderecos(eArr)) {			        
			    		Fornecedor fornecedor = vh.instanciaFornecedor(campos, documento, endereco);
			        	fachada.insert(fornecedor, LoginViewHelper.getLogInfo(req, resp));
			        	resp.sendRedirect("/trabalho-les/listagemFornecedores");
			        } else {

			        }
		        } else {

		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	} 
	    }
	}
}
