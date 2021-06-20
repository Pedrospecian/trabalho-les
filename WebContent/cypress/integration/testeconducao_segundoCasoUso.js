let port = "8080";
let waitTime = 600;

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
        cy.wait(5000);
        cy.get('[cypress-datafim]').clear().type("2021-04-30")
        cy.wait(waitTime);
        cy.get('[cypress-tipo]').select("livro")
        cy.get('[cypress-submitgrafico]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/gerarGrafico')
        cy.wait(5000);
        cy.get('[cypress-datainicio]').clear().type("2021-05-01")
        cy.wait(waitTime);
        cy.get('[cypress-datafim]').clear().type("2021-06-30")
        cy.wait(waitTime);
        cy.get('[cypress-submitgrafico]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/gerarGrafico')
        cy.wait(5000);
        cy.get('[cypress-tipo]').select("categoria")
        cy.get('[cypress-submitgrafico]').click()
        cy.wait(waitTime);
        cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/gerarGrafico')
    });  
});
