let port = "8080";

describe('Teste de condução de CRUD de clientes', () => {
  it('Cadastra um cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-cadastroCliente]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroCliente')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-sexo]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoCliente]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataNascimento]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-sexo').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoCliente').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-status').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataNascimento').type('1988-03-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-senha]').type('12').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').type('e').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').type('Mud').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').type('!').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').type('A!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click().wait(500)

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroCliente')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-sexo').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoCliente').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-status').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataNascimento').type('1988-03-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste@teste.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-senha]').type('123Muda!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaConfirmar]').clear().type('123Muda!').parent().should('have.class', 'has-success')

    cy.get('.js-tipo-documento').select('1')
    cy.get('.js-numero-documento').type('83993121503')
    cy.get('.js-validade-documento').type('2022-03-31')
    cy.get('.js-adicionar-documento').click()
    cy.get('.js-boxes-documentos .box-single').should('be.visible')

    cy.get('.js-nome-endereco').type('Endereço Teste')
    cy.get('.js-tipo-endereco').select('1')
    cy.get('.js-tipo-residencia').select('1')
    cy.get('.js-funcao-endereco').select('1')
    cy.get('.js-pais').select('1')
    cy.get('.js-cep').type('12345678')
    cy.get('.js-tipo-logradouro').select('1')
    cy.get('.js-logradouro').type('Rua teste')
    cy.get('.js-numero').type('12')
    cy.get('.js-bairro').type('Vila Oliveira')
    cy.get('.js-cidade').type('Mogi das Cruzes')
    cy.get('.js-uf').type('SP')
    cy.get('.js-adicionar-endereco').click()
    cy.get('.js-boxes-enderecos .box-single').should('be.visible')

    cy.get('.js-numero-cartao').type('1111222233334444')
    cy.get('.js-nome-cartao').type('Teste')
    cy.get('.js-bandeira-cartao').select('1')
    cy.get('.js-cvv-cartao').type('123')
    cy.get('.js-validade-cartao').type('2030-12-31')
    cy.get('.js-adicionar-cartao').click()
    cy.get('.js-boxes-cartoes .box-single').should('be.visible')

    cy.get('.js-tipo-telefone').select('1')
    cy.get('.js-ddd-telefone').type('11')
    cy.get('.js-numero-telefone').type('912345678')
    cy.get('.js-adicionar-telefone').click()
    cy.get('.js-boxes-telefones .box-single').should('be.visible')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
  });

  it('Lista os clientes', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemClientes]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
  });

  it('Edita os dados de um cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemClientes]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')

    cy.get('tr:first-child [cypress-editarCliente]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarCliente')

    cy.get('[cypress-nome]').clear()
    cy.get('[cypress-email]').clear()
    cy.get('[cypress-sexo]').select('')
    cy.get('[cypress-tipoCliente]').select('')
    cy.get('[cypress-dataNascimento]').clear()
    cy.get('[cypress-status]').select('')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-sexo]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoCliente]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataNascimento]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-sexo').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoCliente').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-status').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataNascimento').type('1988-03-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click().wait(500)

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')

    cy.get('tr:first-child [cypress-editarCliente]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarCliente')

    cy.get('.js-tipo-documento').select('2')
    cy.get('.js-numero-documento').type('43210987654321')
    cy.get('.js-validade-documento').type('2022-03-31')
    cy.get('.js-adicionar-documento').click()
    cy.get('.js-boxes-documentos .box-single').should('be.visible')

    cy.get('.js-nome-endereco').type('Endereço Teste')
    cy.get('.js-tipo-endereco').select('1')
    cy.get('.js-tipo-residencia').select('1')
    cy.get('.js-funcao-endereco').select('1')
    cy.get('.js-pais').select('1')
    cy.get('.js-cep').type('12345678')
    cy.get('.js-tipo-logradouro').select('1')
    cy.get('.js-logradouro').type('Rua teste')
    cy.get('.js-numero').type('12')
    cy.get('.js-bairro').type('Vila Oliveira')
    cy.get('.js-cidade').type('Mogi das Cruzes')
    cy.get('.js-uf').type('SP')
    cy.get('.js-adicionar-endereco').click()
    cy.get('.js-boxes-enderecos .box-single').should('be.visible')

    cy.get('.js-numero-cartao').type('1111222233334444')
    cy.get('.js-nome-cartao').type('Teste')
    cy.get('.js-bandeira-cartao').select('1')
    cy.get('.js-cvv-cartao').type('123')
    cy.get('.js-validade-cartao').type('2030-12-31')
    cy.get('.js-adicionar-cartao').click()
    cy.get('.js-boxes-cartoes .box-single').should('be.visible')

    cy.get('.js-tipo-telefone').select('1')
    cy.get('.js-ddd-telefone').type('11')
    cy.get('.js-numero-telefone').type('912345678')
    cy.get('.js-adicionar-telefone').click()
    cy.get('.js-boxes-telefones .box-single').should('be.visible')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')

    cy.get('tr:first-child [cypress-editarCliente]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarCliente')

    cy.get('.js-boxes-enderecos .box-single:first-of-type .js-remove-existing-endereco').click()
    cy.get('.js-boxes-cartoes .box-single:first-of-type .js-remove-existing-cartao').click()
    cy.get('.js-boxes-telefones .box-single:first-of-type .js-remove-existing-telefone').click()

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
  });

  it('Altera a senha de um cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemClientes]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')

    cy.get('table tr:last-child [cypress-alterarSenha]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/alterarSenha')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-senhaAtual]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-senhaAtual]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaNova]').type('12').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').type('e').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').type('Mud').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').type('!').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').type('A!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
  });

  it('Inativa um cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemClientes]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
    cy.get('table tr:first-child [cypress-alterarStatusCliente]')
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')
  });

  it('Lista os cupons de troca de um cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemClientes]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')

    cy.get('table tr:last-child [cypress-listagemCuponsTroca]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemCuponsTroca')
  });
});

