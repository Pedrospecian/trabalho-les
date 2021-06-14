let port = "8080";

describe('Teste de condução de processo de compra', () => {
  it('Acessa um livro', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/home')

    cy.wait(600);

    cy.get('.book-single:first-child [cypress-livro-single]').click()

    cy.wait(600);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
  });

  it('Acessa o carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')

    cy.wait(600);

    cy.get('[cypress-email]').type("teste@loginhio.com")
    
    cy.wait(600);

    cy.get('[cypress-senha]').type("123Mudar!")
    
    cy.wait(600);

    cy.get('[cypress-submit]').click()
    
    cy.wait(600);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    
    cy.wait(600);

    cy.get('[cypress-minhaConta]').click()
    
    cy.wait(600);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    
    cy.wait(600);

    cy.get('[cypress-carrinho]').click()
    
    cy.wait(600);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    
    cy.wait(600);

    cy.get('[cypress-logout]').click()

    cy.wait(600);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  it('Adiciona um produto a um carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.wait(600);
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.wait(600);
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
  	cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    cy.wait(600);
    cy.get('.book-single:first-child [cypress-livro-single]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')
    cy.wait(600);
    cy.get('[cypress-quantidade]').type(2).parent().should('have.class', 'has-success')
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(600);
    cy.get('[cypress-logout]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  it('Adiciona mais um produto a um carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.wait(600);
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.wait(600);
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    cy.wait(600);
    cy.get('.book-single:nth-child(2) [cypress-livro-single]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(600);

    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.get('[cypress-quantidade]').parent().should('have.class', 'has-danger')
    cy.wait(600);

    cy.get('[cypress-quantidade]').type(1).parent().should('have.class', 'has-success')
    cy.wait(600);

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(600);

    cy.get('[cypress-submit]').click()
    cy.wait(600);
    
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(600);
    cy.get('[cypress-logout]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  it('Altera a quantidade de um produto no carrinho', () => {
    cy.visit('http://localhost:' + port + '/trabalho-les/login')
    cy.wait(600);
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.wait(600);
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    cy.wait(600);
    cy.get('[cypress-minhaConta]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.wait(600);
    cy.get('[cypress-carrinho]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(600);
    cy.get('tr:first-child [cypress-quantidade]').clear().type(5)
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(600);

    cy.get('[cypress-logout]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  it('Finaliza uma compra', () => {
  	cy.visit('http://localhost:' + port + '/trabalho-les/login')
      cy.wait(600);
    cy.get('[cypress-email]').type("teste@loginhio.com")
    cy.wait(600);
    cy.get('[cypress-senha]').type("123Mudar!")
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    cy.wait(600);

    cy.get('[cypress-minhaConta]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
    cy.wait(600);
    cy.get('[cypress-carrinho]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(600);
    cy.get('[cypress-finalizar]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(600);

    //usa um endereço e um cartao ja cadastrados
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.wait(600);
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').type(1)
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(600);
    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').clear().type(1812.20)
    cy.wait(3000);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
    cy.wait(600);
    cy.visit('http://localhost:' + port + '/trabalho-les/home')
    cy.wait(600);
    cy.get('.book-single:first-child [cypress-livro-single]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
    cy.wait(600);
    cy.get('[cypress-quantidade]').type(5).parent().should('have.class', 'has-success')
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);

    //tenta pagar a conta usando mais de um cartao
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
    cy.wait(600);
    cy.get('[cypress-finalizar]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(600);
    cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
    cy.wait(600);

    //cadastra um novo endereço
    cy.get('[cypress-enderecoEntregaNovo]').click()
    cy.wait(600);

    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(600);

    cy.get('.js-tipo-endereco').select('1')
    cy.wait(600);
    cy.get('.js-tipo-residencia').select('1')
    cy.wait(600);
    cy.get('.js-funcao-endereco').select('2')
    cy.wait(600);
    cy.get('.js-pais').select('1')
    cy.wait(600);
    cy.get('.js-cep').type('12345-678')
    cy.wait(600);
    cy.get('.js-tipo-logradouro').select('1')
    cy.wait(600);
    cy.get('.js-logradouro').type('Rua teste')
    cy.wait(600);
    cy.get('.js-numero').type('6012')
    cy.wait(600);
    cy.get('.js-nome-endereco').type('Casa da minha tia')
    cy.wait(600);
    cy.get('.js-bairro').type('Perdizes')
    cy.wait(600);
    cy.get('.js-cidade').type('São Paulo')
    cy.wait(600);
    cy.get('.js-uf').type('SP')
    cy.wait(600);
    
    //cadastra um novo cartao
    cy.get('.js-checkout-new-card').click()
    cy.wait(600);
    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
    cy.wait(600);

    cy.get('.js-cartoes-wrapper-checkout .box-single:first-child [cypress-valorPagoCartao]').clear().type(1440.0)
    cy.wait(600);

    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-numero-cartao').type("5206984449283106")
    cy.wait(600);
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-nome-cartao').type("cartao novo teste")
    cy.wait(600);
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-bandeira-cartao').select("2")
    cy.wait(600);
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-cvv-cartao').type("536")
    cy.wait(600);
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-validade-cartao').type("2022-08-26")
    cy.wait(600);
    cy.get('.js-cartoes-novos-wrapper .box-single:first-child .js-valorPagamento').type(20)
    cy.wait(600);

    //insere um cupom de desconto
    cy.get('[cypress-cupomDesconto]').type("DESCONTOEH10!!!")
    cy.wait(600);

    //insere varios cupons de troca

    cy.get('.js-adicionar-cupom-troca').click()
    cy.wait(600);

    cy.get('.js-boxes-cupons-troca .box-single').should('not.exist')
    cy.wait(600);

    cy.get('.js-cupom-troca').type('CUPOMTROCA9120214125074130')
    cy.wait(600);
    cy.get('.js-adicionar-cupom-troca').click()

    cy.wait(4000);

    cy.get('.js-cupom-troca').type('TESTELOGINHO20213825053846')
    cy.wait(600);
    cy.get('.js-adicionar-cupom-troca').click()

    cy.wait(2000);

    cy.get('[cypress-submit]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
    cy.wait(600);

    cy.get('[cypress-logout]').click()
    cy.wait(600);
    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
  });

  
});

describe('Teste de alteração de status de pedido', () => {
    it('Visualiza a listagem de pedidos', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);
        cy.get('[cypress-meusPedidos').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    });

    it('Visualiza os detalhes de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tbody tr:first-child [cypress-detalhes-pedido]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido')
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
    });

    it('Monitora as alterações de status de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')        
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')     
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em processamento');
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
        cy.wait(600);

        //alterar pedido para aceito
        cy.get('[cypress-todosPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Aceito');
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        //visualiza status do pedido como aceito
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')  
        cy.wait(600);      

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Aceito');
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        //alterar pedido para em trânsito
        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
        cy.wait(600);

        cy.get('[cypress-todosPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em trânsito');
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        //visualiza status do pedido como em transito
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);        

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Em trânsito');
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        //alterar pedido para entregue
        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
        cy.wait(600);

        cy.get('[cypress-todosPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Entregue');
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        //visualiza status do pedido como entregue
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')        
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);
        cy.get('[cypress-meusPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Entregue');
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        
    });
});

describe('Teste de reprovação de pedido com cartão inválido', () => {
    it('Monitora as alterações de status de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('.book-single:first-child [cypress-livro-single]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/produto')
        cy.wait(600);
        cy.get('[cypress-quantidade]').type(2).parent().should('have.class', 'has-success')
        cy.wait(600);

        cy.get('[cypress-submit]').click()
        cy.wait(600);
        
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/carrinho')
        cy.wait(600);
        cy.get('[cypress-finalizar]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/checkout')
        cy.wait(600);

        cy.get('.enderecos-wrapper .box-single:first-child [cypress-enderecoEntrega]').click()
        cy.get('.js-cartoes-wrapper-checkout .box-single:nth-child(2) [cypress-valorPagoCartao]').clear().type(673.0)
        cy.wait(2000);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
        cy.wait(600);

        cy.get('[cypress-todosPedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/todosPedidos');
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-acoesPedido] a:not([cypress-detalhes-pedido])').click();
        cy.wait(600);
        cy.get('.paginated-table-wrapper .tabela-pagina-single.active tr:first-child [cypress-statusPedido]').should('contain', 'Reprovado');
        cy.wait(600);


    })
});

