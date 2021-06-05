package facades;

import dao.ConfiguracoesDAO;
import model.Configuracao;

import java.util.ArrayList;
import java.util.Date;

import strategies.ValidarCampos;
import strategies.CriptografarSenha;
import utils.Campo;
import utils.ResultadosBusca;
import utils.Log;

public class FachadaConfiguracoes implements IFachada<Configuracao, Campo[]> {
	public boolean validarCampos(Campo[] campos) {
		ValidarCampos validarCampos = new ValidarCampos();

		boolean camposValidos = true;

		for(int i = 0; i < campos.length; i++) {
			if (validarCampos.processa(campos[i]) == false) {
				camposValidos = false;
			}
		}
		return camposValidos;
	}

	public ResultadosBusca select(Campo[] campos) {
		ConfiguracoesDAO dao = new ConfiguracoesDAO();

		dao.select(campos);
		ArrayList<Configuracao> arrl = dao.selectVals;
		ResultadosBusca rb = new ResultadosBusca(arrl);

		return rb;
	}

	public String insert(Configuracao configuracao, String usuarioResponsavel) {
		return "";
	}

	public String delete(Configuracao configuracao, String usuarioResponsavel) {
		return "";
	}

	public String update(Configuracao configuracao, String usuarioResponsavel) {
		return "";
	}

	public String updateConfiguracoes(Configuracao[] configuracoes, String usuarioResponsavel) {
		ConfiguracoesDAO dao = new ConfiguracoesDAO();
		dao.updateConfiguracoes(configuracoes);

		String configuracoesDados = "";

		for (int i = 0; i < configuracoes.length; i++) {
			configuracoesDados += configuracoes[i].getDescricao() + ": " + configuracoes[i].getValor() + ", ";
		}

		configuracoesDados = configuracoesDados.substring(0, configuracoesDados.length() - 2);

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Configuracoes {"+ configuracoesDados +"}",
							 "Alteração");
		log.registrar();

