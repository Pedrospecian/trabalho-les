let port = "8080";
let cpf = "63128164436"; //alterar o cpf a cada teste novo
let emailCliente = "a2xasaaaw3bcaaa@teaste.com.br" //alterar o email a cada teste novo
let emailAdmin = "ateaswaaaabcaaaadmin3@teste.com.br" //alterar o email a cada teste novo
let waitTime = 10;

describe('Teste de condução de CRUD de clientes', () => {
  it('Cadastra um cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')    
    cy.wait(waitTime);
    cy.get('[cypress-cadastroCliente]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroCliente')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime)

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-genero]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-tipoCliente]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-dataNascimento]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-senha]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-genero]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-tipoCliente').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-dataNascimento').type('1988-03-12').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-email]').clear().type(emailCliente)
    cy.wait(waitTime)
    cy.get('[cypress-senha]').type('12').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-senha]').type('e').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-senha]').type('Mud').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-senha]').type('!').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-senha]').type('A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.wait(waitTime)
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-senha]').clear().type('123Mudar!').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-senhaConfirmar]').clear().type('123Mudar!').parent().should('have.class', 'has-success')
    cy.wait(waitTime)

    cy.get('.js-tipo-documento').select('1')
    cy.wait(waitTime)
    cy.get('.js-numero-documento').type(cpf)
    cy.wait(waitTime)
    cy.get('.js-validade-documento').type('2022-03-31')
    cy.wait(waitTime)
    cy.get('.js-adicionar-documento').click()
    cy.wait(waitTime)
    cy.get('.js-boxes-documentos .box-single').should('be.visible')
    cy.wait(waitTime)

    cy.get('.js-nome-endereco').type('Endereço Teste')
    cy.wait(waitTime)
    cy.get('.js-tipo-endereco').select('1')
    cy.wait(waitTime)
    cy.get('.js-tipo-residencia').select('1')
    cy.wait(waitTime)
    cy.get('.js-funcao-endereco').select('1')
    cy.wait(waitTime)
    cy.get('.js-pais').select('1')
    cy.wait(waitTime)
    cy.get('.js-cep').type('12345678')
    cy.wait(waitTime)
    cy.get('.js-tipo-logradouro').select('1')
    cy.wait(waitTime)
    cy.get('.js-logradouro').type('Rua teste')
    cy.wait(waitTime)
    cy.get('.js-numero').type('12')
    cy.wait(waitTime)
    cy.get('.js-bairro').type('Vila Oliveira')
    cy.wait(waitTime)
    cy.get('.js-cidade').type('Mogi das Cruzes')
    cy.wait(waitTime)
    cy.get('.js-uf').type('SP')
    cy.wait(waitTime)
    cy.get('.js-adicionar-endereco').click()
    cy.wait(waitTime)
    cy.get('.js-boxes-enderecos .box-single').should('be.visible')
    cy.wait(waitTime)

    cy.get('.js-nome-endereco').type('Endereço Teste')
    cy.wait(waitTime)
    cy.get('.js-tipo-endereco').select('1')
    cy.wait(waitTime)
    cy.get('.js-tipo-residencia').select('1')
    cy.wait(waitTime)
    cy.get('.js-funcao-endereco').select('2')
    cy.wait(waitTime)
    cy.get('.js-pais').select('1')
    cy.wait(waitTime)
    cy.get('.js-cep').type('08780220')
    cy.wait(waitTime)
    cy.get('.js-tipo-logradouro').select('1')
    cy.wait(waitTime)
    cy.get('.js-logradouro').type('Rua teste')
    cy.wait(waitTime)
    cy.get('.js-numero').type('12')
    cy.wait(waitTime)
    cy.get('.js-bairro').type('Vila Oliveira')
    cy.wait(waitTime)
    cy.get('.js-cidade').type('Mogi das Cruzes')
    cy.wait(waitTime)
    cy.get('.js-uf').type('SP')
    cy.wait(waitTime)
    cy.get('.js-adicionar-endereco').click()
    cy.wait(waitTime)
    cy.get('.js-boxes-enderecos .box-single').should('be.visible')
    cy.wait(waitTime)

    cy.get('.js-numero-cartao').type('1111222233334444')
    cy.wait(waitTime)
    cy.get('.js-nome-cartao').type('Teste')
    cy.wait(waitTime)
    cy.get('.js-bandeira-cartao').select('1')
    cy.wait(waitTime)
    cy.get('.js-cvv-cartao').type('123')
    cy.wait(waitTime)
    cy.get('.js-validade-cartao').type('2030-12-31')
    cy.wait(waitTime)
    cy.get('.js-adicionar-cartao').click()
    cy.wait(waitTime)
    cy.get('.js-boxes-cartoes .box-single').should('be.visible')
    cy.wait(waitTime)
    cy.get('input[name="cartaoPreferencial"][value="1111222233334444"]').click()
    cy.wait(waitTime)

    cy.get('.js-tipo-telefone').select('1')
    cy.wait(waitTime)
    cy.get('.js-ddd-telefone').type('11')
    cy.wait(waitTime)
    cy.get('.js-numero-telefone').type('56125678')
    cy.wait(waitTime)
    cy.get('.js-adicionar-telefone').click()
    cy.wait(waitTime)
    cy.get('.js-boxes-telefones .box-single').should('be.visible')
    cy.wait(waitTime)

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime)

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
    cy.wait(waitTime);
  });

  it('Lista os clientes', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')  
    cy.wait(waitTime);
    cy.get('[cypress-listagemClientes]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
    cy.wait(waitTime);
  });

  it('Altera a senha de um cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemClientes]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
    cy.wait(waitTime);

    cy.get('table tr:last-child [cypress-alterarSenha]').first().click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/alterarSenha')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.get('[cypress-senhaAtual]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-senhaAtual]').clear().type('123Mudar!').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('12').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('e').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('Mud').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('!').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
    cy.wait(waitTime);
  });

  it('Inativa um cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemClientes]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
    cy.wait(waitTime);
    cy.get('table tr:first-child [cypress-alterarStatusCliente]')
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
    cy.wait(waitTime);
  });
});

