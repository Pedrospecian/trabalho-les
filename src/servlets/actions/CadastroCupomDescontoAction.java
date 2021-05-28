package servlets.actions;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;

import facades.FachadaCupomDesconto;
import model.CupomDesconto;

import viewHelpers.CupomDescontoViewHelper;
import viewHelpers.LoginViewHelper;

public class CadastroCupomDescontoAction extends HttpServlet {
	private static final long serialVersionUID = 12;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 5)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			try {
				Campo[] campos = CupomDescontoViewHelper.getCadastroCupomActionCampos(req);

				FachadaCupomDesconto fachada = new FachadaCupomDesconto();

				if(fachada.validarCampos(campos)) {
					String nome = campos[0].getValor();
			        double valor = Double.parseDouble(campos[1].getValor());
			        int status = Integer.parseInt(campos[2].getValor());
			        Date dataInicio = new SimpleDateFormat("yyyy-MM-dd").parse(campos[3].getValor());		       	
			        Date dataFim = new SimpleDateFormat("yyyy-MM-dd").parse(campos[4].getValor());

		        	CupomDesconto cupom = new CupomDesconto((long)1, new Date(), nome, valor, dataInicio, dataFim, status);

		        	fachada.insert(cupom);
		        	resp.sendRedirect("/trabalho-les/listagemCupons");
		        } else {
	    	       
		        }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	} 
	    }
	}
}