		return "Configurações alteradas com sucesso!";
	}

	public void resetarConfiguracoes(String usuarioResponsavel) {
		ConfiguracoesDAO dao = new ConfiguracoesDAO();
		dao.resetarConfiguracoes();

		String configuracoesDados = "numerosVendaInativacaoAutomatica: 10, diasInativacaoAutomatica: 10, diasPermanenciaCarrinho: 7, diasPermanenciaBloqueioItemCarrinho: 10, cepOrigem: 08780220";

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Configuracoes {"+ configuracoesDados +"}",
							 "Alteração");
		log.registrar();
	}

	public void implantarDominio(String usuarioResponsavel) {
		ConfiguracoesDAO dao = new ConfiguracoesDAO();
		dao.implantarDominio();

		Date agora = new Date();

		String autoresStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Autor teste', descricao: 'teste'}, {id: 2, dataCadastro: " + agora + " , nome: 'Agatha Christie', descricao: 'autora de livros de suspense'}]";
		String bandeirasStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Visa'}, {id: 2, dataCadastro: " + agora + " , nome: 'MasterCard'}]";
		String cartoesAprovadosStr = "[{id: 1, nome: 'teste', numero: '1111222233334444', dataExpiracao: '2022-11-11', cvv: '111', idBandeira: 2, limiteDisponivel: 4.000}, {id: 2, nome: 'cartao novo teste', numero: '5206984449283106', dataExpiracao: '2022-08-26', cvv: '536', idBandeira: 2, limiteDisponivel: 7.140}]";
		String categoriasStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'cat1'}, {id: 2, dataCadastro: " + agora + " , nome: 'cat 2'}, {id: 3, dataCadastro: " + agora + " , nome: 'categoria #3'}]";
		String categoriasAtivacaoStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Aumento de demanda'}, {id: 2, dataCadastro: " + agora + " , nome: 'Lançamento de adaptação'}, {id: 3, dataCadastro: " + agora + " , nome: 'Outro'}]";
		String categoriasInativacaoStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Falta de demanda'}, {id: 2, dataCadastro: " + agora + " , nome: 'Conteúdo ofensivo'}, {id: 3, dataCadastro: " + agora + " , nome: 'Outro'}]";
		String configuracoesStr = "[{id: 1, descricao: 'numerosVendaInativacaoAutomatica', valor: '10', dataAlteracao: '2021-05-29'}, {id: 2, descricao: 'diasInativacaoAutomatica', valor: '10', dataAlteracao: '2021-05-29'}, {id: 3, descricao: 'diasPermanenciaCarrinho', valor: '7', dataAlteracao: '2021-05-29'}, {id: 4, descricao: 'diasPermanenciaBloqueioItemCarrinho', valor: '10', dataAlteracao: '2021-05-29'}, {id: 5, descricao: 'cepOrigem', valor: '08780220', dataAlteracao: '2021-05-29'}]";
		String editorasStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Editora teste', descricao: 'teste'}, {id: 2, dataCadastro: " + agora + " , nome: 'Editora Ininap', descricao: 'Editora de HQs'}]";
		String generosStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Masculino'}, {id: 2, dataCadastro: " + agora + " , nome: 'Feminino'}]";
		String tiposClientesStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Comprador', descricao: 'Clientes convencionais.'}, {id: 2, dataCadastro: " + agora + " , nome: 'Revendedor', descricao: 'Clientes que compram para revender. Eles podem ter algum desconto.'}, {id: 3, dataCadastro: " + agora + " , nome: 'Parceiro', descricao: 'Clientes que possuem parceria com a loja. Eles podem ter algum desconto.'}]";
		String tiposDocumentosStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'CPF', descricao: 'Cadastro de Pessoa Física'}, {id: 2, dataCadastro: " + agora + " , nome: 'CNPJ', descricao: 'Cadastro Nacional de Pessoa Jurídica'}, {id: 3, dataCadastro: " + agora + " , nome: 'RG', descricao: 'Registro Geral'}, {id: 4, dataCadastro: " + agora + " , nome: 'SSN', descricao: 'Social Security Number'}]";
		String tiposEnderecosStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Residencial', descricao: 'Casa, apartamento etc.'}, {id: 2, dataCadastro: " + agora + " , nome: 'Comercial', descricao: 'Empresa'}]";
		String tiposLogradourosStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Rua', descricao: 'Rua'}, {id: 2, dataCadastro: " + agora + " , nome: 'Avenida', descricao: 'Avenida'}, {id: 3, dataCadastro: " + agora + " , nome: 'Viela', descricao: 'Viela'}, {id: 4, dataCadastro: " + agora + " , nome: 'Outro...', descricao: 'Outro tipo de logradouro'}]";
		String tiposResidenciasStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Casa', descricao: 'Casa'}, {id: 2, dataCadastro: " + agora + " , nome: 'Apartamento', descricao: 'Apartamento'}, {id: 3, dataCadastro: " + agora + " , nome: 'Outro...', descricao: 'Outro tipo de endereço'}]";
		String tiposTelefonesStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Residencial', descricao: 'Telefone residencial'}, {id: 2, dataCadastro: " + agora + " , nome: 'Celular', descricao: 'Telefone celular'}]";
		String tiposUsuariosStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Funcionário', descricao: 'Tem acesso limitado às funções do admin'}, {id: 2, dataCadastro: " + agora + " , nome: 'Administrador', descricao: 'Tem acesso total às funções do admin'}, {id: 3, dataCadastro: " + agora + " , nome: 'Gerente de vendas', descricao: 'Pode alterar o valor dos produtos abaixo da margem de lucro'}]";
		String funcoesEnderecosStr = "[{id: 1, dataCadastro: " + agora + " , nome: 'Endereço de Cobrança', descricao: 'Endereço de Cobrança'}, {id: 2, dataCadastro: " + agora + " , nome: 'Endereço de Entrega', descricao: 'Endereço de Entrega'}, {id: 3, dataCadastro: " + agora + " , nome: 'Endereço de Cobrança e Entrega', descricao: 'Endereço de Cobrança e Entrega'}]";

		Log log = new Log(usuarioResponsavel + " (admin)",
							 "Autores {"+ autoresStr +"}, " + 
							 "Bandeiras {"+ bandeirasStr +"}, " + 
							 "CartoesAprovados {"+ cartoesAprovadosStr +"}, " + 
							 "Categorias {"+ categoriasStr +"}, " + 
							 "CategoriasAtivacao {"+ categoriasAtivacaoStr +"}, " + 
							 "CategoriasInativacao {"+ categoriasInativacaoStr +"}, " + 
							 "Configuracoes {"+ configuracoesStr +"}, " + 
							 "Editoras {"+ editorasStr +"}, " + 
							 "Generos {"+ generosStr +"}, " + 
							 "tiposClientes {"+ tiposClientesStr +"}, " + 
							 "tiposDocumentos {"+ tiposDocumentosStr +"}, " + 
							 "tiposEnderecos {"+ tiposEnderecosStr +"}, " + 
							 "tiposLogradouros {"+ tiposLogradourosStr +"}, " + 
							 "tiposResidencias {"+ tiposResidenciasStr +"}, " + 
							 "tiposTelefones {"+ tiposTelefonesStr +"}, " + 
							 "tiposUsuarios {"+ tiposUsuariosStr +"}, " + 
							 "funcoesEnderecos {"+ funcoesEnderecosStr +"}",
							 "Implantação de tabelas de domínio");
		log.registrar();
	}

}
