let sessionToken;
const baseURL = 'https://restful-booker.herokuapp.com';

describe('restful-booker APIs', () => {
  it('should create token', () => {
    cy.request('POST', `${baseURL}/auth`, {
      "username" : "admin",
      "password" : "password123"
    }).then((response) => {
      expect(response.status).equal(200);
      sessionToken = response.body.token;
    })
  });

  it('should get Booking Ids', async () => {
    cy.request(`${baseURL}/booking`).then((response => {
      expect(response.status).equal(200);
    }))
  });
})