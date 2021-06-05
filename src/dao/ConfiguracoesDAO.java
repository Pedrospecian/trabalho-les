package dao;

import utils.Conexao;
import strategies.CriaFiltragemUsuario;
import strategies.CriaPaginacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Configuracao;
import model.EntidadeDominio;

import utils.Campo;

public class ConfiguracoesDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public int countVals;
	public Configuracao selectSingleVal;

	public ArrayList select(Campo[] campos) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select * from configuracoes;");
	
			ResultSet rs = pst.executeQuery();
			
			ArrayList<Configuracao> list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				Configuracao configuracao = new Configuracao(
					rs.getLong("configuracoes.id"),
					rs.getString("configuracoes.descricao"),
					rs.getString("configuracoes.valor"),
					rs.getDate("configuracoes.dataAlteracao"));

				list.add(configuracao);
			}			
			
			this.selectVals = list;
			
			pst.close();
			connection.close();
			
			return this.selectVals;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			

			return null;
		}
	}

	public void insert(EntidadeDominio entidade) {
		
	}

	public void update(EntidadeDominio entidade) {

	}

	public void updateConfiguracoes(Configuracao[] configuracoes) {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			java.sql.Date agora = new java.sql.Date(new Date().getTime());

			for (int i = 0; i < configuracoes.length; i++) {			
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE configuracoes SET valor = ?, dataAlteracao = ? WHERE id = ?;");
				
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setString(1, configuracoes[i].getValor());
				pst.setDate(2, agora );
				pst.setLong(3, (long) i + 1);
				
				pst.executeUpdate();
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

	public void resetarConfiguracoes() {
		PreparedStatement pst = null;
		String[] configuracoesPadrao = {"10", "10", "7", "10", "08780220"};
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			java.sql.Date agora = new java.sql.Date(new Date().getTime());

			for (int i = 0; i < configuracoesPadrao.length; i++) {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE configuracoes SET valor = ?, dataAlteracao = ? WHERE id = ?;");
				
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);

				pst.setString(1, configuracoesPadrao[i]);
				pst.setDate(2, agora );
				pst.setLong(3, (long) i + 1);
				
				pst.executeUpdate();
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

	public void delete(long id) {

	}

	public void implantarDominio() {
		PreparedStatement pst = null;
		
		try {
			connection = Conexao.getConnectionMySQL();
			connection.setAutoCommit(false);

			java.sql.Date agora = new java.sql.Date(new Date().getTime());
			
			//autores
			StringBuilder sql1 = new StringBuilder();
			sql1.append("INSERT IGNORE INTO autores (id, dataCadastro, nome, resumo) VALUES " +
			"(1, ?, 'Autor teste', 'teste'), " +
			"(2, ?, 'Agatha Christie', 'autora de livros de suspense');");

			pst = connection.prepareStatement(sql1.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );

			pst.executeUpdate();

			//bandeiras
			StringBuilder sql2 = new StringBuilder();
			sql2.append("INSERT IGNORE INTO bandeiras (id, dataCadastro, nome) VALUES " +
			"(1, ?, 'Visa'), " +
			"(2, ?, 'MasterCard');");
			
			pst = connection.prepareStatement(sql2.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );

			pst.executeUpdate();

			//cartoes_aprovados
			StringBuilder sql3 = new StringBuilder();
			sql3.append("INSERT IGNORE INTO cartoes_aprovados (id, nome, numero, dataExpiracao, cvv, idBandeira, limiteDisponivel) VALUES " +
			"(1, 'teste', '1111222233334444', '2022-11-11', '111', 2, 4000.0), " +
			"(2, 'cartao novo teste', '5206984449283106', '2022-08-26', '536', 2, 7140);");
			
			pst = connection.prepareStatement(sql3.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//categorias
			StringBuilder sql4 = new StringBuilder();
			sql4.append("INSERT IGNORE INTO categorias (id, dataCadastro, nome) VALUES " +
			"(1, ?, 'cat1'), " +
			"(2, ?, 'cat 2'), " +
			"(3, ?, 'categoria #3');");
			
			pst = connection.prepareStatement(sql4.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );

			pst.executeUpdate();

			//categorias_ativacao
			StringBuilder sql5 = new StringBuilder();
			sql5.append("INSERT IGNORE INTO categorias_ativacao (id, dataCadastro, nome) VALUES " +
			"(1, ?, 'Aumento de demanda'), " +
			"(2, ?, 'Lançamento de adaptação'), " +
			"(3, ?, 'Outro');");
			
			pst = connection.prepareStatement(sql5.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );

			pst.executeUpdate();

			//categorias_inativacao
			StringBuilder sql6 = new StringBuilder();
			sql6.append("INSERT IGNORE INTO categorias_inativacao (id, dataCadastro, nome) VALUES " +
			"(1, ?, 'Falta de demanda'), " +
			"(2, ?, 'Conteúdo ofensivo'), " +
			"(3, ?, 'Outro');");
			
			pst = connection.prepareStatement(sql6.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );

			pst.executeUpdate();

			//configuracoes
			StringBuilder sql7 = new StringBuilder();
			sql7.append("INSERT IGNORE INTO configuracoes (id, descricao, valor, dataAlteracao) VALUES "+
			"(1, 'numerosVendaInativacaoAutomatica', '10', '2021-05-29'), "+
			"(2, 'diasInativacaoAutomatica', '10', '2021-05-29'), "+
			"(3, 'diasPermanenciaCarrinho', '7', '2021-05-29'), "+
			"(4, 'diasPermanenciaBloqueioItemCarrinho', '10', '2021-05-29'), "+
			"(5, 'cepOrigem', '08780220', '2021-05-29');");
			pst = connection.prepareStatement(sql7.toString(),
					Statement.RETURN_GENERATED_KEYS);
			pst.executeUpdate();

			//editoras
			StringBuilder sql8 = new StringBuilder();
			sql8.append("INSERT IGNORE INTO editoras (id, dataCadastro, nome, descricao) VALUES " +
			"(1, ?, 'Editora teste', 'teste'), " +
			"(2, ?, 'Editora Ininap', 'Editora de HQs');");
			
			pst = connection.prepareStatement(sql8.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );

			pst.executeUpdate();

			//generos
			StringBuilder sql9 = new StringBuilder();
			sql9.append("INSERT IGNORE INTO generos (id, dataCadastro, nome) VALUES " +
			"(1, ?, 'Masculino'), " +
			"(2, ?, 'Feminino');");
			
			pst = connection.prepareStatement(sql9.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );

			pst.executeUpdate();

			//tipos_clientes
			StringBuilder sql10 = new StringBuilder();
			sql10.append("INSERT IGNORE INTO tipos_clientes (id, dataCadastro, nome, descricao) VALUES (1, ?, 'Comprador', 'Clientes convencionais.'), (2, ?, 'Revendedor', 'Clientes que compram para revender. Eles podem ter algum desconto.'), (3, ?, 'Parceiro', 'Clientes que possuem parceria com a loja. Eles podem ter algum desconto.');");
			
			pst = connection.prepareStatement(sql10.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );

			pst.executeUpdate();

			//tipos_documentos
			StringBuilder sql11 = new StringBuilder();
			sql11.append("INSERT IGNORE INTO tipos_documentos (id, dataCadastro, nome, descricao) VALUES (1, ?, 'CPF', 'Cadastro de Pessoa Física'), (2, ?, 'CNPJ', 'Cadastro Nacional de Pessoa Jurídica'), (3, ?, 'RG', 'Registro Geral'), (4, ?, 'SSN', 'Social Security Number');");
			
			pst = connection.prepareStatement(sql11.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );
			pst.setDate(4, agora );

			pst.executeUpdate();

			//tipos_enderecos
			StringBuilder sql12 = new StringBuilder();
			sql12.append("INSERT IGNORE INTO tipos_enderecos (id, dataCadastro, nome, descricao) VALUES (1, ?, 'Residencial', 'Casa, apartamento etc.'), (2, ?, 'Comercial', 'Empresa');");
			
			pst = connection.prepareStatement(sql12.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );

			pst.executeUpdate();

			//tipos_logradouros
			StringBuilder sql13 = new StringBuilder();
			sql13.append("INSERT IGNORE INTO tipos_logradouros (id, dataCadastro, nome, descricao) VALUES (1, ?, 'Rua', 'Rua'), (2, ?, 'Avenida', 'Avenida'), (3, ?, 'Viela', 'Viela'), (4, ?, 'Outro...', 'Outro tipo de logradouro');");
			
			pst = connection.prepareStatement(sql13.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );
			pst.setDate(4, agora );

			pst.executeUpdate();

			//tipos_residencias
			StringBuilder sql14 = new StringBuilder();
			sql14.append("INSERT IGNORE INTO tipos_residencias (id, dataCadastro, nome, descricao) VALUES (1, ?, 'Casa', 'Casa'), (2, ?, 'Apartamento', 'Apartamento'), (3, ?, 'Outro...', 'Outro tipo de endereço');");
			
			pst = connection.prepareStatement(sql14.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );

			pst.executeUpdate();

			//tipos_telefones
			StringBuilder sql15 = new StringBuilder();
			sql15.append("INSERT IGNORE INTO tipos_telefones (id, dataCadastro, nome, descricao) VALUES (1, ?, 'Residencial', 'Telefone residencial'), (2, ?, 'Celular', 'Telefone celular');");
			
			pst = connection.prepareStatement(sql15.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );

			pst.executeUpdate();

			//tipos_usuarios
			StringBuilder sql16 = new StringBuilder();
			sql16.append("INSERT IGNORE INTO tipos_usuarios (id, dataCadastro, nome, descricao) VALUES (1, ?, 'Funcionário', 'Tem acesso limitado às funções do admin'), (2, ?, 'Administrador', 'Tem acesso total às funções do admin'), (3, ?, 'Gerente de vendas', 'Pode alterar o valor dos produtos abaixo da margem de lucro');");
			
			pst = connection.prepareStatement(sql16.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );

			pst.executeUpdate();

			//funcoes_enderecos
			StringBuilder sql17 = new StringBuilder();
			sql17.append("INSERT IGNORE INTO funcoes_enderecos (id, dataCadastro, nome, descricao) VALUES (1, ?, 'Endereço de Cobrança', 'Endereço de Cobrança'), (2, ?, 'Endereço de Entrega', 'Endereço de Entrega'), (3, ?, 'Endereço de Cobrança e Entrega', 'Endereço de Cobrança e Entrega');");
			
			pst = connection.prepareStatement(sql17.toString(),
					Statement.RETURN_GENERATED_KEYS);

			pst.setDate(1, agora );
			pst.setDate(2, agora );
			pst.setDate(3, agora );

			pst.executeUpdate();
			
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
}
