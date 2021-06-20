package facades;

import dao.TrocaDAO;
import model.Cliente;
import model.CupomTroca;
import model.ItemCarrinho;
import model.LivroEstoque;

import java.util.ArrayList;
import model.EntidadeDominio;
import utils.Campo;
import utils.ResultadosBusca;
import utils.Log;

public class FachadaTroca implements IFachada<Cliente, Campo[]> {
	public ResultadosBusca select(Campo[] campos) {
		TrocaDAO dao = new TrocaDAO();

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
		TrocaDAO dao = new TrocaDAO();

		return dao.encontraCupomTroca(cupom, idUsuario);
	}

	public ResultadosBusca selectSolicitacoesTroca() {
		TrocaDAO dao = new TrocaDAO();

		dao.selectSolicitacoesTroca();
		ArrayList arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public CupomTroca[] encontraCuponsTroca(CupomTroca[] cuponsTroca) {
		TrocaDAO dao = new TrocaDAO();
		return dao.encontraCuponsTroca(cuponsTroca);
	}

	public void solicitarTroca(ItemCarrinho itemCarrinho, String usuarioResponsavel) {
		TrocaDAO dao = new TrocaDAO();
		dao.solicitarTroca(itemCarrinho);

		Log log = new Log(usuarioResponsavel,
							 "SolicitacaoTroca {idItemCarrinho: " + itemCarrinho.getId() +
						   ", quantidadeItensTrocados: " + itemCarrinho.getQuantidadeItensTrocados() + 
							 "}",
							 "Inserção de solicitação de troca");
        log.registrar();
	}

	public void decidirPedidoTroca(long id, int aprovacao, String usuarioResponsavel) {
		TrocaDAO dao = new TrocaDAO();
		dao.decidirPedidoTroca(id, aprovacao);

		Log log = new Log(usuarioResponsavel,
							 "SolicitacaoTroca {id: " + id +
						   ", aprovacao: " + aprovacao + 
							 "}",
							 "Decisão acerca de pedido de troca");
        log.registrar();
	}

	public void confirmarRecebimentoTroca(long id, boolean retornarEstoque, String usuarioResponsavel) {
		TrocaDAO dao = new TrocaDAO();
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
