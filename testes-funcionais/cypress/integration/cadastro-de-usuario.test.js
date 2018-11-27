describe('Cadastro de usuario', () => {

    it('Permite o cadastro de novos usuarios administradores', () => {
        cy.visit('/registrar')

        cy.get("#ADMINISTRADOR").click()

        cy.get("#first_name").type("admin")
        cy.get("#password").type("admin")

        cy.get("input[type=submit]").click()
        cy.location('pathname').should('eq', '/login')
    });

    it('Permite o cadastro de novos usuarios assistentes', () => {
        cy.visit('/registrar')

        cy.get("#ASSISTENTE_SOCIAL").click()

        cy.get("#nomeAssistente").type("assistente")
        cy.get("#emailDaAssistente").type("assistente")
        cy.get("#hospitalDeReferencia").type("assistente")
        cy.get("#telefoneAssistente").type("assistente")
        cy.get("#first_name").type("assistente")
        cy.get("#password").type("assistente")

        cy.get("input[type=submit]").click()
        cy.location('pathname').should('eq', '/login')
    });

});
