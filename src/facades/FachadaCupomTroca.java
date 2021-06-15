package facades;

import dao.ClienteDAO;
import dao.LivroDAO;
import dao.PedidoDAO;
import dao.CupomTrocaDAO;
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

public class FachadaCupomTroca implements IFachada<Cliente, Campo[]> {
	public ResultadosBusca select(Campo[] campos) {
		CupomTrocaDAO dao = new CupomTrocaDAO();

		dao.select(campos);
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);
		
		return rb;
	}

	public String insert(Cliente cliente, String usuarioResponsavel) {
		return "";
	}

	public String delete(Cliente cliente, String usuarioResponsavel) {
		return "";
	}

	public String update(Cliente cliente, String usuarioResponsavel) {
		return "";
	}

	public CupomTroca encontraCupomTroca(String cupom, long idUsuario) {
		CupomTrocaDAO dao = new CupomTrocaDAO();

		return dao.encontraCupomTroca(cupom, idUsuario);
	}

	public ResultadosBusca selectSolicitacoesTroca() {
		CupomTrocaDAO dao = new CupomTrocaDAO();

		dao.selectSolicitacoesTroca();
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public CupomTroca[] encontraCuponsTroca(CupomTroca[] cuponsTroca) {
		CupomTrocaDAO dao = new CupomTrocaDAO();
		return dao.encontraCuponsTroca(cuponsTroca);
	}

	public void solicitarTroca(ItemCarrinho itemCarrinho, String usuarioResponsavel) {
		CupomTrocaDAO dao = new CupomTrocaDAO();
		dao.solicitarTroca(itemCarrinho);

		Log log = new Log(usuarioResponsavel,
							 "SolicitacaoTroca {idItemCarrinho: " + itemCarrinho.getId() +
						   ", quantidadeItensTrocados: " + itemCarrinho.getQuantidadeItensTrocados() + 
							 "}",
							 "Inserção de solicitação de troca");
        log.registrar();
	}

	public void decidirPedidoTroca(long id, int aprovacao, String usuarioResponsavel) {
		CupomTrocaDAO dao = new CupomTrocaDAO();
		dao.decidirPedidoTroca(id, aprovacao);

		Log log = new Log(usuarioResponsavel,
							 "SolicitacaoTroca {id: " + id +
						   ", aprovacao: " + aprovacao + 
							 "}",
							 "Decisão acerca de pedido de troca");
        log.registrar();
	}

	public void confirmarRecebimentoTroca(long id, boolean retornarEstoque, String usuarioResponsavel) {
		CupomTrocaDAO dao = new CupomTrocaDAO();
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
}
