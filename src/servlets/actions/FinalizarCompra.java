package servlets.actions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Campo;
import utils.DadosCalculoFrete;
import facades.FachadaCarrinho;
import facades.FachadaCliente;
import facades.FachadaCupomDesconto;
import facades.FachadaTroca;
import facades.FachadaPedido;
import model.CartaoCredito;
import model.Cliente;
import model.CupomDesconto;
import model.CupomTroca;
import model.Endereco;
import model.Pedido;
import model.Bairro;
import model.Cidade;
import model.Estado;
import model.Pais;
import model.TipoEndereco;
import model.Carrinho;
import model.TipoResidencia;
import model.FuncaoEndereco;
import model.TipoLogradouro;
import viewHelpers.PedidoViewHelper;
import viewHelpers.LoginViewHelper;

public class FinalizarCompra extends HttpServlet {
	private static final long serialVersionUID = 12;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginViewHelper lvh = new LoginViewHelper();
		if(!lvh.isAuthorized(req, resp, 2)){
			resp.sendRedirect("/trabalho-les/home");
		}else{
			resp.setContentType("text/html");
			try {
				FachadaPedido fachada = new FachadaPedido();
				FachadaCliente fachadaCliente = new FachadaCliente();
				FachadaCarrinho fachadaCarrinho = new FachadaCarrinho();
				FachadaCupomDesconto fachadaCupomDesconto = new FachadaCupomDesconto();
				FachadaTroca fachadaCupomTroca = new FachadaTroca();
				
				PedidoViewHelper vh = new PedidoViewHelper();
				Campo[] campos = vh.cadastroCampos(req);

				if(fachada.validarCompraCampos(campos)) {
					Carrinho carrinho = fachadaCarrinho.selectCarrinho(lvh.getUsuarioLogadoId(req, resp));
					double valorTotal = PedidoViewHelper.calcularTotalCarrinho(carrinho);
					Cliente cliente = fachadaCliente.selectSingle(lvh.getUsuarioLogadoId(req, resp), false);
					Endereco endereco;

					if (Long.parseLong(campos[0].getValor()) == 0) {
						endereco = new Endereco(
												(long) 0,
												new Date(),
												campos[8].getValor(),
												campos[9].getValor(),
												campos[15].getValor(),
												campos[10].getValor(),
												new Bairro((long)1, new Date(), campos[11].getValor(), new Cidade(
													(long)1, new Date(), campos[12].getValor(), new Estado(
														(long)1, new Date(), campos[13].getValor(), new Pais(
															Long.parseLong(campos[6].getValor()), new Date(), ""
														)
													)
												)),
												new TipoEndereco(Long.parseLong(campos[3].getValor()), new Date(), "", ""),
												campos[2].getValor(),
												new TipoResidencia(Long.parseLong(campos[4].getValor()), new Date(), "", ""),
												new FuncaoEndereco(Long.parseLong(campos[5].getValor()), new Date(), "", ""),
												new TipoLogradouro(Long.parseLong(campos[7].getValor()), new Date(), "", ""),
												campos[14].getValor()
						);
						endereco.setNovo(true);
					} else {
						endereco = fachadaCliente.selectEnderecoSingle(Long.parseLong(campos[0].getValor()));
						endereco.setNovo(false);
					}

					CupomDesconto cupomDesconto = fachadaCupomDesconto.encontraCupomDesconto(campos[1].getValor());
					CartaoCredito[] cartoesCredito = PedidoViewHelper.organizaCartoesCredito(cliente, req);
					DadosCalculoFrete dadosCalculoFrete = fachada.getDadosCalculoFrete( carrinho.getId() );

					dadosCalculoFrete.setTipoServico((campos[17].getValor()));
					dadosCalculoFrete.setCepDestino(endereco.getCep().replace("-", ""));
	      			
	      			double[] valorFrete = fachada.calculaValorFrete(dadosCalculoFrete);

					Pedido pedido = new Pedido((long)1,
							new Date(),
							cliente,
							1,
							endereco,
							valorFrete[0],
							cupomDesconto,
							cartoesCredito,
							null,
							carrinho,
							valorTotal,
							(int)valorFrete[1],
							campos[17].getValor()
					);

					CupomTroca[] cuponsTroca = PedidoViewHelper.createCuponsTrocaFromStrings(campos[16].getValor(), pedido);
					cuponsTroca = fachadaCupomTroca.encontraCuponsTroca(cuponsTroca);

					pedido.setCuponsTroca(cuponsTroca);

					if(valorFrete[0] > 0 && valorFrete[1] > 0 && fachada.validarValorCompra(pedido) && fachada.efetuaCompra(pedido, cartoesCredito, cuponsTroca, LoginViewHelper.getLogInfo(req, resp))) {

						resp.sendRedirect("/trabalho-les/meusPedidos");
					} else {

					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
