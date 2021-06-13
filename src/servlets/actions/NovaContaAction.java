package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaCliente;
import model.CartaoCredito;
import model.Cliente;
import model.Endereco;
import model.Telefone;
import model.Documento;
import model.TipoCliente;
import viewHelpers.ClienteViewHelper;
import viewHelpers.UsuarioViewHelper;

public class NovaContaAction extends HttpServlet {
	private static final long serialVersionUID = 12;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		try {
			Campo[] campos = ClienteViewHelper.getCadastroClienteActionCampos(req);

			campos[4].setValor("1");

			FachadaCliente fachada = new FachadaCliente();

		    String email = campos[30].getValor();

			if(fachada.validarCampos(campos) && fachada.validaEmailExistente(email)) {
				String nome = campos[0].getValor();
		        int genero = Integer.parseInt(campos[1].getValor());
		        Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse(campos[2].getValor());
		        long tipoCliente = Long.parseLong(campos[3].getValor());
		        int status = Integer.parseInt(campos[4].getValor());
		        String senha = campos[31].getValor();

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

		    	if (documentos != null && fachada.validarDocumentos(documentos, true) && enderecos != null && fachada.validarEnderecos(enderecos, true)) {
		        
		        	Cliente cliente = new Cliente((long)1, new Date(), documentos, nome, genero, dataNascimento, new TipoCliente(tipoCliente, new Date(), "", ""), enderecos, status, cartoesCredito, email, senha, telefones);

		        	fachada.insert(cliente, "cliente novo");

		        	for (int i = 0; i < cartoesCredito.length; i++) {
		        		if (cartoesCredito[i].getNumero().equals(campos[33].getValor())) {
			        		fachada.setCartaoPreferencial(cliente, cartoesCredito[i], "cliente novo");
			        		break;
			        	}
		        	}

		        	//se loga com os dados do cliente recém-cadastrado
		        	Cookie cookieId=new Cookie("login_id", String.valueOf(cliente.getId())); 
					Cookie cookieNome=new Cookie("nome", String.valueOf(cliente.getNome()));
					Cookie cookieTipo=new Cookie("tipo", "cliente");  

		            resp.addCookie(cookieId);
		            resp.addCookie(cookieNome); 
		            resp.addCookie(cookieTipo);
		            resp.sendRedirect("/trabalho-les/home");
		        } else {
					
		        }
	        } else {
    	        resp.sendRedirect(req.getHeader("referer") + "?erro=true");
	        }
    	} catch(Exception e) {
    		e.printStackTrace();
    	} 
	}
}