describe('Teste de condução de CRUD de usuários de admin', () => {
  it('Cadastra um usuário de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-cadastroUsuarioAdmin]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroUsuarioAdmin')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-campoSenha]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-campoSenha]').type('12').parent().should('have.class', 'has-danger')
    cy.get('[cypress-campoSenha]').type('e').parent().should('have.class', 'has-danger')
    cy.get('[cypress-campoSenha]').type('Mud').parent().should('have.class', 'has-danger')
    cy.get('[cypress-campoSenha]').type('!').parent().should('have.class', 'has-danger')
    cy.get('[cypress-campoSenha]').type('A!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
  });

  it('Lista os usuários de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemUsuariosAdmin]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
  });

  it('Edita os dados de um usuário de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemUsuariosAdmin]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')


    cy.get('table tr:first-child [cypress-editarUsuarioAdmin]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarUsuarioAdmin')

    cy.get('[cypress-nome]').clear()
    cy.get('[cypress-email]').clear()
    cy.get('[cypress-status]').select('')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
  });

  it('Altera a senha de um usuário de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemUsuariosAdmin]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')

    cy.get('table tr:last-child [cypress-alterarSenhaUsuarioAdmin]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/alterarSenhaUsuarioAdmin')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-senhaAtual]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-senhaAtual]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaNova]').type('12').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').type('e').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').type('Mud').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').type('!').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaNova]').type('A!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
  });

  it('Inativa um usuário de admin', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemUsuariosAdmin]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
    cy.get('table tr:first-child [cypress-alterarStatusUsuarioAdmin]')
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemUsuariosAdmin')
  });
});

describe('Teste de condução de gerenciamento de pedidos', () => {
  it('Lista todos os pedidos feitos', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-todosPedidos]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
  });

  it('Lista todos os pedidos de um cliente em específico', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemClientes]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemClientes')


    cy.get('[cypress-clienteSingle]:first-child [cypress-listagemPedidosAdmin]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
  });

  it('Visualiza os detalhes de um pedido', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-todosPedidos]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
    cy.get('[cypress-detalhesPedido]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido')
  });

  it('Altera o status de um pedido', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-todosPedidos]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
    cy.get('[cypress-despacho]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
    cy.get('[cypress-entregaEfetuada]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
  });

  it('Autoriza uma troca', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-todosPedidos]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
    cy.get('[cypress-autorizarTroca]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
  });

  it('Recusa uma troca', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-todosPedidos]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
    cy.get('[cypress-recusarTroca]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
  });

  it('Confirma o recebimento de uma troca', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-todosPedidos]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
    cy.get('[cypress-confirmarRecebimento]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
    cy.get('[cypress-retornarEstoque]').click()
    cy.get('[cypress-confirmarRecebimento]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos')
  });
});