describe('Teste de condução de CRUD de usuários de admin', () => {
  it('Cadastra um usuário de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-cadastroUsuarioAdmin]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroUsuarioAdmin')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-campoSenha]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-email]').clear().type(emailAdmin).parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-tipoUsuario]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-campoSenha]').type('12').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-campoSenha]').type('e').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-campoSenha]').type('Mud').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-campoSenha]').type('!').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-campoSenha]').type('A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.wait(waitTime);
  });

  it('Lista os usuários de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemUsuariosAdmin]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.wait(waitTime);
  });

  it('Edita os dados de um usuário de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemUsuariosAdmin]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.wait(waitTime);


    cy.get('.tabela-pagina-single.active tr:first-child [cypress-editarUsuarioAdmin]').first().click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarUsuarioAdmin')
    cy.wait(waitTime);

    cy.get('[cypress-nome]').clear()
    cy.wait(waitTime);
    cy.get('[cypress-email]').clear()
    cy.wait(waitTime);
    cy.get('[cypress-status]').select('')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-email]').clear().type(emailAdmin).parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.wait(waitTime);
  });

  it('Altera a senha de um usuário de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemUsuariosAdmin]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.wait(waitTime);

    cy.get('.tabela-pagina-single.active tr:first-child [cypress-alterarSenhaUsuarioAdmin]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/alterarSenhaUsuarioAdmin')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.get('[cypress-senhaAtual]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-senhaAtual]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('12').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('e').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('Mud').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('!').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaNova]').type('A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.wait(waitTime);
  });

  it('Inativa um usuário de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemUsuariosAdmin]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.wait(waitTime);
    cy.get('.tabela-pagina-single.active tr:first-child [cypress-alterarStatusUsuarioAdmin]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.wait(waitTime);
  });
});

