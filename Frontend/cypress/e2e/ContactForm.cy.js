describe('Contact Form', () => {
  beforeEach(() => {
    cy.visit('/contact') // assuming the contact form is located at /contact route
  })

  it('should disable submit button when form is invalid', () => {
    cy.get('form').within(() => {
      cy.get('#name').type('John')
      cy.get('#email').type('invalid email')
      cy.get('#message').type('Hello World')
      cy.get("#submitButton").should('be.disabled')
    })
  })

  it('should submit form successfully when all fields are valid', () => {
    const name = 'John Doe'
    const email = 'johndoe@example.com'
    const message = 'Hello World'
  
    cy.get('form').within(() => {
      cy.get('#name').type(name)
      cy.get('#email').type(email)
      cy.get('#message').type(message)
      cy.get('#submitButton').should('not.be.disabled').click()
    })
  
  
  
    cy.on("window:confirm", (t) => {
      
      expect(t).to.equal("Success");
   });
  })
  
})
