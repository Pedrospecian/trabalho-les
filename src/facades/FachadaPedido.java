package facades;

import dao.ClienteDAO;
import dao.LivroDAO;
import dao.PedidoDAO;
import model.Cliente;
import model.CupomDesconto;
import model.CupomTroca;
import model.Carrinho;
import model.CartaoCredito;
import model.ItemCarrinho;
import model.Livro;
import model.LivroEstoque;

import java.util.ArrayList;
import java.util.Date;

import model.Documento;
import model.Endereco;
import model.EntidadeDominio;
import strategies.ValidarCampos;
import strategies.ValidarValorCompra;
import utils.Campo;
import utils.DadosCalculoFrete;
import utils.ItemGrafico;
import utils.ResultadosBusca;
import utils.Log;
import strategies.ValidacaoDocumentos;
import strategies.ValidacaoEnderecos;
import strategies.VerificarCamposCpf;
import strategies.CalculaValorFrete;
import model.Pedido;

public class FachadaPedido implements IFachada<Pedido, Campo[]> {
	public boolean validarCampos(Campo[] campos) {
		ValidarCampos validarCampos = new ValidarCampos();

		boolean camposValidos = true;

		for(int i = 0; i < campos.length; i++) {
			if (campos[i].getObrigatorio() && validarCampos.processa(campos[i]) == false) {
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
		PedidoDAO dao = new PedidoDAO();

		dao.select(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public Pedido selectSingle(long id) {
		PedidoDAO dao = new PedidoDAO();
		dao.selectSingle(id);
		return dao.selectSingleVal;
	}

	/*public boolean validarCompraCampos(Campo[] campos) {
		return true;
	}*/

	public boolean validarValorCompra(Pedido pedido) {
		ValidarValorCompra val = new ValidarValorCompra();

		return val.processa(pedido);
	}

	public ResultadosBusca selectPedidosPorCliente(long id, Campo[] campos) {
		PedidoDAO dao = new PedidoDAO();

		dao.selectPedidosPorCliente(id, campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public DadosCalculoFrete getDadosCalculoFrete(long idCarrinho) {
		PedidoDAO dao = new PedidoDAO();

		dao.getDadosCalculoFrete(idCarrinho);

		return dao.selectDadosCalculoFreteSingle;
	}

	public double[] calculaValorFrete(DadosCalculoFrete dados) {
		CalculaValorFrete str = new CalculaValorFrete();
		Double[] arr = str.processa(dados);
		double[] arr2 = {arr[0].doubleValue(), arr[1].doubleValue()};
		return arr2;
	}

	public ArrayList<ItemGrafico> gerarGrafico(Campo[] campos) {
		PedidoDAO dao = new PedidoDAO();

		dao.gerarGrafico(campos);
		ArrayList<ItemGrafico> arr = dao.selectVals;
		
		return arr;
	}

	public String updateStatus(Pedido pedido, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();
		dao.updateStatus(pedido);

		Log log = new Log(usuarioResponsavel,
							 "Pedido {id: " + pedido.getId() +							 
							 			  ", status: " + pedido.getStatus() + 
							 "}",
							 "Alteração de status");
        log.registrar();

		return "Status de pedido alterado com sucesso!";
	}

	public boolean efetuaCompra(Pedido pedido, CartaoCredito[] cartoes, CupomTroca[] cuponsTroca, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();
		boolean efetuouCompra = dao.efetuaCompra(pedido, cartoes, cuponsTroca);

		if (efetuouCompra) {
			String cuponsTrocaStr = "";
			String cartoesStr = "";

			if (cuponsTroca != null) {
				for (int i = 0; i < cuponsTroca.length; i++) {
					cuponsTrocaStr += "{id: " + cuponsTroca[i].getId() + ", valor: " + cuponsTroca[i].getValor() + "}, ";
				}

				cuponsTrocaStr = cuponsTrocaStr.substring(0, cuponsTrocaStr.length() - 2);
			}

			if (cartoes != null) {
				for (int i = 0; i < cartoes.length; i++) {
					cartoesStr += "{id: " + cartoes[i].getId() + ", valor: " + cartoes[i].getValorPago() + "}, ";
				}

				cartoesStr = cartoesStr.substring(0, cartoesStr.length() - 2);
			}
			
			long idCupomDesconto = 0;
			
			if (pedido.getCupomDesconto() != null) {
				idCupomDesconto = pedido.getCupomDesconto().getId();
			}

			Log log = new Log(usuarioResponsavel,
							 "Pedido {id: " + pedido.getId() +
						   ", cliente: " + pedido.getCliente().getId() +
						   ", carrinho: " + pedido.getCarrinho().getId() +
						   ", valorFrete: " + pedido.getValorFrete() +
						   ", tipoServico: " + pedido.getTipoServico() +
						   ", prazo: " + pedido.getPrazo() +
						   ", valorTotal: " + pedido.getValorTotal() +
						   ", endereco: " + pedido.getEndereco().getId() +
						   ", cupomDesconto: " + idCupomDesconto +
						   ", cuponsTroca: [" + cuponsTrocaStr + "]" +
						   ", cartoesCredito: [" + cartoesStr + "]" +
						   ", status: " + pedido.getStatus() + 
							 "}",
							 "Inserção de pedido");
       		log.registrar();

			return true;
		}

		return false;
	}

	public void reprovaPedido(Pedido pedido, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();

		dao.reprovaPedido(pedido);

		Log log = new Log(usuarioResponsavel,
							 "Pedido {id: " + pedido.getId() +							 
						   ", status: " + pedido.getStatus() + 
							 "}",
							 "Reprovação");
        log.registrar();
	}

	public void processaPedido(Pedido pedido, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();

		ArrayList<ItemCarrinho> lessCompra = dao.processaPedido(pedido);

		Log log = new Log(usuarioResponsavel,
							 "Pedido {id: " + pedido.getId() +							 
						   ", status: " + pedido.getStatus() + 
							 "}",
							 "Processamento");
        log.registrar();

        //registra as baixas de estoque
        if (lessCompra != null) {

        	String les = "";

        	for(ItemCarrinho le : lessCompra ) {
        		les += "{id: " + le.getId() +
						   ", quantidade: " + le.getQuantidade() +
						   ", livro: " + le.getLivro().getId() + 
						 "}, ";
        	}
        	
        	if (les.length() >= 2) les = les.substring(0, les.length() - 2);

       		Log log3 = new Log(usuarioResponsavel,
							 "LivroEstoque: [" + les + "]}",
							 "Baixas de estoque via aceite de compra");
       		log3.registrar();
        }
	}

	public String insert(Pedido pedido, String usuarioResponsavel) {
		return "";
	}

	public String delete(Pedido pedido, String usuarioResponsavel) {
		return "";
	}

	public String update(Pedido pedido, String usuarioResponsavel) {
		return "";
	}
}
