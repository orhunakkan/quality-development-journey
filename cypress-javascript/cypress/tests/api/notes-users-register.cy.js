describe('Notes Users Register API', () => {

    const apiUrl = `${Cypress.env('apiBaseUrl')}/users/register`;

    it('should register a new user successfully', () => {
        const suffix = Date.now();
        const requestBody = {
            name: `testuser-${suffix}`,
            email: `testuser-${suffix}@example.com`,
            password: 'Test@1234'
        };

        cy.request({
            method: 'POST',
            url: apiUrl,
            body: requestBody,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((response) => {
            expect(response.status).to.eq(201);
            expect(response.body).to.have.property('success', true);
            expect(response.body).to.have.property('status', 201);
            expect(response.body).to.have.property('message', 'User account created successfully');
            expect(response.body).to.have.property('data');
            expect(response.body.data).to.have.property('id').that.is.a('string');
            expect(response.body.data).to.have.property('name', requestBody.name);
            expect(response.body.data).to.have.property('email', requestBody.email);
        });
    });
});