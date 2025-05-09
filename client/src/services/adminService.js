import keycloak from '../config/keycloak'

const BASE_URL = 'http://localhost:8081/api/admins';

export const createAdmin = async (data) => {
    const keycloakToken = keycloak.token;

    const response = await fetch(`${BASE_URL}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${keycloakToken}`,
        },
        body: JSON.stringify(data),
    });

    if (response.ok) {
        console.log('Admin created successfully');
    } else {
        console.error('Failed to create admin', response.status, await response.text());
    }
};
