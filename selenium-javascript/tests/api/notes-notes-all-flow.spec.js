import axios from 'axios';
import { expect } from 'chai';
import dotenv from 'dotenv';

dotenv.config();

describe('Notes Users Register and Login API', () => {
  const registerUrl = `${process.env.API_URL}/users/register`;
  const loginUrl = `${process.env.API_URL}/users/login`;
  const notesUrl = `${process.env.API_URL}/notes`;
  const profileUrl = `${process.env.API_URL}/users/profile`;
  const forgotPasswordUrl = `${process.env.API_URL}/users/forgot-password`;
  const logoutUrl = `${process.env.API_URL}/users/logout`;
  const deleteAccountUrl = `${process.env.API_URL}/users/delete-account`;

  let registeredUser = {};
  let authToken = '';
  let createdNoteId = '';

  it('should register a new user successfully', async () => {
    const suffix = Date.now();
    const requestBody = {
      name: `testuser-${suffix}`,
      email: `testuser-${suffix}@example.com`,
      password: 'Test@1234',
    };

    registeredUser = requestBody;

    const response = await axios.post(registerUrl, requestBody, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    expect(response.status).to.equal(201);
    expect(response.data).to.haveOwnProperty('success', true);
    expect(response.data).to.haveOwnProperty('status', 201);
    expect(response.data).to.haveOwnProperty('message', 'User account created successfully');
    expect(response.data).to.haveOwnProperty('data');
    expect(response.data.data).to.haveOwnProperty('id');
    expect(typeof response.data.data.id).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('name', requestBody.name);
    expect(response.data.data).to.haveOwnProperty('email', requestBody.email);
  });

  it('should login with the registered user successfully', async () => {
    const loginBody = {
      email: registeredUser.email,
      password: registeredUser.password,
    };

    const response = await axios.post(loginUrl, loginBody, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    expect(response.status).to.equal(200);
    expect(response.data).to.haveOwnProperty('success', true);
    expect(response.data).to.haveOwnProperty('status', 200);
    expect(response.data).to.haveOwnProperty('message', 'Login successful');
    expect(response.data).to.haveOwnProperty('data');
    expect(response.data.data).to.haveOwnProperty('id');
    expect(typeof response.data.data.id).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('name', registeredUser.name);
    expect(response.data.data).to.haveOwnProperty('email', registeredUser.email);
    expect(response.data.data).to.haveOwnProperty('token');
    expect(typeof response.data.data.token).to.equal('string');

    authToken = response.data.data.token;
  });

  it('should create a note with title, description, and category using auth token', async () => {
    const notePayload = {
      title: 'Home Note Title',
      description: 'Home API note description',
      category: 'Home',
    };

    const response = await axios.post(notesUrl, notePayload, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    expect(response.status).to.equal(200);
    expect(response.data).to.haveOwnProperty('success', true);
    expect(response.data).to.haveOwnProperty('message', 'Note successfully created');
    expect(response.data).to.haveOwnProperty('data');
    expect(response.data.data).to.haveOwnProperty('id');
    expect(typeof response.data.data.id).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('title', notePayload.title);
    expect(response.data.data).to.haveOwnProperty('description', notePayload.description);
    expect(response.data.data).to.haveOwnProperty('category', notePayload.category);
    expect(response.data.data).to.haveOwnProperty('completed', false);
    expect(response.data.data).to.haveOwnProperty('created_at');
    expect(typeof response.data.data.created_at).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('updated_at');
    expect(typeof response.data.data.updated_at).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('user_id');
    expect(typeof response.data.data.user_id).to.equal('string');

    createdNoteId = response.data.data.id;
  });

  it('should create a note with title, description, and category using auth token', async () => {
    const notePayload = {
      title: 'Work Note Title',
      description: 'Work API note description',
      category: 'Work',
    };

    const response = await axios.post(notesUrl, notePayload, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    expect(response.status).to.equal(200);
    expect(response.data).to.haveOwnProperty('success', true);
    expect(response.data).to.haveOwnProperty('message', 'Note successfully created');
    expect(response.data).to.haveOwnProperty('data');
    expect(response.data.data).to.haveOwnProperty('id');
    expect(typeof response.data.data.id).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('title', notePayload.title);
    expect(response.data.data).to.haveOwnProperty('description', notePayload.description);
    expect(response.data.data).to.haveOwnProperty('category', notePayload.category);
    expect(response.data.data).to.haveOwnProperty('completed', false);
    expect(response.data.data).to.haveOwnProperty('created_at');
    expect(typeof response.data.data.created_at).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('updated_at');
    expect(typeof response.data.data.updated_at).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('user_id');
    expect(typeof response.data.data.user_id).to.equal('string');
  });

  it('should retrieve note by ID with auth token', async () => {
    const response = await axios.get(`${notesUrl}/${createdNoteId}`, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    expect(response.status).to.equal(200);
    expect(response.data).to.haveOwnProperty('success', true);
    expect(response.data).to.haveOwnProperty('message', 'Note successfully retrieved');
    expect(response.data).to.haveOwnProperty('data');
    expect(response.data.data).to.haveOwnProperty('id', createdNoteId);
    expect(response.data.data).to.haveOwnProperty('title', 'Home Note Title');
    expect(response.data.data).to.haveOwnProperty('description', 'Home API note description');
    expect(response.data.data).to.haveOwnProperty('category', 'Home');
    expect(response.data.data).to.haveOwnProperty('completed', false);
    expect(response.data.data).to.haveOwnProperty('created_at');
    expect(typeof response.data.data.created_at).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('updated_at');
    expect(typeof response.data.data.updated_at).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('user_id');
    expect(typeof response.data.data.user_id).to.equal('string');
  });

  it('should update note by ID with auth token', async () => {
    const updatePayload = {
      title: 'Updated Home Note Title',
      description: 'Updated Home API note description',
      completed: true,
      category: 'Personal',
    };

    const response = await axios.put(`${notesUrl}/${createdNoteId}`, updatePayload, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    expect(response.status).to.equal(200);
    expect(response.data).to.haveOwnProperty('success', true);
    expect(response.data).to.haveOwnProperty('message', 'Note successfully Updated');
    expect(response.data).to.haveOwnProperty('data');
    expect(response.data.data).to.haveOwnProperty('id', createdNoteId);
    expect(response.data.data).to.haveOwnProperty('title', updatePayload.title);
    expect(response.data.data).to.haveOwnProperty('description', updatePayload.description);
    expect(response.data.data).to.haveOwnProperty('category', updatePayload.category);
    expect(response.data.data).to.haveOwnProperty('completed', updatePayload.completed);
    expect(response.data.data).to.haveOwnProperty('created_at');
    expect(typeof response.data.data.created_at).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('updated_at');
    expect(typeof response.data.data.updated_at).to.equal('string');
    expect(response.data.data).to.haveOwnProperty('user_id');
    expect(typeof response.data.data.user_id).to.equal('string');
  });

  it('should retrieve all notes with auth token', async () => {
    const response = await axios.get(notesUrl, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    expect(response.status).to.equal(200);
    expect(response.data).to.haveOwnProperty('success', true);
    expect(response.data).to.haveOwnProperty('message', 'Notes successfully retrieved');
    expect(response.data).to.haveOwnProperty('data');
    expect(Array.isArray(response.data.data)).to.be.true;
    expect(response.data.data).to.have.lengthOf(2);

    const notes = response.data.data;

    const personalNote = notes.find(note => note.category === 'Personal');
    expect(personalNote).to.exist;
    expect(personalNote).to.haveOwnProperty('id');
    expect(typeof personalNote.id).to.equal('string');
    expect(personalNote).to.haveOwnProperty('title', 'Updated Home Note Title');
    expect(personalNote).to.haveOwnProperty('description', 'Updated Home API note description');
    expect(personalNote).to.haveOwnProperty('completed', true);
    expect(personalNote).to.haveOwnProperty('created_at');
    expect(typeof personalNote.created_at).to.equal('string');
    expect(personalNote).to.haveOwnProperty('updated_at');
    expect(typeof personalNote.updated_at).to.equal('string');
    expect(personalNote).to.haveOwnProperty('user_id');
    expect(typeof personalNote.user_id).to.equal('string');

    const workNote = notes.find(note => note.category === 'Work');
    expect(workNote).to.exist;
    expect(workNote).to.haveOwnProperty('id');
    expect(typeof workNote.id).to.equal('string');
    expect(workNote).to.haveOwnProperty('title', 'Work Note Title');
    expect(workNote).to.haveOwnProperty('description', 'Work API note description');
    expect(workNote).to.haveOwnProperty('completed', false);
    expect(workNote).to.haveOwnProperty('created_at');
    expect(typeof workNote.created_at).to.equal('string');
    expect(workNote).to.haveOwnProperty('updated_at');
    expect(typeof workNote.updated_at).to.equal('string');
    expect(workNote).to.haveOwnProperty('user_id');
    expect(typeof workNote.user_id).to.equal('string');
  });

  it('should delete note by ID with auth token', async () => {
    const response = await axios.delete(`${notesUrl}/${createdNoteId}`, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    expect(response.status).to.equal(200);
    expect(response.data).to.haveOwnProperty('success', true);
    expect(response.data).to.haveOwnProperty('message', 'Note successfully deleted');
  });
});
