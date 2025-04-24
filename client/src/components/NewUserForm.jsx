import React, { useState, useEffect } from "react";
import { createUser, getRoles, addRolesToUser, getUserByEmail } from "../services/authService";

const NewUserForm = () => {
    const [roles, setRoles] = useState([]);
    const [selectedRoles, setSelectedRoles] = useState([]);
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
    });

    useEffect(() => {
        const fetchRoles = async () => {
            try {
                const res = await getRoles();
                setRoles(res);
            } catch (err) {
                console.error("Failed to fetch roles", err);
            }
        };

        fetchRoles();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleRoleChange = (e) => {
        const { value, checked } = e.target;
        const role = roles.find(r => r.name === value);

        setSelectedRoles(prev =>
            checked
                ? [...prev, role]
                : prev.filter(r => r.id !== role.id)
        );
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const userData = {
            credentials: [
                {
                    temporary: false,
                    type: "password",
                    value: formData.password
                }
            ],
            firstName: formData.firstName,
            lastName: formData.lastName,
            email: formData.email,
            emailVerified: true,
            enabled: true,
        };

        try {
            await createUser(userData);
            const resultUser = await getUserByEmail(userData.email);
            await addRolesToUser(resultUser[0].id, selectedRoles);
            alert("User created successfully!");
        } catch (error) {
            console.error("Error creating user:", error);
            alert("Failed to create user.");
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Select Roles:</label>
                <div>
                    {roles.map(role => (
                        <div key={role.id}>
                            <input
                                type="checkbox"
                                id={role.name}
                                value={role.name}
                                checked={selectedRoles.some(selected => selected.id === role.id)}
                                onChange={handleRoleChange}
                            />
                            <label htmlFor={role.name}>{role.name}</label>
                        </div>
                    ))}
                </div>
            </div>
            <div>
                <label htmlFor="firstName">First Name:</label>
                <input
                    type="text"
                    id="firstName"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="lastName">Last Name:</label>
                <input
                    type="text"
                    id="lastName"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="email">Email:</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />
            </div>
            <button type="submit">Create User</button>
        </form>
    );
};

export default NewUserForm;
