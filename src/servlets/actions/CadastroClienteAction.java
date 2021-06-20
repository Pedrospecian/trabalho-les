package servlets.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaCliente;
import model.CartaoCredito;
import model.Cliente;
import model.Endereco;
import model.Documento;
import model.Telefone;
import viewHelpers.ClienteViewHelper;
import viewHelpers.LoginViewHelper;

public class CadastroClienteAction extends HttpServlet {
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
				ClienteViewHelper vh = new ClienteViewHelper();
				Campo[] campos = vh.cadastroCampos(req);

				/*for (int i = 0; i < campos.length; i++) {
					System.out.println(campos[i].getNome() + " => " + campos[i].getValor());
				}*/

				FachadaCliente fachada = new FachadaCliente();			

				if(fachada.validarCampos(campos) && fachada.validaEmailExistente(campos[30].getValor())) {
			        Documento[] documentos = ClienteViewHelper.createDocumentosFromStrings(
			        							campos[5].getValor(),
			        							campos[6].getValor(),
			        							campos[7].getValor());
			    	Endereco[] enderecos = ClienteViewHelper.createEnderecosFromStrings(
			        							campos[8].getValor(),
			        							campos[9].getValor(),
			        							campos[10].getValor(),
			        							campos[11].getValor(),
			        							campos[12].getValor(),
			        							campos[13].getValor(),
			        							campos[14].getValor(),
			        							campos[15].getValor(),
			        							campos[16].getValor(),
			        							campos[17].getValor(),
			        							campos[23].getValor(),
			        							campos[24].getValor(),
			        							campos[25].getValor(),
			        							campos[26].getValor());
			    	
			    	CartaoCredito[] cartoesCredito = ClienteViewHelper.createCartoesCreditoFromStrings(
			    								campos[18].getValor(),
			    								campos[19].getValor(),
			    								campos[20].getValor(),
			    								campos[21].getValor(),
			    								campos[22].getValor());

			    	Telefone[] telefones = ClienteViewHelper.createTelefonesFromStrings(
			    		campos[27].getValor(),
			    		campos[28].getValor(),
			    		campos[29].getValor());

			    	if (documentos != null && fachada.validarDocumentos(documentos, true)
			    	 && enderecos != null && fachada.validarEnderecos(enderecos, true)
			    	 && cartoesCredito != null && fachada.validarCartoesCredito(cartoesCredito)
			    	 && telefones != null && fachada.validarTelefones(telefones)) {
			    	 	Cliente cliente = vh.instanciaCliente(campos, documentos, enderecos, cartoesCredito, telefones);
			        	fachada.insert(cliente, LoginViewHelper.getLogInfo(req, resp));

			        	for (int i = 0; i < cartoesCredito.length; i++) {
			        		if (cartoesCredito[i].getNumero().equals(campos[33].getValor())) {
				        		fachada.setCartaoPreferencial(cliente, cartoesCredito[i], LoginViewHelper.getLogInfo(req, resp));
				        		break;
				        	}
			        	}

			        	resp.sendRedirect("/trabalho-les/listagemClientes");
			        } else {
			        	System.out.println("Ocorreu um erro ao inserir o cliente!");
			        	/*System.out.println(documentos == null);
			        	System.out.println(enderecos == null);
			        	System.out.println(cartoesCredito == null);
			        	System.out.println(telefones == null);*/
			        }
		        } else {

		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	} 
	    }
	}
}
