import { test, expect } from '@playwright/test';

test.describe('Notes Users Register and Login API', () => {
  const registerUrl = `${process.env.API_URL}/users/register`;
  const loginUrl = `${process.env.API_URL}/users/login`;
  const notesUrl = `${process.env.API_URL}/notes`;
  const profileUrl = `${process.env.API_URL}/users/profile`;
  const forgotPasswordUrl = `${process.env.API_URL}/users/forgot-password`;
  const logoutUrl = `${process.env.API_URL}/users/logout`;
  const deleteAccountUrl = `${process.env.API_URL}/users/delete-account`;

  let registeredUser: { name: string; email: string; password: string } = {
    name: '',
    email: '',
    password: '',
  };
  let authToken = '';
  let createdNoteId = '';

  test.describe.configure({ mode: 'serial' });

  test('should register a new user successfully', async ({ request }) => {
    const suffix = Date.now();
    const requestBody = {
      name: `testuser-${suffix}`,
      email: `testuser-${suffix}@example.com`,
      password: 'Test@1234',
    };

    registeredUser = requestBody;

    const response = await request.post(registerUrl, {
      data: requestBody,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const responseBody = await response.json();
    expect(response.status()).toBe(201);
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('status', 201);
    expect(responseBody).toHaveProperty('message', 'User account created successfully');
    expect(responseBody).toHaveProperty('data');
    expect(responseBody.data).toHaveProperty('id');
    expect(typeof responseBody.data.id).toBe('string');
    expect(responseBody.data).toHaveProperty('name', requestBody.name);
    expect(responseBody.data).toHaveProperty('email', requestBody.email);
  });

  test('should login with the registered user successfully', async ({ request }) => {
    const loginBody = {
      email: registeredUser.email,
      password: registeredUser.password,
    };

    const response = await request.post(loginUrl, {
      data: loginBody,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const responseBody = await response.json();
    expect(response.status()).toBe(200);
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('status', 200);
    expect(responseBody).toHaveProperty('message', 'Login successful');
    expect(responseBody).toHaveProperty('data');
    expect(responseBody.data).toHaveProperty('id');
    expect(typeof responseBody.data.id).toBe('string');
    expect(responseBody.data).toHaveProperty('name', registeredUser.name);
    expect(responseBody.data).toHaveProperty('email', registeredUser.email);
    expect(responseBody.data).toHaveProperty('token');
    expect(typeof responseBody.data.token).toBe('string');

    authToken = responseBody.data.token;
  });

  test('should create a note with title, description, and category using auth token', async ({ request }) => {
    const notePayload = {
      title: 'Home Note Title',
      description: 'Home API note description',
      category: 'Home',
    };

    const response = await request.post(notesUrl, {
      data: notePayload,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    const responseBody = await response.json();
    expect(response.status()).toBe(200);
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('message', 'Note successfully created');
    expect(responseBody).toHaveProperty('data');
    expect(responseBody.data).toHaveProperty('id');
    expect(typeof responseBody.data.id).toBe('string');
    expect(responseBody.data).toHaveProperty('title', notePayload.title);
    expect(responseBody.data).toHaveProperty('description', notePayload.description);
    expect(responseBody.data).toHaveProperty('category', notePayload.category);
    expect(responseBody.data).toHaveProperty('completed', false);
    expect(responseBody.data).toHaveProperty('created_at');
    expect(typeof responseBody.data.created_at).toBe('string');
    expect(responseBody.data).toHaveProperty('updated_at');
    expect(typeof responseBody.data.updated_at).toBe('string');
    expect(responseBody.data).toHaveProperty('user_id');
    expect(typeof responseBody.data.user_id).toBe('string');

    createdNoteId = responseBody.data.id;
  });

  test('should create a second note with title, description, and category using auth token', async ({ request }) => {
    const notePayload = {
      title: 'Work Note Title',
      description: 'Work API note description',
      category: 'Work',
    };

    const response = await request.post(notesUrl, {
      data: notePayload,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    const responseBody = await response.json();
    expect(response.status()).toBe(200);
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('message', 'Note successfully created');
    expect(responseBody).toHaveProperty('data');
    expect(responseBody.data).toHaveProperty('id');
    expect(typeof responseBody.data.id).toBe('string');
    expect(responseBody.data).toHaveProperty('title', notePayload.title);
    expect(responseBody.data).toHaveProperty('description', notePayload.description);
    expect(responseBody.data).toHaveProperty('category', notePayload.category);
    expect(responseBody.data).toHaveProperty('completed', false);
    expect(responseBody.data).toHaveProperty('created_at');
    expect(typeof responseBody.data.created_at).toBe('string');
    expect(responseBody.data).toHaveProperty('updated_at');
    expect(typeof responseBody.data.updated_at).toBe('string');
    expect(responseBody.data).toHaveProperty('user_id');
    expect(typeof responseBody.data.user_id).toBe('string');
  });

  test('should retrieve note by ID with auth token', async ({ request }) => {
    const response = await request.get(`${notesUrl}/${createdNoteId}`, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    const responseBody = await response.json();
    expect(response.status()).toBe(200);
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('message', 'Note successfully retrieved');
    expect(responseBody).toHaveProperty('data');
    expect(responseBody.data).toHaveProperty('id', createdNoteId);
    expect(responseBody.data).toHaveProperty('title', 'Home Note Title');
    expect(responseBody.data).toHaveProperty('description', 'Home API note description');
    expect(responseBody.data).toHaveProperty('category', 'Home');
    expect(responseBody.data).toHaveProperty('completed', false);
    expect(responseBody.data).toHaveProperty('created_at');
    expect(typeof responseBody.data.created_at).toBe('string');
    expect(responseBody.data).toHaveProperty('updated_at');
    expect(typeof responseBody.data.updated_at).toBe('string');
    expect(responseBody.data).toHaveProperty('user_id');
    expect(typeof responseBody.data.user_id).toBe('string');
  });

  test('should update note by ID with auth token', async ({ request }) => {
    const updatePayload = {
      title: 'Updated Home Note Title',
      description: 'Updated Home API note description',
      completed: true,
      category: 'Personal',
    };

    const response = await request.put(`${notesUrl}/${createdNoteId}`, {
      data: updatePayload,
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    const responseBody = await response.json();
    expect(response.status()).toBe(200);
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('message', 'Note successfully Updated');
    expect(responseBody).toHaveProperty('data');
    expect(responseBody.data).toHaveProperty('id', createdNoteId);
    expect(responseBody.data).toHaveProperty('title', updatePayload.title);
    expect(responseBody.data).toHaveProperty('description', updatePayload.description);
    expect(responseBody.data).toHaveProperty('category', updatePayload.category);
    expect(responseBody.data).toHaveProperty('completed', updatePayload.completed);
    expect(responseBody.data).toHaveProperty('created_at');
    expect(typeof responseBody.data.created_at).toBe('string');
    expect(responseBody.data).toHaveProperty('updated_at');
    expect(typeof responseBody.data.updated_at).toBe('string');
    expect(responseBody.data).toHaveProperty('user_id');
    expect(typeof responseBody.data.user_id).toBe('string');
  });

  test('should retrieve all notes with auth token', async ({ request }) => {
    const response = await request.get(notesUrl, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    const responseBody = await response.json();
    expect(response.status()).toBe(200);
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('message', 'Notes successfully retrieved');
    expect(responseBody).toHaveProperty('data');
    expect(Array.isArray(responseBody.data)).toBe(true);
    expect(responseBody.data).toHaveLength(2);

    const notes = responseBody.data;

    const personalNote = notes.find((note: any) => note.category === 'Personal');
    expect(personalNote).toBeDefined();
    expect(personalNote).toHaveProperty('id');
    expect(typeof personalNote.id).toBe('string');
    expect(personalNote).toHaveProperty('title', 'Updated Home Note Title');
    expect(personalNote).toHaveProperty('description', 'Updated Home API note description');
    expect(personalNote).toHaveProperty('completed', true);
    expect(personalNote).toHaveProperty('created_at');
    expect(typeof personalNote.created_at).toBe('string');
    expect(personalNote).toHaveProperty('updated_at');
    expect(typeof personalNote.updated_at).toBe('string');
    expect(personalNote).toHaveProperty('user_id');
    expect(typeof personalNote.user_id).toBe('string');

    const workNote = notes.find((note: any) => note.category === 'Work');
    expect(workNote).toBeDefined();
    expect(workNote).toHaveProperty('id');
    expect(typeof workNote.id).toBe('string');
    expect(workNote).toHaveProperty('title', 'Work Note Title');
    expect(workNote).toHaveProperty('description', 'Work API note description');
    expect(workNote).toHaveProperty('completed', false);
    expect(workNote).toHaveProperty('created_at');
    expect(typeof workNote.created_at).toBe('string');
    expect(workNote).toHaveProperty('updated_at');
    expect(typeof workNote.updated_at).toBe('string');
    expect(workNote).toHaveProperty('user_id');
    expect(typeof workNote.user_id).toBe('string');
  });

  test('should delete note by ID with auth token', async ({ request }) => {
    const response = await request.delete(`${notesUrl}/${createdNoteId}`, {
      headers: {
        'Content-Type': 'application/json',
        'x-auth-token': authToken,
      },
    });

    const responseBody = await response.json();
    expect(response.status()).toBe(200);
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('message', 'Note successfully deleted');
  });
});
