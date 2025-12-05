// Create bolier plate for notes users register API test POST request
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
            expect(response.body).to.have.property('message', 'User account created successfully');
            expect(response.body).to.have.property('data');
            expect(response.body.data).to.have.property('id');
            const username = response.body.data.username || response.body.data.name;
            expect(username).to.eq(requestBody.name);
            expect(response.body.data).to.have.property('email', requestBody.email);
        });
    });
});