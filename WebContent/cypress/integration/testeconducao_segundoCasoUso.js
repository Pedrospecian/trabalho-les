let port = "8080";

describe('Teste de análise', () => {
    it('Monitora as alterações de status de um pedido', () => {
        cy.visit('http://localhost:' + port + '/trabalho-les/loginAdmin')
        cy.wait(1000);
        cy.get('[cypress-email]').type("teste@adminnovo.com")
        cy.wait(1000);
        cy.get('[cypress-senha]').type("123Mudar!")
        cy.wait(1000);
        cy.get('[cypress-submit]').click()
        cy.wait(1000);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/homeAdmin')
        cy.wait(1000);

        //gera um grafico
        cy.get('[cypress-datainicio]').type("2021-02-01")
        cy.wait(1000);
        cy.get('[cypress-datafim]').type("2021-06-30")
        cy.wait(1000);
        cy.get('[cypress-submitgrafico]').click()
        cy.wait(1000);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/gerarGrafico')
    });  
});
