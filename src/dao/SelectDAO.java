package dao;

import utils.Conexao;
import strategies.CriaFiltragem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Bandeira;
import model.TipoCliente;
import model.TipoDocumento;
import model.TipoEndereco;
import model.TipoTelefone;
import model.TipoResidencia;
import model.TipoLogradouro;
import model.FuncaoEndereco;
import model.TipoUsuario;
import model.Genero;

public class SelectDAO {
	private Connection connection = null;
	public ArrayList selectVals;

	private final String[] tabelasNomes = {
		"generos",
		"tipos_clientes",
		"tipos_documentos",
		"tipos_enderecos",
		"tipos_residencias",
		"funcoes_enderecos",
		"tipos_logradouros",
		"bandeiras",
		"tipos_telefones",
		"tipos_usuarios"
	};

	public ArrayList getOpcoesSelect(int numero) {
		PreparedStatement pst = null;
		try {
			connection = Conexao.getConnectionMySQL();
			pst = connection.prepareStatement("select * from " + tabelasNomes[numero - 1] +" as a;");
			ResultSet rs = pst.executeQuery();			
			ArrayList list = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();

			switch (numero) {
			    case 1:
				    while (rs.next()) {
						Genero a = new Genero(rs.getLong("a.id"), rs.getDate("a.dataCadastro"),	rs.getString("a.nome"));
						list.add(a);
					}
				    break;
			    case 2:
				    while (rs.next()) {
						TipoCliente a = new TipoCliente(rs.getLong("a.id"), rs.getDate("a.dataCadastro"), rs.getString("a.nome"), rs.getString("a.descricao"));
						list.add(a);
					}
				    break;
				case 3:
				    while (rs.next()) {
						TipoDocumento a = new TipoDocumento(rs.getLong("a.id"), rs.getDate("a.dataCadastro"), rs.getString("a.nome"), rs.getString("a.descricao"));
						list.add(a);
					}
				    break;
				case 4:
				    while (rs.next()) {
						TipoEndereco a = new TipoEndereco(rs.getLong("a.id"), rs.getDate("a.dataCadastro"),	rs.getString("a.nome"), rs.getString("a.descricao"));
						list.add(a);
					}
				    break;
				case 5:
				    while (rs.next()) {
						TipoResidencia a = new TipoResidencia(rs.getLong("a.id"), rs.getDate("a.dataCadastro"),	rs.getString("a.nome"), rs.getString("a.descricao"));
						list.add(a);
					}
				    break;
				case 6:
				    while (rs.next()) {
						FuncaoEndereco a = new FuncaoEndereco(rs.getLong("a.id"), rs.getDate("a.dataCadastro"),	rs.getString("a.nome"), rs.getString("a.descricao"));
						list.add(a);
					}
				    break;
				case 7:
				    while (rs.next()) {
						TipoLogradouro a = new TipoLogradouro(rs.getLong("a.id"), rs.getDate("a.dataCadastro"),	rs.getString("a.nome"), rs.getString("a.descricao"));
						list.add(a);
					}
				    break;
				case 8:
				    while (rs.next()) {
						Bandeira a = new Bandeira(rs.getLong("a.id"), rs.getDate("a.dataCadastro"),	rs.getString("a.nome"));
						list.add(a);
					}
				    break;
				case 9:
				    while (rs.next()) {
						TipoTelefone a = new TipoTelefone(rs.getLong("a.id"), rs.getDate("a.dataCadastro"),	rs.getString("a.nome"), rs.getString("a.descricao"));
						list.add(a);
					}
				    break;
				case 10:
				    while (rs.next()) {
						TipoUsuario a = new TipoUsuario(rs.getLong("a.id"), rs.getDate("a.dataCadastro"),	rs.getString("a.nome"), rs.getString("a.descricao"));
						list.add(a);
					}
				    break;
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
}