describe('Teste de condução de CRUD de cupom de desconto', () => {
  it('Cadastra um cupom de desconto', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-cadastroCupom]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroCupom')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-valor]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataInicio]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataFim]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-valor]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-valor]').clear().type('10').parent().should('have.class', 'has-success')
    cy.get('[cypress-valor]').type('.50').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataInicio]').type('2020-01-20').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataFim]').type('2020-01-25').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemCupons')
  });

  it('Lista os cupons de desconto', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemCupons]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemCupons')
  });

  it('Edita um cupom de desconto', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemCupons]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemCupons')

    cy.get('[cypress-editarCupom]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarCupom')

    cy.get('input:not([type="hidden"])').clear()
    cy.get('[cypress-status]').select('')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-valor]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataInicio]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataFim]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-valor]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-valor]').clear().type('10').parent().should('have.class', 'has-success')
    cy.get('[cypress-valor]').type('.50').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataInicio]').type('2020-01-20').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataFim]').type('2020-01-25').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemCupons')
  });

  it('Altera o status de um cupom de desconto', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemCupons]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemCupons')

    cy.get('[cypress-alterarStatusCupom]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemCupons')
  });
});

describe('Teste de condução de CRUD de livros', () => {
  it('Cadastra um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-cadastroLivro]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroLivro')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-titulo]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-autor]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-editora]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-ano]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-isbn]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-numeroPaginas]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-sinopse]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-altura]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-largura]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-peso]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-profundidade]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-preco]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-codigoBarras]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-grupoPrecificacao]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-titulo]').type('Teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-autor]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-editora]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-ano]').type('2012').parent().should('have.class', 'has-success')
    cy.get('[cypress-isbn]').type('235461').parent().should('have.class', 'has-success')
    cy.get('[cypress-numeroPaginas]').type('100').parent().should('have.class', 'has-success')
    cy.get('[cypress-sinopse]').type('Lorem ipsum dolor sit amet').parent().should('have.class', 'has-success')
    cy.get('[cypress-altura]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-altura]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-altura]').clear().type('1,1').parent().should('have.class', 'has-success')
    cy.get('[cypress-largura]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-largura]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-largura]').clear().type('1,1').parent().should('have.class', 'has-success')
    cy.get('[cypress-peso]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-peso]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-peso]').clear().type('1,1').parent().should('have.class', 'has-success')
    cy.get('[cypress-profundidade]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-profundidade]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-profundidade]').clear().type('1,1').parent().should('have.class', 'has-success')    
    cy.get('[cypress-preco]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-preco]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-preco]').clear().type('1,1').parent().should('have.class', 'has-success')
    cy.get('[cypress-codigoBarras]').type('1234567899').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-grupoPrecificacao]').select('1').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
  });

  it('Lista os livros', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemLivros]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
  });

  it('Edita um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemLivros]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')

    cy.get('[cypress-editarLivro]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarLivro')

    cy.get('input:not([type="hidden"]):not([type="file"])').clear()
    cy.get('textarea').clear()
    cy.get('[cypress-status]').select('')
    cy.get('[cypress-editora]').select('')
    cy.get('[cypress-autor]').select('')
    cy.get('[cypress-grupoPrecificacao]').select('')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-titulo]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-autor]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-editora]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-ano]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-isbn]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-numeroPaginas]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-sinopse]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-altura]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-largura]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-peso]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-profundidade]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-preco]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-codigoBarras]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-grupoPrecificacao]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-titulo]').type('Teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-autor]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-editora]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-ano]').type('2012').parent().should('have.class', 'has-success')
    cy.get('[cypress-isbn]').type('235461').parent().should('have.class', 'has-success')
    cy.get('[cypress-numeroPaginas]').type('100').parent().should('have.class', 'has-success')
    cy.get('[cypress-sinopse]').type('Lorem ipsum dolor sit amet').parent().should('have.class', 'has-success')
    cy.get('[cypress-altura]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-altura]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-altura]').clear().type('1,1').parent().should('have.class', 'has-success')
    cy.get('[cypress-largura]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-largura]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-largura]').clear().type('1,1').parent().should('have.class', 'has-success')
    cy.get('[cypress-peso]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-peso]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-peso]').clear().type('1,1').parent().should('have.class', 'has-success')
    cy.get('[cypress-profundidade]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-profundidade]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-profundidade]').clear().type('1,1').parent().should('have.class', 'has-success')    
    cy.get('[cypress-preco]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-preco]').clear().type('1,').parent().should('have.class', 'has-danger')
    cy.get('[cypress-preco]').clear().type('1,1').parent().should('have.class', 'has-success')
    cy.get('[cypress-codigoBarras]').type('1234567899').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-grupoPrecificacao]').select('1').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
  });

  it('Faz um pedido de inativação de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemLivros]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')

    cy.get('[cypress-justificarInativacaoLivro]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/justificarInativacaoLivro')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-categoriaInativacao]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-justificativa]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-categoriaInativacao]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-justificativa]').type('Lorem ipsum').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')

  });

  it('Cancela um pedido de inativação de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemLivros]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')

    cy.get('[cypress-cancelarInativacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
  });

  it('Lista os livros com pedidos de inativação', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-livrosPendentesInativacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
  });

  it('Aceita o pedido de inativação de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-livrosPendentesInativacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
    cy.get('[cypress-aceitarInativacao]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
  });

  it('Recusa o pedido de inativação de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-livrosPendentesInativacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
    cy.get('[cypress-recusarInativacao]').click().wait(100) 
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/livrosPendentesInativacao')
  });

  it('Lista as alterações no estoque de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemLivros]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')

    cy.get('[cypress-listagemEstoque]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemEstoque')
  });

  it('Insere uma entrada de estoque de um livro', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemLivros]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')

    cy.get('[cypress-listagemEstoque]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemEstoque')

    cy.get('[cypress-cadastroEstoque]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroEstoque')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-custo]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-fornecedor]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataEntrada]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-quantidade]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-quantidade]').clear().type('11,1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-quantidade]').clear().type('11').parent().should('have.class', 'has-success')
    cy.get('[cypress-custo]').type('1e11').parent().should('have.class', 'has-danger')
    cy.get('[cypress-custo]').clear().type('11').parent().should('have.class', 'has-success')
    cy.get('[cypress-custo]').type(',11').parent().should('have.class', 'has-success')
    cy.get('[cypress-fornecedor]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataEntrada]').type('2021-02-03').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemEstoque')

  });
});

