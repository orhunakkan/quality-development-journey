/// <reference types="cypress" />

describe('Notes Notes API Flow', () => {
  const registerUrl = `${Cypress.env('API_URL')}/users/register`;
  const loginUrl = `${Cypress.env('API_URL')}/users/login`;
  const notesUrl = `${Cypress.env('API_URL')}/notes`;

  let registeredUser = {};
  let authToken = '';
  let createdNoteId = '';

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

  it('should create a note with title, description, and category using auth token', () => {
    const notePayload = {
      title: 'Home Note Title',
      description: 'Home API note description',
      category: 'Home',
    };

    cy.request({
      method: 'POST',
      url: notesUrl,
      body: notePayload,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'Note successfully created');
      expect(response.body).to.have.property('data');
      expect(response.body.data).to.have.property('id').that.is.a('string');
      expect(response.body.data).to.have.property('title', notePayload.title);
      expect(response.body.data).to.have.property('description', notePayload.description);
      expect(response.body.data).to.have.property('category', notePayload.category);
      expect(response.body.data).to.have.property('completed', false);
      expect(response.body.data).to.have.property('created_at').that.is.a('string');
      expect(response.body.data).to.have.property('updated_at').that.is.a('string');
      expect(response.body.data).to.have.property('user_id').that.is.a('string');

      createdNoteId = response.body.data.id;
    });
  });

  it('should create a note with title, description, and category using auth token', () => {
    const notePayload = {
      title: 'Work Note Title',
      description: 'Work API note description',
      category: 'Work',
    };

    cy.request({
      method: 'POST',
      url: notesUrl,
      body: notePayload,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'Note successfully created');
      expect(response.body).to.have.property('data');
      expect(response.body.data).to.have.property('id').that.is.a('string');
      expect(response.body.data).to.have.property('title', notePayload.title);
      expect(response.body.data).to.have.property('description', notePayload.description);
      expect(response.body.data).to.have.property('category', notePayload.category);
      expect(response.body.data).to.have.property('completed', false);
      expect(response.body.data).to.have.property('created_at').that.is.a('string');
      expect(response.body.data).to.have.property('updated_at').that.is.a('string');
      expect(response.body.data).to.have.property('user_id').that.is.a('string');
    });
  });

  it('should retrieve note by ID with auth token', () => {
    cy.request({
      method: 'GET',
      url: `${notesUrl}/${createdNoteId}`,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'Note successfully retrieved');
      expect(response.body).to.have.property('data');
      expect(response.body.data).to.have.property('id', createdNoteId);
      expect(response.body.data).to.have.property('title', 'Home Note Title');
      expect(response.body.data).to.have.property('description', 'Home API note description');
      expect(response.body.data).to.have.property('category', 'Home');
      expect(response.body.data).to.have.property('completed', false);
      expect(response.body.data).to.have.property('created_at').that.is.a('string');
      expect(response.body.data).to.have.property('updated_at').that.is.a('string');
      expect(response.body.data).to.have.property('user_id').that.is.a('string');
    });
  });

  it('should update note by ID with auth token', () => {
    const updatePayload = {
      title: 'Updated Home Note Title',
      description: 'Updated Home API note description',
      completed: true,
      category: 'Personal',
    };

    cy.request({
      method: 'PUT',
      url: `${notesUrl}/${createdNoteId}`,
      body: updatePayload,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'Note successfully Updated');
      expect(response.body).to.have.property('data');
      expect(response.body.data).to.have.property('id', createdNoteId);
      expect(response.body.data).to.have.property('title', updatePayload.title);
      expect(response.body.data).to.have.property('description', updatePayload.description);
      expect(response.body.data).to.have.property('category', updatePayload.category);
      expect(response.body.data).to.have.property('completed', updatePayload.completed);
      expect(response.body.data).to.have.property('created_at').that.is.a('string');
      expect(response.body.data).to.have.property('updated_at').that.is.a('string');
      expect(response.body.data).to.have.property('user_id').that.is.a('string');
    });
  });

  it('should retrieve all notes with auth token', () => {
    cy.request({
      method: 'GET',
      url: notesUrl,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'Notes successfully retrieved');
      expect(response.body).to.have.property('data').that.is.an('array').with.lengthOf(2);

      const notes = response.body.data;

      const personalNote = notes.find((note) => note.category === 'Personal');
      expect(personalNote).to.exist;
      expect(personalNote).to.have.property('id').that.is.a('string');
      expect(personalNote).to.have.property('title', 'Updated Home Note Title');
      expect(personalNote).to.have.property('description', 'Updated Home API note description');
      expect(personalNote).to.have.property('completed', true);
      expect(personalNote).to.have.property('created_at').that.is.a('string');
      expect(personalNote).to.have.property('updated_at').that.is.a('string');
      expect(personalNote).to.have.property('user_id').that.is.a('string');

      const workNote = notes.find((note) => note.category === 'Work');
      expect(workNote).to.exist;
      expect(workNote).to.have.property('id').that.is.a('string');
      expect(workNote).to.have.property('title', 'Work Note Title');
      expect(workNote).to.have.property('description', 'Work API note description');
      expect(workNote).to.have.property('completed', false);
      expect(workNote).to.have.property('created_at').that.is.a('string');
      expect(workNote).to.have.property('updated_at').that.is.a('string');
      expect(workNote).to.have.property('user_id').that.is.a('string');
    });
  });

  it('should delete note by ID with auth token', () => {
    cy.request({
      method: 'DELETE',
      url: `${notesUrl}/${createdNoteId}`,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    }).then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.have.property('success', true);
      expect(response.body).to.have.property('message', 'Note successfully deleted');
    });
  });
});
