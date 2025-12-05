import axios from 'axios';
import { expect } from 'chai';

describe('Notes Health Check API', () => {

  const apiUrl = 'https://practice.expandtesting.com/notes/api/health-check';

  it('should return a successful health check response', async () => {
    const response = await axios.get(apiUrl);
    expect(response.status).to.eq(200);
    expect(response.data).to.have.property('success', true);
    expect(response.data).to.have.property('message', 'Notes API is Running');
  });
});