let port = "8080";
let waitTime = 1;

describe('Teste de condução de processo de compra', () => {
  it('Adiciona um produto a um carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
  	cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    cy.wait(waitTime);
    cy.get('.book-single:first-child [cypress-livro-single]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);
    cy.get('[cypress-quantidade]').type(2).parent().should('have.class', 'has-success')
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(waitTime);
    cy.get('[cypress-logout]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  /*it('Adiciona mais um produto a um carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    cy.wait(waitTime);
    cy.get('.book-single:nth-child(2) [cypress-livro-single]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')
    cy.wait(waitTime);

    cy.get('[cypress-quantidade]').type(1).parent().should('have.class', 'has-success')
    cy.wait(waitTime);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(waitTime);

    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(waitTime);
    cy.get('[cypress-logout]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });*/

  it('Finaliza uma compra', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/login')
      cy.wait(waitTime);
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.wait(waitTime);
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    cy.wait(waitTime);

    cy.get('[cypress-minhaConta]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.wait(waitTime);
    cy.get('[cypress-carrinho]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(waitTime);
    cy.get('[cypress-finalizar]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(waitTime);

    //usa um endereço e um cartao ja cadastrados
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.wait(waitTime);
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').type(1)
    cy.wait(waitTime);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(waitTime);
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').clear().type(673)
    cy.wait(3000);
    cy.get('[cypress-submit]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
    cy.get('[cypress-logout]').click()
    cy.wait(waitTime);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  
});

describe('Teste de alteração de status de pedido', () => {
    it('Monitora as alterações de status de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(waitTime);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(waitTime);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(waitTime);
        cy.get('[cypress-submit]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')        
        cy.wait(waitTime);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(waitTime);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')     
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em processamento');
        cy.wait(waitTime);

        cy.get('[cypress-logout]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);

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

        //alterar pedido para aceito
        cy.get('[cypress-todosPedidos]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Aceito');
        cy.wait(waitTime);

        cy.get('[cypress-logout]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);

        //visualiza status do pedido como aceito
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(waitTime);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(waitTime);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(waitTime);
        cy.get('[cypress-submit]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')  
        cy.wait(waitTime);      

        cy.get('[cypress-minhaConta]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(waitTime);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Aceito');
        cy.wait(waitTime);

        cy.get('[cypress-logout]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);

        //alterar pedido para em trânsito
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

        cy.get('[cypress-todosPedidos]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em trânsito');
        cy.wait(waitTime);

        cy.get('[cypress-logout]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);

        //visualiza status do pedido como em transito
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(waitTime);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(waitTime);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(waitTime);
        cy.get('[cypress-submit]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);        

        cy.get('[cypress-minhaConta]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(waitTime);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em trânsito');
        cy.wait(waitTime);

        cy.get('[cypress-logout]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);

        //alterar pedido para entregue
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

        cy.get('[cypress-todosPedidos]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Entregue');
        cy.wait(waitTime);

        cy.get('[cypress-logout]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);

        //visualiza status do pedido como entregue
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(waitTime);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(waitTime);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(waitTime);
        cy.get('[cypress-submit]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')        
        cy.wait(waitTime);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(waitTime);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Entregue');
        cy.wait(waitTime);

        cy.get('[cypress-logout]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        
    });
});

describe('Teste de reprovação de pedido com cartão inválido', () => {
    it('Monitora as alterações de status de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(waitTime);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(waitTime);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(waitTime);
        cy.get('[cypress-submit]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);

        cy.get('.book-single:first-child [cypress-livro-single]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
        cy.wait(waitTime);
        cy.get('[cypress-quantidade]').type(2).parent().should('have.class', 'has-success')
        cy.wait(waitTime);

        cy.get('[cypress-submit]').click()
        cy.wait(waitTime);
        
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
        cy.wait(waitTime);
        cy.get('[cypress-finalizar]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
        cy.wait(waitTime);

        cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
        cy.get('.js-cartoes-wrapper-checkout .box-single:nth-child(1) [cypress-valorPagoCartao]').clear().type(673.0)
        cy.wait(2000);
        cy.get('[cypress-submit]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(waitTime);

        cy.get('[cypress-logout]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(waitTime);

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

        cy.get('[cypress-todosPedidos]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.wait(waitTime);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Aceito');
        cy.wait(waitTime);
    })
});
