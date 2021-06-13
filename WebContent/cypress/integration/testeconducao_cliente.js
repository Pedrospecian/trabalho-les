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
    cy.get('[cypress-genero]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoCliente]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataNascimento]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senha]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-senhaConfirmar]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-genero').select('1').parent().should('have.class', 'has-success')
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
    cy.get('[cypress-genero').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-tipoCliente').select('1').parent().should('have.class', 'has-success')
    cy.get('[cypress-dataNascimento').type('1988-03-12').parent().should('have.class', 'has-success')
    cy.get('[cypress-email]').type('teste@taieoste.com').parent().should('have.class', 'has-success')
    cy.get('[cypress-senha]').type('123Muda!').parent().should('have.class', 'has-success')
    cy.get('[cypress-senhaConfirmar]').clear().type('123Muda!').parent().should('have.class', 'has-success')

    cy.get('.js-tipo-documento').select('1')
    cy.get('.js-numero-documento').type('46462631570')
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

    cy.get('.js-nome-endereco').type('Endereço Teste')
    cy.get('.js-tipo-endereco').select('1')
    cy.get('.js-tipo-residencia').select('1')
    cy.get('.js-funcao-endereco').select('2')
    cy.get('.js-pais').select('1')
    cy.get('.js-cep').type('08780220')
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
    cy.get('input[name="cartaoPreferencial"][value="1111222233334444"]').click()

    cy.get('.js-tipo-telefone').select('1')
    cy.get('.js-ddd-telefone').type('11')
    cy.get('.js-numero-telefone').type('912345678')
    cy.get('.js-adicionar-telefone').click()
    cy.get('.js-boxes-telefones .box-single').should('be.visible')

    cy.get('[cypress-submit]').click()

    cy.url().should('include', 'http://localhost:' + port + '/trabalho-les/home')
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
    cy.get('[cypress-genero]').select('')
    cy.get('[cypress-tipoCliente]').select('')
    cy.get('[cypress-dataNascimento]').clear()

    cy.get('[cypress-submit]').click()

    cy.get('[cypress-nome]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-email]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-genero]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-tipoCliente]').parent().should('have.class', 'has-danger')
    cy.get('[cypress-dataNascimento]').parent().should('have.class', 'has-danger')

    cy.get('[cypress-nome]').type('teste').parent().should('have.class', 'has-success')
    cy.get('[cypress-genero').select('1').parent().should('have.class', 'has-success')
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