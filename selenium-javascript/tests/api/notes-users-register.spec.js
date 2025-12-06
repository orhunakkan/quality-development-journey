import axios from 'axios';
import { expect } from 'chai';

describe('Notes Users Register API', () => {

    const apiUrl = `${process.env.API_URL}/users/register`;

    it('should register a new user successfully', async () => {
        const suffix = Date.now();
        const requestBody = {
            name: `testuser-${suffix}`,
            email: `testuser-${suffix}@example.com`,
            password: 'Test@1234'
        };

        const response = await axios.post(apiUrl, requestBody, {
            headers: {
                'Content-Type': 'application/json'
            }
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
});