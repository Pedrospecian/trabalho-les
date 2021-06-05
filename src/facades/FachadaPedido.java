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

	public String insert(Cliente cliente, String usuarioResponsavel) {
		/*try {
			ClienteDAO dao = new ClienteDAO();
			dao.insert(cliente);

			return "Cliente inserido com sucesso!";
		}catch(Exception e) {
			e.printStackTrace();
			return "Erro de validação. Tente novamente.";
		}*/
		return "";
	}

	public String delete(Cliente cliente, String usuarioResponsavel) {
		/*ClienteDAO dao = new ClienteDAO();

		dao.delete(cliente.getId());

		return "Cliente excluído com sucesso!";*/
		return "";
	}

	public String update(Cliente cliente, String usuarioResponsavel) {
		/*ClienteDAO dao = new ClienteDAO();
		dao.update(cliente);

		return "Cliente alterado com sucesso!";*/
		return "";
	}

	public Carrinho selectCarrinho(long idCliente) {
		PedidoDAO dao = new PedidoDAO();
		dao.selectCarrinho(idCliente);
		return dao.selectCarrinhoVal;
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

	public ArrayList<ItemGrafico> gerarGrafico(Campo[] campos) {
		PedidoDAO dao = new PedidoDAO();

		dao.gerarGrafico(campos);
		ArrayList<ItemGrafico> arr = dao.selectGerarGraficoVals;
		
		return arr;
	}












	public void removerItemCarrinho(long id, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();

		dao.removerItemCarrinho(id);

		Log log = new Log(usuarioResponsavel,
							 "ItemCarrinho {id: " + id +
							 "}",
							 "Exclusão de item do carrinho");
        log.registrar();
	}

	public void alteraQteItemCarrinho(long id, int quantidade, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();
		LivroDAO livroDAO = new LivroDAO();

		dao.alteraQteItemCarrinho(id, quantidade);

		Log log = new Log(usuarioResponsavel,
							 "ItemCarrinho {id: " + id +							 
							 			  ", quantidade: " + quantidade + 
							 "}",
							 "Alteração de quantidade de item do carrinho");
        log.registrar();
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

	public String adicionarCarrinho(ItemCarrinho itemCarrinho, long idCarrinho, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();
		LivroDAO livroDAO = new LivroDAO();

		int estoque = livroDAO.contaEstoque(new Livro(itemCarrinho.getLivro().getId(), new Date()), idCarrinho);

		itemCarrinho.setQuantidade( Math.min(itemCarrinho.getQuantidade(), estoque) );

		dao.adicionarCarrinho(itemCarrinho);

		Log log = new Log(usuarioResponsavel,
							 "ItemCarrinho {id: " + itemCarrinho.getId() +
						   ", idCarrinho: " + idCarrinho + 
						   ", idLivro: " + itemCarrinho.getLivro().getId() + 
						   ", quantidade: " + itemCarrinho.getQuantidade() + 
							 "}",
							 "Produto adicionado ao carrinho");
        log.registrar();

		return "Produto adicionado ao carrinho com sucesso!";
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

	public void solicitarTroca(ItemCarrinho itemCarrinho, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();
		dao.solicitarTroca(itemCarrinho);

		Log log = new Log(usuarioResponsavel,
							 "SolicitacaoTroca {idItemCarrinho: " + itemCarrinho.getId() +
						   ", quantidadeItensTrocados: " + itemCarrinho.getQuantidadeItensTrocados() + 
							 "}",
							 "Inserção de solicitação de troca");
        log.registrar();
	}

	public void decidirPedidoTroca(long id, int aprovacao, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();
		dao.decidirPedidoTroca(id, aprovacao);

		Log log = new Log(usuarioResponsavel,
							 "SolicitacaoTroca {id: " + id +
						   ", aprovacao: " + aprovacao + 
							 "}",
							 "Decisão acerca de pedido de troca");
        log.registrar();
	}

	public void confirmarRecebimentoTroca(long id, boolean retornarEstoque, String usuarioResponsavel) {
		PedidoDAO dao = new PedidoDAO();
		EntidadeDominio[] retornos = dao.confirmarRecebimentoTroca(id, retornarEstoque);

		Log log = new Log(usuarioResponsavel,
							 "SolicitacaoTroca {id: " + id +
						   ", retornarEstoque: " + retornarEstoque + 
							 "}",
							 "Confirmação de recebimento de troca");
        log.registrar();

        CupomTroca ct = (CupomTroca) retornos[0];

        //registra criacao de cupom de troca
        Log log2 = new Log(usuarioResponsavel,
							 "CupomTroca {id: " + ct.getId() +
						   ", nome: " + ct.getNome() + 
						   ", valor: " + ct.getValor() + 
						   ", pedido: " + ct.getPedido().getId() + 
							 "}",
							 "Geração de cupom de troca");
        log2.registrar();

        //registra as entradas de estoque
        if (retornos[1] != null) {
        	LivroEstoque le = (LivroEstoque) retornos[1];
        	Log log3 = new Log(usuarioResponsavel,
							 "LivroEstoque {id: " + le.getId() +
						   ", quantidade: " + le.getQuantidade() +
						   ", dataEntrada: " + le.getDataEntrada() + 
						   ", livro: " + le.getLivro().getId() + 
							 "}",
							 "Entrada no estoque via devolução");
       		log3.registrar();
       	}
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

        /*for(ItemCarrinho le : lessCompra ) {
    		System.out.println( "{id: " + le.getId() +
					   ", quantidade: " + le.getQuantidade() +
					   ", livro: " + le.getLivro().getId() + 
					 "}, ");
    	}*/

        //registra as baixas de estoque
        if (lessCompra != null) {

        	String les = "";

        	for(ItemCarrinho le : lessCompra ) {
        		les += "{id: " + le.getId() +
						   ", quantidade: " + le.getQuantidade() +
						   ", livro: " + le.getLivro().getId() + 
						 "}, ";
        	}

        	les = les.substring(0, les.length() - 2);
        	/*Log log3 = new Log(usuarioResponsavel,
							 "LivroEstoque {id: " + le.getId() +
						   ", quantidade: " + le.getQuantidade() +
						   ", dataEntrada: " + le.getDataEntrada() + 
						   ", livro: " + le.getLivro().getId() + 
							 "}",
							 "Entrada no estoque via devolução");
       		log3.registrar();*/
       		Log log3 = new Log(usuarioResponsavel,
							 "LivroEstoque: [" + les + "]}",
							 "Baixas de estoque via aceite de compra");
       		log3.registrar();
        	//long id, Date dataCadastro, int quantidade, double custo, Date dataEntrada, Fornecedor fornecedor, Livro livro, int tipoMovimentacao
        
        }
	}	
}
