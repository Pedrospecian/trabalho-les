package facades;

import dao.CarrinhoDAO;
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

public class FachadaCarrinho implements IFachada<EntidadeDominio, Campo[]> {
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
		return null;
	}

	public String insert(EntidadeDominio entidadeDominio, String usuarioResponsavel) {
		return "";
	}

	public String delete(EntidadeDominio entidadeDominio, String usuarioResponsavel) {
		return "";
	}

	public String update(EntidadeDominio entidadeDominio, String usuarioResponsavel) {
		return "";
	}

	public Carrinho selectCarrinho(long idCliente) {
		CarrinhoDAO dao = new CarrinhoDAO();
		dao.selectCarrinho(idCliente);
		return dao.selectCarrinhoVal;
	}

	public void removerItemCarrinho(long id, String usuarioResponsavel) {
		CarrinhoDAO dao = new CarrinhoDAO();

		dao.removerItemCarrinho(id);

		Log log = new Log(usuarioResponsavel,
							 "ItemCarrinho {id: " + id +
							 "}",
							 "Exclusão de item do carrinho");
        log.registrar();
	}

	public void alteraQteItemCarrinho(long id, int quantidade, String usuarioResponsavel) {
		CarrinhoDAO dao = new CarrinhoDAO();

		dao.alteraQteItemCarrinho(id, quantidade);

		Log log = new Log(usuarioResponsavel,
							 "ItemCarrinho {id: " + id +							 
							 			  ", quantidade: " + quantidade + 
							 "}",
							 "Alteração de quantidade de item do carrinho");
        log.registrar();
	}	

	public String adicionarCarrinho(ItemCarrinho itemCarrinho, long idCarrinho, String usuarioResponsavel) {
		CarrinhoDAO dao = new CarrinhoDAO();
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
}