describe('Teste de condução de CRUD de livros', () => {
  it('Cadastra um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-cadastroLivro]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroLivro')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.get('[cypress-titulo]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-autor]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-editora]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-ano]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-isbn]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-numeroPaginas]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-sinopse]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-altura]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-largura]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-peso]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-profundidade]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-preco]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-codigoBarras]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-grupoPrecificacao]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-titulo]').type('Teste').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-autor]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-editora]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-ano]').type('2012').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-isbn]').type('235461').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-numeroPaginas]').type('100').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-sinopse]').type('Lorem ipsum dolor sit amet').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-altura]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-altura]').clear().type('1.').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-altura]').clear().type('1.1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-largura]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-largura]').clear().type('1.').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-largura]').clear().type('1.1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-peso]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-peso]').clear().type('1.').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-peso]').clear().type('1.1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-profundidade]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-profundidade]').clear().type('1.').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-profundidade]').clear().type('1.1').parent().should('have.class', 'has-success')  
    cy.wait(waitTime);  
    cy.get('[cypress-preco]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-preco]').clear().type('1.').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-preco]').clear().type('1.1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-codigoBarras]').type('1234567899').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-grupoPrecificacao]').select('2').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-edicao]').type('123').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('.js-id-categoria').select('1')
    cy.wait(waitTime)
    cy.get('.js-adicionar-categoria').click()
    cy.wait(waitTime)

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
    cy.wait(waitTime);
  });

  it('Lista os livros', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemLivros]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
    cy.wait(waitTime);
  });

  it('Edita um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemLivros]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
    cy.wait(waitTime);

    cy.get('.tabela-pagina-single.active tr:first-child [cypress-editarLivro]').first().click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarLivro')
    cy.wait(waitTime);

    cy.get('input:not([type="hidden"]):not([type="file"])').clear()
    cy.wait(waitTime);
    cy.get('textarea').clear()
    cy.wait(waitTime);
    cy.get('[cypress-editora]').select('')
    cy.wait(waitTime);
    cy.get('[cypress-autor]').select('')
    cy.wait(waitTime);
    cy.get('[cypress-grupoPrecificacao]').select('')

    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.get('[cypress-titulo]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-autor]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-editora]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-ano]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-isbn]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-numeroPaginas]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-sinopse]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-altura]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-largura]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-peso]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-profundidade]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-preco]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-preco]').clear().type('1.1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-codigoBarras]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-grupoPrecificacao]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-titulo]').type('Teste').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-autor]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-editora]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-ano]').type('2012').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-isbn]').type('235461').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-numeroPaginas]').type('100').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-sinopse]').type('Lorem ipsum dolor sit amet').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-edicao]').type('1e1').parent().should('have.class', 'has-success')
    cy.wait(waitTime)
    cy.get('[cypress-altura]').clear().type('2').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-largura]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-largura]').clear().type('1.').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-largura]').clear().type('1.1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-peso]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-peso]').clear().type('1.').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-peso]').clear().type('1.1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-profundidade]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-profundidade]').clear().type('1.').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-profundidade]').clear().type('1.1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-codigoBarras]').type('1234567899').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-grupoPrecificacao]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
    cy.wait(waitTime);
  });

  it('Faz um pedido de inativação de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemLivros]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
    cy.wait(waitTime);

    cy.get('.tabela-pagina-single.active tr:first-child [cypress-justificarInativacaoLivro]').first().click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/justificarInativacaoLivro')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.get('[cypress-categoriaInativacao]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-justificativa]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-categoriaInativacao]').select('1').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-justificativa]').type('Lorem ipsum').parent().should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
    cy.wait(waitTime);

  });

  it('Lista os livros com pedidos de inativação', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')   
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("saoraphael@globo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-livrosPendentesInativacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
    cy.wait(waitTime);
  });

  it('Aceita o pedido de inativação de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')   
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("saoraphael@globo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')    
    cy.wait(waitTime);    
    cy.get('[cypress-livrosPendentesInativacao]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
    cy.wait(waitTime);
    cy.get('.tabela-pagina-single.active tr:first-child [cypress-aceitarInativacao]').click().wait(100)
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
    cy.wait(waitTime);
  });

  it('Recusa o pedido de inativação de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("saoraphael@globo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-livrosPendentesInativacao]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
    cy.wait(waitTime);
    cy.get('.tabela-pagina-single.active tr:first-child [cypress-recusarInativacao]').click().wait(100) 
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
    cy.wait(waitTime);
  });

  it('Lista as alterações no estoque de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemLivros]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
    cy.wait(waitTime);

    cy.get('.tabela-pagina-single.active tr:first-child [cypress-listagemEstoque]').first().click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemEstoque')
    cy.wait(waitTime);
  });

  it('Insere uma entrada de estoque de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-listagemLivros]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
    cy.wait(waitTime);

    cy.get('.tabela-pagina-single.active tr:first-child [cypress-listagemEstoque]').first().click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemEstoque')
    cy.wait(waitTime);

    cy.get('[cypress-cadastroEstoque]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroEstoque')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-custo]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-fornecedor]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-dataEntrada]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-quantidade]').type('1e1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-quantidade]').clear().type('11.1').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-quantidade]').clear().type('11').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-custo]').type('1e11').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-custo]').clear().type('11').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-custo]').type('.11').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-fornecedor]').select('2').parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.get('[cypress-dataEntrada]').type('2021-02-03').parent().should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemEstoque')
    cy.wait(waitTime);

  });
});

describe('Teste de condução de configurações', () => {
  it('Cria um usuário novo', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin') 
    cy.wait(waitTime);
    cy.get('[cypress-configuracoes]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/configuracoes')
    cy.wait(waitTime);

    cy.get('[cypress-numerosVendaInativacaoAutomatica]')
      .type('1e1')
      .parent()
      .should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-diasInativacaoAutomatica]')
      .type('1e1')
      .parent()
      .should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-diasPermanenciaCarrinho]')
      .type('1e1')
      .parent()
      .should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-diasPermanenciaBloqueioItemCarrinho]')
      .type('1e1')
      .parent()
      .should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/configuracoes')
    cy.wait(waitTime);

    cy.get('[cypress-numerosVendaInativacaoAutomatica]').clear()
      .type('1')
      .parent()
      .should('have.class', 'has-success')
    cy.wait(waitTime);     

    cy.get('[cypress-diasInativacaoAutomatica]').clear()
      .type('1')
      .parent()
      .should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-diasPermanenciaCarrinho]').clear()
      .type('1')
      .parent()
      .should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-diasPermanenciaBloqueioItemCarrinho]').clear()
      .type('1')
      .parent()
      .should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/configuracoes')
    cy.wait(waitTime);
    cy.get('[cypress-restaurarPadrao]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/configuracoes')
    cy.wait(waitTime);
  })
});

describe('Teste de implantação de tabelas de domínio', () => {
  it('Clica no link de implantação de tabelas de domínio', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@adminnovo.com")    
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")    
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()    
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.wait(waitTime);
    cy.get('[cypress-implantarDominio]').click()
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.wait(waitTime);
  });
});