describe('Teste de condução de CRUD de grupos de precificação', () => {
  it('Cadastra um grupo de precificação', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-cadastroGrupoPrecificacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroGrupoPrecificacao')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-porcentagem]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-porcentagem]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-porcentagem]').clear().type('10').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemGruposPrecificacao')
  });

  it('Lista os grupos de precificação', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemGruposPrecificacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemGruposPrecificacao')
  });

  it('Edita um grupo de precificação', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemGruposPrecificacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemGruposPrecificacao')

    cy.get('[cypress-editarGrupoPrecificacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarGrupoPrecificacao')

    cy.get('input:not([type="hidden"])').clear()
    cy.get('[cypress-status]').select('')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-porcentagem]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-porcentagem]').type('1e1').parent().should('have.class', 'has-danger')
    cy.get('[cypress-porcentagem]').clear().type('11').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemGruposPrecificacao')
  });

  it('Altera o status de um grupo de precificação', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemGruposPrecificacao]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemGruposPrecificacao')

    cy.get('[cypress-alterarStatusGrupoPrecificacao]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemGruposPrecificacao')
  });
});

describe('Teste de condução de CRUD de fornecedores', () => {
  it('Cadastra um fornecedor', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-cadastroFornecedor]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/cadastroFornecedor')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoDocumento]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-documento]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataValidade]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-nomeEndereco]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoEndereco]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoResidencia]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-funcaoEndereco]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-pais]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-cep]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-logradouro]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-numero]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-bairro]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-cidade]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-estado]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').clear().type('teste@tes').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').clear().type('teste@teste.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoDocumento]').select('CNPJ').parent().should('have.class', 'has-success')
    cy.get('[cypress-documento]').type('12345678901234').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataValidade]').type('2023-12-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-nomeEndereco]').type('Centro de fornecimento').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoEndereco]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoResidencia]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-funcaoEndereco]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-pais]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-cep]').type('12345-567').parent().should('have.class', 'has-success')
    cy.get('[cypress-logradouro]').type('Rua teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-numero]').type('12').parent().should('have.class', 'has-success')
    cy.get('[cypress-bairro]').type('Bairro Teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-cidade]').type('Cidade Teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-estado]').type('SP').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemFornecedores')
  });

  it('Lista os fornecedores', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemFornecedor]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemFornecedor')
  });

  it('Edita um fornecedor', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemFornecedor]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemFornecedor')

    cy.get('[cypress-editarFornecedor]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/editarFornecedor')

    cy.get('input:not([type="hidden"])').clear()
    cy.get('[cypress-status]').select('')
    cy.get('[cypress-tipoDocumento]').select('')
    cy.get('[cypress-tipoEndereco]').select('')
    cy.get('[cypress-tipoResidencia]').select('')
    cy.get('[cypress-funcaoEndereco]').select('')
    cy.get('[cypress-pais]').select('')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-status]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoDocumento]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-documento]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataValidade]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-nomeEndereco]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoEndereco]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoResidencia]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-funcaoEndereco]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-pais]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-cep]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-logradouro]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-numero]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-bairro]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-cidade]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-estado]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').clear().type('teste@tes').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').clear().type('teste@teste.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-status]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoDocumento]').select('CNPJ').parent().should('have.class', 'has-success')
    cy.get('[cypress-documento]').type('12345678901234').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataValidade]').type('2023-12-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-nomeEndereco]').type('Centro de fornecimento').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoEndereco]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoResidencia]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-funcaoEndereco]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-pais]').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-cep]').type('12345-567').parent().should('have.class', 'has-success')
    cy.get('[cypress-logradouro]').type('Rua teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-numero]').type('12').parent().should('have.class', 'has-success')
    cy.get('[cypress-bairro]').type('Bairro Teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-cidade]').type('Cidade Teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-estado]').type('SP').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemFornecedores')
  });

  it('Altera o status de um fornecedor', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-listagemFornecedor]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemFornecedor')

    cy.get('[cypress-alteraStatusFornecedor]').click().wait(100)
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemFornecedor')
  });
});

