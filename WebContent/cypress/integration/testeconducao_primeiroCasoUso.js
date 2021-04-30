let port = "8080";

describe('Teste de condução de processo de compra', () => {
  it('Acessa um livro', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/home')

    cy.get('.book-single:first-child [cypress-livro-single]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
  });

  it('Acessa o carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-carrinho]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.get('[cypress-logout]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  it('Adiciona um produto a um carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.get('[cypress-submit]').click()
  	cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

    cy.get('.book-single:first-child [cypress-livro-single]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')

    cy.get('[cypress-submit]').click()
    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-quantidade]').type(2).parent().should('have.class', 'has-success')

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')

    cy.get('[cypress-submit]').click()
    
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.get('[cypress-logout]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  it('Adiciona mais um produto a um carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

    cy.get('.book-single:last-child [cypress-livro-single]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')

    cy.get('[cypress-submit]').click()
    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-quantidade]').type(4).parent().should('have.class', 'has-success')

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')

    cy.get('[cypress-submit]').click()
    
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.get('[cypress-logout]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  it('Altera a quantidade de um produto no carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

    cy.get('[cypress-minhaConta]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.get('[cypress-carrinho]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.get('tr:first-child [cypress-quantidade]').clear().type(12)
    cy.get('tr:last-child [cypress-remover]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')

    cy.get('[cypress-logout]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  it('Finaliza uma compra', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

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
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').type(1)
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').clear().type(1064.90)
    cy.wait(1000);
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.get('.book-single:first-child [cypress-livro-single]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.get('[cypress-quantidade]').type(5).parent().should('have.class', 'has-success')
    cy.get('[cypress-submit]').click()

    //tenta pagar a conta usando mais de um cartao
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.get('[cypress-finalizar]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    /*cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')*/

    //cadastra um novo endereço
    /*cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
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
    cy.get('.js-uf').type('SP')*/

    /*cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').clear().type(1000)
    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')*/
    
    //cadastra um novo cartao
    //cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
    //cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.get('.js-checkout-new-card').click()
    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')

    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').clear().type(319.8)

    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-numero-cartao').type("5206984449283106")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-nome-cartao').type("cartao novo teste")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-bandeira-cartao').select("2")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-cvv-cartao').type("536")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-validade-cartao').type("2022-08-26")
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-valorPagamento').type(20)

    /*cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')*/

    //cadastra dois novos cartoes, associando um deles à conta
    /*cy.visit('http://localhost:' + port + '/trabalho-les/checkout')
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

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')*/

    //insere um cupom de desconto
    cy.get('[cypress-cupomDesconto]').type("DESCONTOEH10!!!")

    //insere varios cupons de troca

    cy.get('.js-adicionar-cupom-troca').click()

    cy.get('.js-boxes-cupons-troca .box-single').should('not.exist')

    cy.get('.js-cupom-troca').type('CUPOMTROCA9120214125074130')
    cy.get('.js-adicionar-cupom-troca').click()

    cy.wait(500);

    cy.get('.js-cupom-troca').type('TESTELOGINHO20213825053846')
    cy.get('.js-adicionar-cupom-troca').click()

    cy.wait(500);

    cy.get('[cypress-submit]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

    cy.get('[cypress-logout]').click()
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  
});

describe('Teste de alteração de status de pedido', () => {
    it('Visualiza a listagem de pedidos', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        cy.get('[cypress-minhaConta]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.get('[cypress-meusPedidos').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    });

    it('Visualiza os detalhes de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        cy.get('[cypress-minhaConta]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.get('[cypress-meusPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tbody tr:first-child [cypress-detalhes-pedido]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido')

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    });

    it('Monitora as alterações de status de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')        

        cy.get('[cypress-minhaConta]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.get('[cypress-meusPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')     
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em processamento');

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')

        //alterar pedido para aceito
        cy.get('[cypress-todosPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Aceito');

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        //visualiza status do pedido como aceito
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')        

        cy.get('[cypress-minhaConta]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.get('[cypress-meusPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Aceito');

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        //alterar pedido para em trânsito
        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')

        cy.get('[cypress-todosPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em trÃ¢nsito');

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        //visualiza status do pedido como em transito
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')        

        cy.get('[cypress-minhaConta]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.get('[cypress-meusPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em trÃ¢nsito');

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        //alterar pedido para entregue
        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')

        cy.get('[cypress-todosPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Entregue');

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        //visualiza status do pedido como entregue
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')        

        cy.get('[cypress-minhaConta]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.get('[cypress-meusPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Entregue');

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        
    });
});

describe('Teste de reprovação de pedido com cartão inválido', () => {
    it('Monitora as alterações de status de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
          cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        cy.get('.book-single:first-child [cypress-livro-single]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
        cy.get('[cypress-quantidade]').type(2).parent().should('have.class', 'has-success')

        cy.get('[cypress-submit]').click()
        
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
        cy.get('[cypress-finalizar]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')

        cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
        cy.get('.js-cartoes-wrapper-checkout .box-single:nth-child(2) [cypress-valorPagoCartao]').clear().type(199.30)
        cy.wait(1000);
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')

        cy.get('[cypress-logout]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')

        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.get('[cypress-submit]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')

        cy.get('[cypress-todosPedidos]').click()
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Reprovado');


    })
})