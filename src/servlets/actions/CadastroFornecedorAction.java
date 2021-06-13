package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaFornecedor;
import model.Fornecedor;
import model.TipoDocumento;
import model.Endereco;
import model.Documento;
import viewHelpers.FornecedorViewHelper;
import viewHelpers.LoginViewHelper;
import viewHelpers.UsuarioViewHelper;

import model.Pais;
import model.Estado;
import model.Cidade;
import model.Bairro;
import model.TipoEndereco;
import model.TipoResidencia;
import model.FuncaoEndereco;
import model.TipoLogradouro;

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
				Campo[] campos = FornecedorViewHelper.getCadastroFornecedorActionCampos(req);

				FachadaFornecedor fachada = new FachadaFornecedor();

				if(fachada.validarCampos(campos)) {
					String nome = campos[0].getValor();
					String email = campos[1].getValor();
			        int status = Integer.parseInt(campos[2].getValor());

			        Documento documento = new Documento(
			        					(long)1,
			        					null,
			        					campos[4].getValor(),
			        					new SimpleDateFormat("yyyy-MM-dd").parse(campos[5].getValor()),
			        					new TipoDocumento(Long.parseLong(campos[3].getValor()), null, null, null)
			        );

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

			    	Documento[] dArr = new Documento[1];
			    	Endereco[] eArr = new Endereco[1];
			    	dArr[0] = documento;
			    	eArr[0] = endereco;
			    	
			    	if (documento != null
			    	 && fachada.validarDocumentos(dArr)
			    	 && endereco != null
			    	 && fachada.validarEnderecos(eArr)) {
			        
			    		Fornecedor fornecedor = new Fornecedor((long)1, new Date(), nome, email, status, documento, endereco);

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