describe('Teste de condução de configurações', () => {
  it('Cria um usuário novo', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-configuracoes]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/configuracoes')

    cy.get('[cypress-numerosVendaInativacaoAutomatica]')
      .type('1e1')
      .parent()
      .should('have.class', 'has-danger')

    cy.get('[cypress-diasInativacaoAutomatica]')
      .type('1e1')
      .parent()
      .should('have.class', 'has-danger')

    cy.get('[cypress-diasPermanenciaCarrinho]')
      .type('1e1')
      .parent()
      .should('have.class', 'has-danger')

    cy.get('[cypress-diasPermanenciaBloqueioItemCarrinho]')
      .type('1e1')
      .parent()
      .should('have.class', 'has-danger')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/configuracoes')

    cy.get('[cypress-numerosVendaInativacaoAutomatica]').clear()
      .type('1')
      .parent()
      .should('have.class', 'has-success')

    cy.get('[cypress-diasInativacaoAutomatica]').clear()
      .type('1')
      .parent()
      .should('have.class', 'has-success')

    cy.get('[cypress-diasPermanenciaCarrinho]').clear()
      .type('1')
      .parent()
      .should('have.class', 'has-success')

    cy.get('[cypress-diasPermanenciaBloqueioItemCarrinho]').clear()
      .type('1')
      .parent()
      .should('have.class', 'has-success')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
  })
});

describe('Teste de implantação de tabelas de domínio', () => {
  it('Clica no link de implantação de tabelas de domínio', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/homeAdmin')
    cy.get('[cypress-implantarDominio]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
  });
});