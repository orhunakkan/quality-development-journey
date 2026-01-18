/// <reference types="cypress" />

describe('Notes Users API Flow', () => {
  const registerUrl = `${Cypress.env('API_URL')}/users/register`;
  const loginUrl = `${Cypress.env('API_URL')}/users/login`;
  const profileUrl = `${Cypress.env('API_URL')}/users/profile`;
  const forgotPasswordUrl = `${Cypress.env('API_URL')}/users/forgot-password`;
  const logoutUrl = `${Cypress.env('API_URL')}/users/logout`;

  let registeredUser = {};
  let authToken = '';

  it('should register a new user successfully', () => {
    const suffix = Date.now();
    const requestBody = {
      name: `testuser-${suffix}`,
      email: `testuser-${suffix}@example.com`,
      password: 'Test@1234',
    };

    registeredUser = requestBody;

    cy.request({
      method: 'POST',
      url: registerUrl,
      body: requestBody,
      headers: {
        'Content-Type': 'application/json',
      },
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

  it('should login with the registered user successfully', () => {
    const loginBody = {
      email: registeredUser.email,
      password: registeredUser.password,
    };

    cy.request({
      method: 'POST',
      url: loginUrl,
      body: loginBody,
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('status', 200);
      expect(response.body).to.have.property('message', 'Login successful');
      expect(response.body).to.have.property('data');
      expect(response.body.data).to.have.property('id').that.is.a('string');
      expect(response.body.data).to.have.property('name', registeredUser.name);
      expect(response.body.data).to.have.property('email', registeredUser.email);
      expect(response.body.data).to.have.property('token').that.is.a('string');

      authToken = response.body.data.token;
    });
  });

  it('should get user profile with valid token', () => {
    cy.request({
      method: 'GET',
      url: profileUrl,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'Profile successful');
      expect(response.body).to.have.property('data');
      expect(response.body.data).to.have.property('id').that.is.a('string');
      expect(response.body.data).to.have.property('name', registeredUser.name);
      expect(response.body.data).to.have.property('email', registeredUser.email);
    });
  });

  it('should update the user profile with name, phone, and company', () => {
    const updatedProfile = {
      name: `${registeredUser.name}-updated`,
      phone: '1234567890',
      company: 'Test Company',
    };

    cy.request({
      method: 'PATCH',
      url: profileUrl,
      body: updatedProfile,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('status', 200);
      expect(response.body).to.have.property('message', 'Profile updated successful');
      expect(response.body).to.have.property('data');
      expect(response.body.data).to.have.property('id').that.is.a('string');
      expect(response.body.data).to.have.property('name', updatedProfile.name);
      expect(response.body.data).to.have.property('email', registeredUser.email);
      expect(response.body.data).to.have.property('phone', updatedProfile.phone);
      expect(response.body.data).to.have.property('company', updatedProfile.company);
      registeredUser = { ...registeredUser, ...updatedProfile };
    });
  });

  it('should send forgot password email with valid email address', () => {
    const forgotPasswordBody = {
      email: registeredUser.email,
    };

    cy.request({
      method: 'POST',
      url: forgotPasswordUrl,
      body: forgotPasswordBody,
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message').that.includes('Password reset link successfully sent to');
    });
  });

  it('should logout with valid auth token', () => {
    cy.request({
      method: 'DELETE',
      url: logoutUrl,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'User has been successfully logged out');
    });
  });
});
