package dao;

import utils.Conexao;
import strategies.CriaFiltragemUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.Usuario;
import model.EntidadeDominio;

import utils.Campo;

public class CarrinhoDAO implements IDAO<EntidadeDominio, Campo[]> {
	private Connection connection = null;
	public ArrayList selectVals;
	public Usuario selectSingleVal;

	public ArrayList select(Campo[] campos) {
		return null;
	}

	public Usuario selectSingle(long id) {
		return null;
	}

	public void insert(EntidadeDominio entidade) {
		
	}

	public void update(EntidadeDominio entidade) {
		
	}

	public void updateStatus(EntidadeDominio entidade) {
		
	}

	public void adicionarAoCarrinho() {
		
	}

	public void delete(long id) {
		
	}
}