describe('Teste de troca', () => {
    it('Faz uma solicitação de troca', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);

        cy.get('[cypress-meuspedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr[data-status=4] a[cypress-detalhes-pedido]').first().click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido?')
        cy.wait(600);

        cy.get('tr:first-child [cypress-trocaQtde]').type('2')
        cy.wait(600);
        cy.get('tr:first-child [cypress-submit]').click()
        cy.wait(600);

        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/solicitarTroca')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);

        cy.get('[cypress-meuspedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr[data-status=5] a[cypress-detalhes-pedido]').first().click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido?')
        cy.wait(600);

        cy.get('tr:first-child [cypress-quantidadeLivroPedido]').should(($lis) => {
            expect($lis.eq(0)).to.contain('2 -> troca solicitada')
        })
        cy.wait(600);
    });

    it('Recusa a solicitação da troca', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
        cy.wait(600);

        cy.get('[cypress-listagemsolicitacoestroca]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemSolicitacoesTroca')
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr:first-child a[cypress-recusartroca]').click()
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr:first-child td[cypress-solicitacoestrocastatus]').should(($lis) => {
            expect($lis.eq(0)).to.contain('Troca recusada')
        })
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);

        cy.get('.notificacoes-wrapper .notificacao-single:first-child').should(($lis) => {
            expect($lis.eq(0)).to.contain('Aviso: um de seus pedidos de troca foi recusado.')
        })
        cy.wait(600);
    });

    it('Aceita a solicitação da troca', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);

        cy.get('[cypress-meuspedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr[data-status=4] a[cypress-detalhes-pedido]').first().click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido?')
        cy.wait(600);

        cy.get('tr:first-child [cypress-trocaQtde]').type('2')
        cy.wait(600);
        cy.get('tr:first-child [cypress-submit]').click()
        cy.wait(600);

        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/solicitarTroca')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);

        cy.get('[cypress-meuspedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr[data-status=5] a[cypress-detalhes-pedido]').first().click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido?')
        cy.wait(600);

        cy.get('tr:first-child [cypress-quantidadeLivroPedido]').should(($lis) => {
            expect($lis.eq(0)).to.contain('2 -> troca solicitada')
        })
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
        cy.wait(600);

        cy.get('[cypress-listagemsolicitacoestroca]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemSolicitacoesTroca')
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr:first-child a[cypress-autorizartroca]').click()
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr:first-child td[cypress-solicitacoestrocastatus]').should(($lis) => {
            expect($lis.eq(0)).to.contain('Troca aceita. Aguardando confirmação de recebimento')
        })
        cy.wait(600);

        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);

        cy.get('.notificacoes-wrapper .notificacao-single:first-child').should(($lis) => {
            expect($lis.eq(0)).to.contain('Aviso: um de seus pedidos de troca foi aceito. Confira seus pedidos!')
        })
        cy.wait(600);
    });
    
    it('Confirma recebimento de troca sem avarias', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);
        cy.get('[cypress-meuscuponstroca]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusCuponsTroca')
        cy.wait(600);

        cy.get('tr').then($cuponsTroca => {
            const qtdeCuponsAtual = $cuponsTroca.length  

            cy.get('[cypress-logout]').click()
            cy.wait(600);

            cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
            cy.wait(600);
            cy.get('[cypress-email]').type("teste@adminnovo.com")
            cy.wait(600);
            cy.get('[cypress-senha]').type("123Mudar!")
            cy.wait(600);
            cy.get('[cypress-submit]').click()
            cy.wait(600);
            cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
            cy.wait(600);

            //confere o estoque antes da confirmacao de recebimento
            cy.get('[cypress-homeAdmin]').click()
            cy.wait(600);
            cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
            cy.wait(600);
            cy.get('[cypress-listagemlivros]').click()
            cy.wait(600);
            cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
            cy.wait(600);

            cy.get('tr[data-id="7"] td[data-estoque]').then($estoque => {
                const estoqueAnterior = $estoque.text()        

                cy.get('[cypress-homeAdmin]').click()
                cy.wait(600);
                cy.get('[cypress-listagemsolicitacoestroca]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemSolicitacoesTroca')
                cy.wait(600);

                cy.get('.tabela-pagina-single.active tr:first-child [cypress-retornarestoque]').click()
                cy.wait(600);
                cy.get('.tabela-pagina-single.active tr:first-child [cypress-confirmarrecebimento]').click()
                cy.wait(600);
                
                cy.get('.tabela-pagina-single.active tr:first-child td[cypress-solicitacoestrocastatus]').should(($lis) => {
                    expect($lis.eq(0)).to.contain('Recebimento confirmado. Os itens retornaram ao estoque')
                })
                cy.wait(600);

                //o estoque precisa ser alterado
                cy.get('[cypress-logout]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
                cy.wait(600);

                cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
                cy.wait(600);
                cy.get('[cypress-email]').type("teste@adminnovo.com")
                cy.wait(600);
                cy.get('[cypress-senha]').type("123Mudar!")
                cy.wait(600);
                cy.get('[cypress-submit]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
                cy.wait(600);
                cy.get('[cypress-listagemlivros]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
                cy.wait(600);

                cy.get('tr[data-id="7"] td[data-estoque]').should(($list) => {
                    expect($list.eq(0)).to.contain(parseInt(estoqueAnterior) + 2)
                })
                cy.wait(600);
                cy.get('[cypress-logout]').click()
                cy.wait(600);

                cy.visit('http://localhost:' + port + '/trabalho-les/login')
                cy.wait(600);
                cy.get('[cypress-email]').type("teste@loginhio.com")
                cy.wait(600);
                cy.get('[cypress-senha]').type("123Mudar!")
                cy.wait(600);
                cy.get('[cypress-submit]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
                cy.wait(600);

                cy.get('[cypress-minhaConta]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
                cy.wait(600);
                cy.get('[cypress-meuscuponstroca]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusCuponsTroca')
                cy.wait(600);

                cy.get('tr').then($cuponsTrocanovo => {
                    expect($cuponsTrocanovo.length).to.eq(qtdeCuponsAtual + 1)
                })
                cy.wait(600);

                //o status do produto precisa ser alterado para trocado

                cy.get('[cypress-minhaConta]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
                cy.wait(600);
                cy.get('[cypress-meuspedidos]').click()
                cy.wait(600);
                cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedido')
                cy.wait(600);

                cy.get('.tabela-pagina-single.active tr[data-status=7] td[cypress-statusPedido]').should(($lis) => {
                    expect($lis.eq(0)).to.contain('Trocado')
                })
                cy.wait(600);
                
            })
        })
    });

    it('Confirma recebimento de troca com avarias', () => {
        //o estoque precisa ser mantido
        cy.visit('http://localhost:' + port + '/trabalho-les/login')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@loginhio.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);

        cy.get('[cypress-meuspedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);
        //6=>troca autorizada
        //7=>troca concluida
        cy.get('.tabela-pagina-single.active tr[data-status=7] a[cypress-detalhes-pedido]').first().click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido?')
        cy.wait(600);

        cy.get('tr:first-child [cypress-trocaQtde]').type('2')
        cy.wait(600);
        cy.get('tr:first-child [cypress-submit]').click()
        cy.wait(600);

        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/solicitarTroca')
        cy.wait(600);

        cy.get('[cypress-minhaConta]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/minhaConta')
        cy.wait(600);

        cy.get('[cypress-meuspedidos]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/meusPedidos')
        cy.wait(600);

        cy.get('.tabela-pagina-single.active tr[data-status=5] a[cypress-detalhes-pedido]').first().click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/pedido?')
        cy.wait(600);

        cy.get('tr:first-child [cypress-quantidadeLivroPedido]').should(($lis) => {
            expect($lis.eq(0)).to.contain('4 -> troca solicitada')
        })
        cy.wait(600);

        //confere o estoque antes da confirmacao de recebimento
        cy.get('[cypress-logout]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
        cy.wait(600);

        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.wait(600);
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.wait(600);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(600);
        cy.get('[cypress-submit]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
        cy.wait(600);
        cy.get('[cypress-listagemlivros]').click()
        cy.wait(600);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
        cy.wait(600);

        cy.get('tr[data-id="7"] td[data-estoque]').then($estoque => {
            const estoqueAnterior = $estoque.text()        

            cy.get('[cypress-homeAdmin]').click()
            cy.wait(600);
            cy.get('[cypress-listagemsolicitacoestroca]').click()
            cy.wait(600);
            cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemSolicitacoesTroca')
            cy.wait(600);
            cy.get('.tabela-pagina-single.active tr:first-child a[cypress-autorizartroca]').click()
            cy.wait(600);
            cy.get('.tabela-pagina-single.active tr:first-child [cypress-confirmarrecebimento]').click()
            cy.wait(600);
            
            cy.get('.tabela-pagina-single.active tr:first-child td[cypress-solicitacoestrocastatus]').should(($lis) => {
                expect($lis.eq(0)).to.contain('Recebimento confirmado. Os itens não retornaram ao estoque')
            })
            cy.wait(600);

            //o estoque precisa ser mantido como estava
            cy.get('[cypress-homeAdmin]').click()
            cy.wait(600);
            cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
            cy.wait(600);
            cy.get('[cypress-listagemlivros]').click()
            cy.wait(600);
            cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/listagemLivros')
            cy.wait(600);


            cy.get('tr[data-id="7"] td[data-estoque]').should(($lis) => {
                expect($lis.eq(0)).to.contain(estoqueAnterior)
            })
            cy.wait(600);
        })
    });

});
