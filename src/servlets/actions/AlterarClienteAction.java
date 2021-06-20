package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

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
import model.TipoCliente;
import model.Telefone;
import viewHelpers.ClienteViewHelper;
import viewHelpers.LoginViewHelper;

public class AlterarClienteAction extends HttpServlet {
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
				Campo[] campos = vh.alterarCampos(req);

				FachadaCliente fachada = new FachadaCliente();

				if(fachada.validarCampos(campos)) {
			        long id = Long.parseLong(campos[0].getValor());
					String nome = campos[1].getValor();
			        int genero = Integer.parseInt(campos[2].getValor());
			        Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse(campos[3].getValor());
			        long tipoCliente = Long.parseLong(campos[4].getValor());
			        int status = Integer.parseInt(campos[5].getValor());
					String email = campos[35].getValor();

			        Documento[] documentos = ClienteViewHelper.createDocumentosFromStrings(
			        							campos[6].getValor(),
			        							campos[7].getValor(),
			        							campos[8].getValor());
			        Endereco[] enderecos = ClienteViewHelper.createEnderecosFromStrings(
			        							campos[9].getValor(),
			        							campos[10].getValor(),
			        							campos[11].getValor(),
			        							campos[12].getValor(),
			        							campos[13].getValor(),
			        							campos[14].getValor(),
			        							campos[15].getValor(),
			        							campos[16].getValor(),
			        							campos[17].getValor(),
			        							campos[20].getValor(),
			        							campos[21].getValor(),
			        							campos[22].getValor(),
			        							campos[23].getValor(),
			        							campos[24].getValor());
			        
			        CartaoCredito[] cartoesCredito = ClienteViewHelper.createCartoesCreditoFromStrings(
			    								campos[25].getValor(),
			    								campos[26].getValor(),
			    								campos[27].getValor(),
			    								campos[28].getValor(),
			    								campos[29].getValor());

			        Telefone[] telefones = ClienteViewHelper.createTelefonesFromStrings(
									    		campos[30].getValor(),
									    		campos[31].getValor(),
									    		campos[32].getValor());

			        Endereco[] enderecosRemovidos = ClienteViewHelper.createEnderecosRemovidosFromStrings(campos[19].getValor());
			        CartaoCredito[] cartoesCreditoRemovidos = ClienteViewHelper.createCartoesCreditoRemovidosFromStrings(campos[33].getValor());
			        Telefone[] telefonesRemovidos = ClienteViewHelper.createTelefonesRemovidosFromStrings(campos[34].getValor());		        
		        
		        	Cliente cliente = new Cliente(id, new Date(), documentos, nome, genero, dataNascimento, new TipoCliente(tipoCliente, new Date(), "", ""), enderecos, status, cartoesCredito, email, "", telefones);
		        	
		        	boolean documentosInvalidos = false;
		        	boolean enderecosInvalidos = false;
		        	boolean cartoesCreditoInvalidos = false;
		        	boolean telefonesInvalidos = false;

		        	if (enderecos != null && !fachada.validarEnderecos(enderecos, false)) {
		        		enderecosInvalidos = true;
		        	}

		        	if (documentos != null && !fachada.validarDocumentos(documentos, false)) {
		        		documentosInvalidos = true;
		        	}

		        	if (cartoesCredito != null && !fachada.validarCartoesCredito(cartoesCredito)) {
		        		cartoesCreditoInvalidos = true;
		        	}

		        	if (telefones != null && !fachada.validarTelefones(telefones)) {
		        		telefonesInvalidos = true;
		        	}

		        	if (!enderecosInvalidos && !documentosInvalidos && !cartoesCreditoInvalidos && !telefonesInvalidos) {
			        	fachada.update(cliente, LoginViewHelper.getLogInfo(req, resp));

			        	if (enderecosRemovidos != null) {
			        		fachada.deleteEnderecos(enderecosRemovidos, LoginViewHelper.getLogInfo(req, resp));
			        	}

			        	if (cartoesCreditoRemovidos != null) {
			        		fachada.deleteCartoesCredito(cartoesCreditoRemovidos, LoginViewHelper.getLogInfo(req, resp));
			        	}

			        	if (telefonesRemovidos != null) {
			        		fachada.deleteTelefones(telefonesRemovidos, LoginViewHelper.getLogInfo(req, resp));
			        	}

			        	//muda o cartao preferencial para um que ja existe 
			        	if (cartoesCredito != null && campos[36].getValor().length() == 16) {
			        		for (int i = 0; i < cartoesCredito.length; i++) {
				        		if (cartoesCredito[i].getNumero().equals(campos[36].getValor())) {
					        		fachada.setCartaoPreferencial(cliente, cartoesCredito[i], LoginViewHelper.getLogInfo(req, resp));
					        		break;
					        	}
				        	}
			        	} else {
			        		fachada.setCartaoPreferencial(cliente, new CartaoCredito(Long.parseLong(campos[36].getValor()), null, null, null, null, null, null ), LoginViewHelper.getLogInfo(req, resp));
			        	}
			        
		        		resp.sendRedirect("/trabalho-les/listagemClientes");
		        	}
		        } else {
		        	//retorna com os dados invalidos
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
}
