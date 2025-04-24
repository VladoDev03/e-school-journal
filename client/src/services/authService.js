import keycloak from '../config/keycloak'

const BASE_URL = 'http://localhost:8080/admin/realms/e-school-journal';

export const createUser = async (userData) => {
    const keycloakToken = keycloak.token;

    const response = await fetch(`${BASE_URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${keycloakToken}`,
        },
        body: JSON.stringify(userData),
    });

    if (response.ok) {
        console.log('User created successfully');
    } else {
        console.error('Failed to create user', response.status, await response.text());
    }
};

export const addRolesToUser = async (userId, roles) => {
    const keycloakToken = keycloak.token;

    const response = await fetch(`${BASE_URL}/users/${userId}/role-mappings/realm`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${keycloakToken}`,
        },
        body: JSON.stringify(roles),
    });

    if (response.ok) {
        console.log('Roles added successfully');
    } else {
        console.error('Failed to add roles', response.status, await response.text());
    }
};

export const getRoles = async () => {
    const keycloakToken = keycloak.token;

    const response = await fetch(`${BASE_URL}/roles`, {
        headers: {
            'Authorization': `Bearer ${keycloakToken}`
        },
    });

    if (response.ok) {
        return await response.json();
    } else {
        console.error('Failed to get roles', response.status, await response.text());
        throw new Error('Failed to get roles');
    }
};

export const getUserByEmail = async (userEmail) => {
    const keycloakToken = keycloak.token;

    const response = await fetch(`${BASE_URL}/users?email=${userEmail}`, {
        headers: {
            'Authorization': `Bearer ${keycloakToken}`
        },
    });

    if (response.ok) {
        return await response.json();
    } else {
        console.error('Failed to get roles', response.status, await response.text());
        throw new Error('Failed to get roles');
    }
};
