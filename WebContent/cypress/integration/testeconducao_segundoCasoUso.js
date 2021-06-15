let port = "8080";
let waitTime = 10;

describe('Teste de análise', () => {
    it('Monitora as alterações de status de um pedido', () => {
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

        //gera um grafico
        cy.get('[cypress-datainicio]').type("2021-02-01")
        cy.wait(waitTime);
        cy.get('[cypress-datafim]').type("2021-06-30")
        cy.wait(waitTime);
        cy.get('[cypress-submitgrafico]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/gerarGrafico')
    });  
});
