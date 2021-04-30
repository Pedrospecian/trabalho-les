let port = "8080";

describe('Teste de criação de conta de cliente', () => {
  it('Cadastra um cliente novo', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-login]').click()
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.get('[cypress-novaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/novaConta')

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-sexo]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoCliente]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataNascimento]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-sexo').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoCliente').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataNascimento').type('1988-03-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-senha]').type('12').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').type('e').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').type('Mud').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').type('!').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').type('A!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaConfirmar]').type('algodiferente').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').clear().type('12eMud!A!').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click().wait(500)

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/novaConta')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-sexo').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoCliente').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataNascimento').type('1988-03-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste@teste.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-senha]').type('123Muda!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaConfirmar]').clear().type('123Muda!').parent().should('have.class', 'has-success')

    cy.get('.js-tipo-documento').select('1')
    cy.get('.js-numero-documento').type('33369361795')
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

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
  });
});

describe('Teste de condução de processo de compra', () => {
  it('Busca por livros', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-term]').type('teste')
    cy.get('[cypress-busca]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/busca')

    cy.get('[cypress-livro-single]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
  });

  it('Acessa um livro', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/home')

    cy.get('[cypress-livro-single]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
  });

  it('Acessa o carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-carrinho]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
  });

  it('Adiciona um produto a um carrinho', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/home')

    cy.get('[cypress-livro-single]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')

    cy.get('[cypress-submit]').click()
    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-quantidade]').type(1).parent().should('have.class', 'has-success')

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')

    cy.get('[cypress-submit]').click()
    
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
  });

  it('Altera a quantidade de um produto no carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-carrinho]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.get('[cypress-quantidade]').type(2)
    cy.get('[cypress-remover]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
  });

  it('Finaliza uma compra', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-carrinho]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.get('[cypress-finalizar]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')

    //usa um endereço e um cartao ja cadastrados
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').type(100)
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').type(10)
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').clear().type(1000)
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

    //tenta pagar a conta usando mais de um cartao
    cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').type(600)
    cy.get('.js-cartoes-wrapper-checkout .box-single:nth-child(2) [cypress-valorPagoCartao]').type(400)
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

    //cadastra um novo endereço
    cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('[cypress-enderecoEntregaNovo]').click()

    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')

    cy.get('.js-tipo-endereco').select('1')
    cy.get('.js-tipo-residencia').select('1')
    cy.get('.js-funcao-endereco').select('1')
    cy.get('.js-pais').select('1')
    cy.get('.js-cep').type('12345-678')
    cy.get('.js-tipo-logradouro').select('1')
    cy.get('.js-logradouro').type('Rua teste')
    cy.get('.js-numero').type('6012')
    cy.get('.js-nome-endereco').type('Casa da minha tia')
    cy.get('.js-bairro').type('Perdizes')
    cy.get('.js-cidade').type('São Paulo')
    cy.get('.js-uf').type('SP')

    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').clear().type(1000)
    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
    
    //cadastra um novo cartao
    cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.get('.js-checkout-new-card').click()
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')

    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-numero-cartao').type("2323454567678989")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-nome-cartao').type("Teste")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-bandeira-cartao').select("1")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-cvv-cartao').type("123")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-validade-cartao').type("2025-04-01")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-valorPagamento').type("1000")

    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

    //cadastra dois novos cartoes, associando um deles à conta
    cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.get('.js-checkout-new-card').click()
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')

    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-numero-cartao').type("2323454567678989")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-nome-cartao').type("Teste")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-bandeira-cartao').select("1")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-cvv-cartao').type("123")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-validade-cartao').type("2025-04-01")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-valorPagamento').type("300")

    cy.get('.js-checkout-new-card').click()

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')

    cy.get('.js-cartoes-novos-wrapper .box-single:nth-child(2) .js-numero-cartao').type("2323454567678989")
    cy.get('.js-cartoes-novos-wrapper .box-single:nth-child(2) .js-nome-cartao').type("Teste")
    cy.get('.js-cartoes-novos-wrapper .box-single:nth-child(2) .js-bandeira-cartao').select("1")
    cy.get('.js-cartoes-novos-wrapper .box-single:nth-child(2) .js-cvv-cartao').type("123")
    cy.get('.js-cartoes-novos-wrapper .box-single:nth-child(2) .js-validade-cartao').type("2025-04-01")
    cy.get('.js-cartoes-novos-wrapper .box-single:nth-child(2) .js-valorPagamento').type("700")
    cy.get('.js-cartoes-novos-wrapper .box-single:nth-child(2) input[name="cadastrarCartao"]').click()

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

    //insere um cupom de desconto
    cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').type(600)
    cy.get('.js-cartoes-wrapper-checkout .box-single:nth-child(2) [cypress-valorPagoCartao]').type(300)
    cy.get('[cypress-cupomDesconto]').type("100CONTOEH10")

    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

    //insere varios cupons de troca
    cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').type(700)

    cy.get('.js-adicionar-cupom-troca').click()

    cy.get('.js-boxes-cupons-troca .box-single').should('not.exist')

    cy.get('.js-cupom-troca').type('CUPOMTROCA123')
    cy.get('.js-adicionar-cupom-troca').click()

    cy.get('.js-cupom-troca').type('CUPOMTROCA123')
    cy.get('.js-adicionar-cupom-troca').click()

    cy.get('.js-cupom-troca').type('CUPOMTROCA123')
    cy.get('.js-adicionar-cupom-troca').click()

    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
  });

  it('Visualiza a listagem de pedidos', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-meusPedidos').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
  });

  it('Visualiza os detalhes de um pedido', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-meusPedidos').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
    cy.get('[cypress-detalhesPedido]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido')
  });
});

describe('Teste de condução de processo de troca', () => {
	it('Seleciona um livro para troca', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-meusPedidos').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
    cy.get('[cypress-detalhesPedido]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido')

    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido')

    cy.get('[cypress-trocaQtde]').type(2)
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/solicitarTroca')
	});

  it('Visualiza os cupons de troca do cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-meusCuponsTroca').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusCuponsTroca')
  });
});

describe('Teste de alteração de dados cadastrais do cliente', () => {
	it('Edita os dados do cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')

    cy.get('[cypress-alterarMeusDados]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/alterarMeusDados')

    cy.get('[cypress-nome]').clear()
    cy.get('[cypress-email]').clear()
    cy.get('[cypress-sexo]').select('')
    cy.get('[cypress-tipoCliente]').select('')
    cy.get('[cypress-dataNascimento]').clear()

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-sexo]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoCliente]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataNascimento]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-sexo').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoCliente').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataNascimento').type('1988-03-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('@').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('teste').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').type('.com').parent().should('have.class', 'has-success')

    cy.get('[cypress-submit]').click().wait(500)

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')

    cy.get('[cypress-alterarMeusDados]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/alterarMeusDados')

    cy.get('.js-tipo-documento').select('2')
    cy.get('.js-numero-documento').type('43250987654321')
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

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')

    cy.get('[cypress-alterarMeusDados]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/alterarMeusDados')

    cy.get('.js-boxes-enderecos .box-single:first-of-type .js-remove-existing-endereco').click()
    cy.get('.js-boxes-cartoes .box-single:first-of-type .js-remove-existing-cartao').click()
    cy.get('.js-boxes-telefones .box-single:first-of-type .js-remove-existing-telefone').click()

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
  });

	it('Altera a senha do cliente', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')

    cy.get('[cypress-alterarMinhaSenha]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/alterarMinhaSenha')

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

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
  });

});