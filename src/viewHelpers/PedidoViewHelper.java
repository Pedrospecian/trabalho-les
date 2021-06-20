package viewHelpers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.Campo;
import model.Pedido;
import model.Cliente;
import model.Bandeira;
import model.EntidadeDominio;
import model.Carrinho;
import model.CartaoCredito;
import model.ItemCarrinho;
import model.CupomTroca;

public class PedidoViewHelper implements IViewHelper<EntidadeDominio> {
	public static Campo[] getAdicionarCarrinhoCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(1, req.getParameter("quantidade"), true, "", true, "quantidade");

		return campos;
	}

	public Campo[] cadastroCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[18];

		campos[0] = new Campo(1, req.getParameter("enderecoEntrega"), true, "", true, "enderecoEntrega");
		campos[1] = new Campo(0, req.getParameter("cupomDesconto"), true, "", true, "CupomDesconto");
		campos[17] = new Campo(0, req.getParameter("tipoFrete"), true, "", true, "tipoFrete");

		boolean enderecoObrigatorio = false;

		if (Long.parseLong(req.getParameter("enderecoEntrega")) == 0) {
			enderecoObrigatorio = true;
		}

		campos[2] = new Campo(1, req.getParameter("nomeEndereco"), true, "", enderecoObrigatorio, "nomeEndereco");
		campos[3] = new Campo(1, req.getParameter("tipoEndereco"), true, "", enderecoObrigatorio, "tipoEndereco");
		campos[4] = new Campo(1, req.getParameter("tipoResidencia"), true, "", enderecoObrigatorio, "tipoResidencia");
		campos[5] = new Campo(1, req.getParameter("funcaoEndereco"), true, "", enderecoObrigatorio, "funcaoEndereco");
		campos[6] = new Campo(1, req.getParameter("pais"), true, "", enderecoObrigatorio, "pais");
		campos[7] = new Campo(1, req.getParameter("tipoLogradouro"), true, "", enderecoObrigatorio, "tipoLogradouro");
		campos[8] = new Campo(1, req.getParameter("logradouro"), true, "", enderecoObrigatorio, "logradouro");
		campos[9] = new Campo(1, req.getParameter("numero"), true, "", enderecoObrigatorio, "numero");
		campos[10] = new Campo(1, req.getParameter("complemento"), true, "", false, "complemento");
		campos[11] = new Campo(1, req.getParameter("bairro"), true, "", enderecoObrigatorio, "bairro");
		campos[12] = new Campo(1, req.getParameter("cidade"), true, "", enderecoObrigatorio, "cidade");
		campos[13] = new Campo(1, req.getParameter("estado"), true, "", enderecoObrigatorio, "estado");
		campos[14] = new Campo(1, req.getParameter("observacoes"), true, "", false, "observacoes");
		campos[15] = new Campo(1, req.getParameter("cep"), true, "", enderecoObrigatorio, "cep");
		//cupons de desconto
		campos[16] = new Campo(6, req.getParameter("arrIdCupomTroca"), true, "", false, "cuponsTroca");
		
		//cartoes de credito

		return campos;
	}

	public static CupomTroca[] createCuponsTrocaFromStrings(String nome, Pedido pedido) throws Exception {
		if (nome.equals("")) return null;
		String[] arrcuponsTrocaNomesStr = nome.split(",");
		CupomTroca[] cuponsTroca = new CupomTroca[arrcuponsTrocaNomesStr.length];

		for (int i = 0; i < cuponsTroca.length; i++) {
			cuponsTroca[i] = new CupomTroca((long)1, new Date(), arrcuponsTrocaNomesStr[i], 0, pedido);
		}

		return cuponsTroca;
	}

	public static double calcularTotalCarrinho(Carrinho carrinho) {
		double total = 0;

		if (carrinho == null || carrinho.getItensCarrinho() == null) return 0;
		
		for (ItemCarrinho itemCarrinho : carrinho.getItensCarrinho()) {
			total += itemCarrinho.getLivro().getPreco() * itemCarrinho.getQuantidade();
		}

		return total;
	}

	public Campo[] alterarStatusCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[1];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");

		return campos;
	}

	public static CartaoCredito[] organizaCartoesCredito(Cliente cliente, HttpServletRequest req) throws Exception {
		int numeroCartoesNovos = Integer.parseInt(req.getParameter("numeroCartoesNovos"));

		CartaoCredito[] cartoesCredito = new CartaoCredito[cliente.getCartoesCredito().length + numeroCartoesNovos];

		for (int i = 0; i < cliente.getCartoesCredito().length; i++) {
			cartoesCredito[i] = cliente.getCartoesCredito()[i];
			if ( req.getParameter("valorPagoCartao_" + cartoesCredito[i].getId()) == null || !req.getParameter("valorPagoCartao_" + cartoesCredito[i].getId()).matches("^[0-9]+((.|,)[0-9]+)?$") ) {
				cartoesCredito[i].setValorPago(0);
			}else {
				cartoesCredito[i].setValorPago(Double.parseDouble(req.getParameter("valorPagoCartao_" + cartoesCredito[i].getId())));
			}
			cartoesCredito[i].setJaExistente(true);
		}

		for (int i = 0; i < numeroCartoesNovos; i++) {
			cartoesCredito[cliente.getCartoesCredito().length + i] = new CartaoCredito(
				(long)1,
				new Date(),
				req.getParameter("cartaoNome_"+ (i+1)),
				req.getParameter("cartaoNumero_"+ (i+1)),
				req.getParameter("cartaoCodigo_"+ (i+1)),
				new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("cartaoValidade_"+ (i+1))),
				new Bandeira(Long.parseLong(req.getParameter("cartaoBandeira_"+ (i+1))), new Date(), "")
			);
			cartoesCredito[cliente.getCartoesCredito().length + i].setValorPago(Double.parseDouble(req.getParameter("valorPagoCartaoNovo_" + (i+1) )));
			if ( req.getParameter("cadastrarCartao_"+(i+1)) != null) {
				cartoesCredito[cliente.getCartoesCredito().length + i].setRegistrarCartao(true);
			} else {
				cartoesCredito[cliente.getCartoesCredito().length + i].setRegistrarCartao(false);
			}
			cartoesCredito[cliente.getCartoesCredito().length + i].setJaExistente(false);
		}

		return cartoesCredito;
	}

	public static Campo[] getSolicitarTrocaCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[2];

		campos[0] = new Campo(1, req.getParameter("id"), true, "", true, "id");
		campos[1] = new Campo(0, req.getParameter("trocaQtde"), true, "", true, "trocaQtde");

		return campos;
	}

	public Campo[] listagemCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[4];

		String resultadosPorPagina = "10";

		if (req.getParameter("resultadosPorPagina") != null && req.getParameter("resultadosPorPagina").matches("^[0-9]+$")) {
			resultadosPorPagina = req.getParameter("resultadosPorPagina");
		}

		campos[0] = new Campo(0, req.getParameter("idCliente"), true, "", true, "pedidos.idUsuario");
		campos[1] = new Campo(3, req.getParameter("dataCadastro"), true, "", true, "pedidos.dataCadastro");
		campos[2] = new Campo(1, req.getParameter("status"), true, "", true, "pedidos.status");
		campos[3] = new Campo(999, resultadosPorPagina, true, "", true, "resultadosPorPagina");

		return campos;
	}

	public static Campo[] getGerarGraficoCampos(HttpServletRequest req) {
		Campo[] campos = new Campo[3];

		campos[0] = new Campo(3, req.getParameter("dataInicio"), true, "", true, "dataInicio");
		campos[1] = new Campo(3, req.getParameter("dataFim"), true, "", true, "dataFim");
		campos[2] = new Campo(0, req.getParameter("tipo"), true, "", true, "tipo");

		return campos;
	}

	public Campo[] alterarCampos(HttpServletRequest req) {
		return null;
	}
	
	public EntidadeDominio instancia(Campo[] campos) {
    	return null;
    }
}