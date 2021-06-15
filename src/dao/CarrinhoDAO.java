package dao;

import utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Usuario;
import model.Carrinho;
import model.EntidadeDominio;
import model.ItemCarrinho;
import model.Livro;
import model.SolicitacaoTroca;
import utils.Campo;

public class CarrinhoDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public Usuario selectSingleVal;
	public Carrinho selectCarrinhoVal;

	public ArrayList select(Campo[] campos) {
		return null;
	}

	public Carrinho selectCarrinho(long idCliente) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from carrinhos where carrinhos.idUsuario = ? and carrinhos.status <= 1;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, idCliente);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				int idCarrinho = rs.getInt(1);

				ArrayList<ItemCarrinho> itensCarrinho = new ArrayList();

				StringBuilder sql2 = new StringBuilder();
				sql2.append("select carrinhos_produtos.idCarrinho, carrinhos_produtos.idCarrinhoProduto, carrinhos_produtos.quantidade, livros.id, livros.titulo, livros.preco, livros.capa from carrinhos_produtos inner join livros on carrinhos_produtos.idProduto = livros.id where carrinhos_produtos.idCarrinho = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, idCarrinho);

				ResultSet rs2 = pst.executeQuery();

				LivroDAO livrodao = new LivroDAO();

				while (rs2.next()) {
					Livro livro = new Livro(
						rs2.getLong("livros.id"),
						null,
						rs2.getString("livros.titulo"),
						null,
						null,
						null,
						"",
						"",
						0,
						"",
						0,
						0,
						0,
						rs2.getDouble("livros.preco"),
						"",
						1,
						rs2.getString("livros.capa"),
						null,
						""
					);
					livro.setEstoque(livrodao.contaEstoque(livro, rs2.getLong("carrinhos_produtos.idCarrinho")));
					ItemCarrinho ic = new ItemCarrinho(
						rs2.getLong("carrinhos_produtos.idCarrinhoProduto"),
						null,
						livro,
						rs2.getInt("carrinhos_produtos.quantidade"),
						null
					);

					itensCarrinho.add(ic);
				}

				Carrinho carrinho = new Carrinho((long)idCarrinho, rs.getDate(2), itensCarrinho, rs.getInt(4), null);
				this.selectCarrinhoVal = carrinho;
				return this.selectCarrinhoVal;
			}
			
			return null;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Carrinho getCarrinhoPorId(long id, boolean posCompra) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from carrinhos where carrinhos.id = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				ArrayList<ItemCarrinho> itensCarrinho = new ArrayList();

				StringBuilder sql2 = new StringBuilder();
				sql2.append("select carrinhos_produtos.precoMomentoCompra, " +
					"carrinhos_produtos.idCarrinhoProduto, carrinhos_produtos.quantidadeItensTrocados, "+
					"carrinhos_produtos.quantidade, livros.id, livros.titulo, livros.preco, livros.capa "+
					"from carrinhos_produtos inner join livros on carrinhos_produtos.idProduto = livros.id where carrinhos_produtos.idCarrinho = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setLong(1, id);

				ResultSet rs2 = pst.executeQuery();

				LivroDAO livrodao = new LivroDAO();

				while (rs2.next()) {
					double precoLivro;

					if (posCompra) {
						precoLivro = rs2.getDouble("carrinhos_produtos.precoMomentoCompra");
					} else {
						precoLivro = rs2.getDouble("livros.preco");
					}

					Livro livro = new Livro(
						rs2.getLong("livros.id"),
						null,
						rs2.getString("livros.titulo"),
						null,
						null,
						null,
						"",
						"",
						0,
						"",
						0,
						0,
						0,
						precoLivro,
						"",
						1,
						rs2.getString("livros.capa"),
						null,
						""
					);

					ItemCarrinho ic = new ItemCarrinho(
						rs2.getLong("carrinhos_produtos.idCarrinhoProduto"),
						null,
						livro,
						rs2.getInt("carrinhos_produtos.quantidade"),
						null
					);

					ic.setQuantidadeItensTrocados(rs2.getInt("carrinhos_produtos.quantidadeItensTrocados"));

					itensCarrinho.add(ic);
				}

				Carrinho carrinho = new Carrinho((long)id, rs.getDate(2), itensCarrinho, rs.getInt(4), null);
				this.selectCarrinhoVal = carrinho;
				return this.selectCarrinhoVal;
			}
			return null;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void adicionarCarrinho(ItemCarrinho itemCarrinho) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			//remove o bloqueio do carrinho inativado
			StringBuilder sqlDelb = new StringBuilder();
			sqlDelb.append("DELETE FROM bloqueios_produtos where idCarrinho = (select id from carrinhos WHERE status = 0 AND idUsuario = ? limit 1);");	

			pst = connection.prepareStatement(sqlDelb.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, itemCarrinho.getCliente().getId());

			pst.executeUpdate();

			//remove o carrinho inativado
			StringBuilder sqlDel = new StringBuilder();
			sqlDel.append("DELETE FROM carrinhos WHERE status = 0 AND idUsuario = ?;");	

			pst = connection.prepareStatement(sqlDel.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, itemCarrinho.getCliente().getId());

			pst.executeUpdate();			
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id FROM carrinhos WHERE carrinhos.idUsuario = ? AND carrinhos.status = 1 limit 1;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			Date agora = new Date(); 

			pst.setLong(1, itemCarrinho.getCliente().getId());
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				//adiciona o produto ao carrinho existente
				adicionaProdutoAoCarrinho(itemCarrinho, rs.getInt(1));
			} else {
				//cria um carrinho novo
				StringBuilder sql2 = new StringBuilder();
				sql2.append("INSERT INTO carrinhos(dataCadastro, idUsuario, status, dataAlteracao) VALUES (?, ?, ?, ?);");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDate(1, new java.sql.Date(agora.getTime()));
				pst.setLong(2, itemCarrinho.getCliente().getId());
				pst.setInt(3, 1);
				pst.setDate(4, new java.sql.Date(agora.getTime()));
				
				pst.executeUpdate();
				
				ResultSet rs2 = pst.getGeneratedKeys();
				if (rs2.next()) {
					//adiciona o produto ao carrinho novo
					adicionaProdutoAoCarrinho(itemCarrinho, rs2.getInt(1));
				}
			}
			
			connection.commit();			
		} catch (Exception e) {
			try {
				if(connection != null) connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void adicionaProdutoAoCarrinho(ItemCarrinho itemCarrinho, long id) {
		PreparedStatement pst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT idCarrinhoProduto, quantidade FROM carrinhos_produtos WHERE carrinhos_produtos.idProduto = ? AND carrinhos_produtos.idCarrinho = ? limit 1;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, itemCarrinho.getLivro().getId());
			pst.setLong(2, id);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				//pega a quantidade de estoque do livro a ser inserido
				LivroDAO livroDAO = new LivroDAO();
				int estoque = livroDAO.contaEstoque(itemCarrinho.getLivro(), id);
				//altera a quantidade de itens no carrinho
				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE carrinhos_produtos SET quantidade = ? WHERE carrinhos_produtos.idCarrinhoProduto = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setInt(1, Math.min( rs.getInt(2) + itemCarrinho.getQuantidade(), estoque ) );
				pst.setLong(2, rs.getInt(1));
				
				pst.executeUpdate();

				//altera o bloqueio do carrinho
				alteraBloqueio(id, Math.min( rs.getInt(2) + itemCarrinho.getQuantidade(), estoque ));

			} else {
				//adiciona o produto ao carrinho existente
				StringBuilder sql2 = new StringBuilder();
				sql2.append("INSERT INTO carrinhos_produtos(idCarrinho, idProduto, quantidade, quantidadeItensTrocados) VALUES (?, ?, ?, 0);");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setLong(1, id);
				pst.setLong(2, itemCarrinho.getLivro().getId());
				pst.setInt(3, itemCarrinho.getQuantidade());
				
				pst.executeUpdate();

				ResultSet rs2 =  pst.getGeneratedKeys();
				if(rs2.next()) {
					//insere o bloqueio do carrinho
					insereBloqueio(rs2.getInt(1), itemCarrinho.getQuantidade());
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void insereBloqueio(long idItemCarrinho, int quantidade) {
		PreparedStatement pst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM carrinhos_produtos WHERE idCarrinhoProduto = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, idItemCarrinho);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				StringBuilder sql2 = new StringBuilder();
				sql2.append("INSERT INTO bloqueios_produtos (dataEntrada, quantidade, idLivro, idCarrinho) VALUES (?, ?, ?, ?);");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setInt(2, quantidade);
				pst.setLong(3, rs.getLong("carrinhos_produtos.idProduto"));
				pst.setLong(4, rs.getLong("carrinhos_produtos.idCarrinho"));
				
				pst.executeUpdate();

				ResultSet rs2 =  pst.getGeneratedKeys();
				if(rs2.next()) {
					StringBuilder sql3 = new StringBuilder();
					sql3.append("UPDATE carrinhos SET dataAlteracao = ? where id = ?;");
					
					pst = connection.prepareStatement(sql3.toString(),
							Statement.RETURN_GENERATED_KEYS);

					pst.setDate(1, new java.sql.Date(new Date().getTime()));
					pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinho"));
					
					pst.executeUpdate();
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void alteraBloqueio(long idItemCarrinho, int quantidade) {
		PreparedStatement pst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM carrinhos_produtos WHERE idCarrinhoProduto = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, idItemCarrinho);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				StringBuilder sql2 = new StringBuilder();
				sql2.append("UPDATE bloqueios_produtos SET quantidade = ? WHERE idLivro = ? AND idCarrinho = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setInt(1, quantidade);
				pst.setLong(2, rs.getLong("carrinhos_produtos.idProduto"));
				pst.setLong(3, rs.getLong("carrinhos_produtos.idCarrinho"));
				
				pst.executeUpdate();

				StringBuilder sql3 = new StringBuilder();
				sql3.append("UPDATE carrinhos SET dataAlteracao = ? where id = ?;");
				
				pst = connection.prepareStatement(sql3.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinho"));
				
				pst.executeUpdate();
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void removeBloqueio(long idItemCarrinho) {
		PreparedStatement pst = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM carrinhos_produtos WHERE idCarrinhoProduto = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, idItemCarrinho);
			
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				StringBuilder sql2 = new StringBuilder();
				sql2.append("DELETE FROM bloqueios_produtos WHERE idLivro = ? AND idCarrinho = ?;");
				
				pst = connection.prepareStatement(sql2.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setLong(1, rs.getLong("carrinhos_produtos.idProduto"));
				pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinho"));

				pst.executeUpdate();

				StringBuilder sql3 = new StringBuilder();
				sql3.append("UPDATE carrinhos SET dataAlteracao = ? where id = ?;");
				
				pst = connection.prepareStatement(sql3.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setDate(1, new java.sql.Date(new Date().getTime()));
				pst.setLong(2, rs.getLong("carrinhos_produtos.idCarrinho"));
				
				pst.executeUpdate();
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void removeBloqueioCarrinhoInteiro(long idCarrinho) {
		PreparedStatement pst = null;		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM bloqueios_produtos WHERE idCarrinho = ?;");
			
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, idCarrinho);
			
			pst.executeUpdate();

			StringBuilder sql3 = new StringBuilder();
			sql3.append("UPDATE carrinhos SET dataAlteracao = ? where id = ?;");
			
			pst = connection.prepareStatement(sql3.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, new java.sql.Date(new Date().getTime()));
			pst.setLong(2, idCarrinho);
			
			pst.executeUpdate();
			connection.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void alteraQteItemCarrinho(long id, int quantidade) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			StringBuilder sqlEstoque = new StringBuilder();
			sqlEstoque.append("SELECT idProduto, idCarrinho from carrinhos_produtos WHERE idCarrinhoProduto = ?;");
			
			pst = connection.prepareStatement(sqlEstoque.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setLong(1, id);
			
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				LivroDAO livrodao = new LivroDAO();
				int estoque = livrodao.contaEstoque(new Livro(rs.getLong("carrinhos_produtos.idProduto"), new Date()), rs.getLong("carrinhos_produtos.idCarrinho"));

				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE carrinhos_produtos SET quantidade = ? WHERE idCarrinhoProduto = ?");
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, Math.min(estoque, quantidade));
				pst.setLong(2, id);
				pst.executeUpdate();

				//altera o bloqueio do produto
				alteraBloqueio(id, quantidade);
				connection.commit();
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao alterar o registro!");
			e.printStackTrace();
		}
	}

	public void removerItemCarrinho(long id) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			//remove o bloqueio do item
			removeBloqueio(id);

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM carrinhos_produtos WHERE idCarrinhoProduto = ?");
			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, id);
			pst.executeUpdate();

			
			connection.commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao excluir o registro!");
			e.printStackTrace();
		}
	}

	protected void devolveItensAoItemCarrinho(long id) {
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement("select * from solicitacoes_troca inner join carrinhos_produtos on carrinhos_produtos.idCarrinhoProduto = solicitacoes_troca.idItemCarrinho where solicitacoes_troca.id = ?;");
			
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
						
			if (rs.next()) {
				pst = connection.prepareStatement("update carrinhos_produtos set quantidade = ?, quantidadeItensTrocados = ? where idCarrinhoProduto = ?");

				pst.setInt(1, rs.getInt("carrinhos_produtos.quantidade") + rs.getInt("solicitacoes_troca.quantidade"));
				pst.setInt(2, rs.getInt("carrinhos_produtos.quantidadeItensTrocados") - rs.getInt("solicitacoes_troca.quantidade"));
				pst.setLong(3, rs.getLong("carrinhos_produtos.idCarrinhoProduto"));
				
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}


	public void insert(EntidadeDominio entidade) {
		
	}

	public void update(EntidadeDominio entidade) {
		
	}

	public void delete(long id) {
		
	}


}
