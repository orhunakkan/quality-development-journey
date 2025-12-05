describe('Notes Health Check API', () => {

  const apiUrl = 'https://practice.expandtesting.com/notes/api/health-check';

  it('should return a successful health check response', () => {
    cy.request({
      method: 'GET',
      url: apiUrl,
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'Notes API is Running');
    });
  });
});
