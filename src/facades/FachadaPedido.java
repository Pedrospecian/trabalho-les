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

import java.util.ArrayList;
import java.util.Date;

import model.Documento;
import model.Endereco;
import strategies.ValidarCampos;
import strategies.ValidarValorCompra;
import utils.Campo;
import utils.DadosCalculoFrete;
import utils.ResultadosBusca;
import strategies.ValidacaoDocumentos;
import strategies.ValidacaoEnderecos;
import strategies.VerificarCamposCpf;
import strategies.CalculaValorFrete;
import model.Pedido;

public class FachadaPedido implements IFachada<Cliente, Campo[]> {

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

	public boolean validarDocumentos(Documento[] documentos, boolean precisaValidarCpf) {
		ValidacaoDocumentos val = new ValidacaoDocumentos();
		ClienteDAO dao = new ClienteDAO();
		VerificarCamposCpf ver = new VerificarCamposCpf();
		boolean cpfValido = !precisaValidarCpf || ver.processa(documentos);

		return val.processa(documentos) && !dao.documentosExistem(documentos) && cpfValido;
	}

	public boolean validarEnderecos(Endereco[] enderecos) {
		ValidacaoEnderecos val = new ValidacaoEnderecos();

		return val.processa(enderecos);
	}

	public String insert(Cliente cliente) {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.insert(cliente);

			return "Cliente inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}
	}

	public String delete(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();

		dao.delete(cliente.getId());

		return "Cliente excluído com sucesso!";
	}

	public void removerItemCarrinho(long id) {
		PedidoDAO dao = new PedidoDAO();

		dao.removerItemCarrinho(id);
	}

	public void alteraQteItemCarrinho(long id, int quantidade) {
		PedidoDAO dao = new PedidoDAO();
		LivroDAO livroDAO = new LivroDAO();

		dao.alteraQteItemCarrinho(id, quantidade);
	}

	public String update(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		dao.update(cliente);

		return "Cliente alterado com sucesso!";
	}

	public String updateStatus(Pedido pedido) {
		PedidoDAO dao = new PedidoDAO();
		dao.updateStatus(pedido);

		return "Status de pedido alterado com sucesso!";
	}

	public String adicionarCarrinho(ItemCarrinho itemCarrinho, long idCarrinho) {
		PedidoDAO dao = new PedidoDAO();
		LivroDAO livroDAO = new LivroDAO();

		int estoque = livroDAO.contaEstoque(new Livro(itemCarrinho.getLivro().getId(), new Date()), idCarrinho);

		itemCarrinho.setQuantidade( Math.min(itemCarrinho.getQuantidade(), estoque) );

		dao.adicionarCarrinho(itemCarrinho);

		return "Produto adicionado ao carrinho com sucesso!";
	}

	public Carrinho selectCarrinho(long idCliente) {
		PedidoDAO dao = new PedidoDAO();
		dao.selectCarrinho(idCliente);
		return dao.selectCarrinhoVal;
	}

	public boolean efetuaCompra(Pedido pedido, CartaoCredito[] cartoes, CupomTroca[] cuponsTroca) {
		PedidoDAO dao = new PedidoDAO();
		return dao.efetuaCompra(pedido, cartoes, cuponsTroca);
	}

	public boolean validarCompraCampos(Campo[] campos) {
		return true;
	}

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

	public void solicitarTroca(ItemCarrinho itemCarrinho) {
		PedidoDAO dao = new PedidoDAO();
		dao.solicitarTroca(itemCarrinho);		
	}

	public CupomDesconto encontraCupomDesconto(String cupom) {
		PedidoDAO dao = new PedidoDAO();

		return dao.encontraCupomDesconto(cupom);
	}

	public CupomTroca encontraCupomTroca(String cupom, long idUsuario) {
		PedidoDAO dao = new PedidoDAO();

		return dao.encontraCupomTroca(cupom, idUsuario);
	}

	public ResultadosBusca selectSolicitacoesTroca() {
		PedidoDAO dao = new PedidoDAO();

		dao.selectSolicitacoesTroca();
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public void decidirPedidoTroca(long id, int aprovacao) {
		PedidoDAO dao = new PedidoDAO();
		dao.decidirPedidoTroca(id, aprovacao);		
	}

	public void confirmarRecebimentoTroca(long id, boolean retornarEstoque) {
		PedidoDAO dao = new PedidoDAO();
		dao.confirmarRecebimentoTroca(id, retornarEstoque);
	}

	public CupomTroca[] encontraCuponsTroca(CupomTroca[] cuponsTroca) {
		PedidoDAO dao = new PedidoDAO();
		return dao.encontraCuponsTroca(cuponsTroca);
	}

	public ResultadosBusca listagemCuponsTroca(long id) {
		PedidoDAO dao = new PedidoDAO();

		dao.listagemCuponsTroca(id);
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

	public void reprovaPedido(Pedido pedido) {
		PedidoDAO dao = new PedidoDAO();

		dao.reprovaPedido(pedido);
	}

	public void processaPedido(Pedido pedido) {
		PedidoDAO dao = new PedidoDAO();

		dao.processaPedido(pedido);
	}
}
