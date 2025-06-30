import React, { useState } from 'react';
import {
  getUserByEmail,
  updateUser
} from '../services/userService';
import '../user.css';

const UserUpdateForm = () => {
  const [searchEmail, setSearchEmail] = useState('');
  const [user, setUser] = useState(null);
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });
  const [loading, setLoading] = useState(false);
  const [submitting, setSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const handleSearch = async () => {
    setErrorMessage('');
    setSuccessMessage('');
    setUser(null);
    setLoading(true);
    try {
      const fetchedUser = await getUserByEmail(searchEmail);
      setUser(fetchedUser);
      setFormData({
        firstName: fetchedUser.firstName,
        lastName: fetchedUser.lastName,
        email: fetchedUser.email
      });
    } catch (error) {
      setErrorMessage('User not found.');
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async () => {
    setSubmitting(true);
    setErrorMessage('');
    setSuccessMessage('');
console.log(formData);
    try {
      await updateUser(user.id, formData);
      setSuccessMessage('User updated successfully!');
    } catch (error) {
      setErrorMessage('Failed to update user.');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="user-update-form">
      <h2>Update User</h2>

      <div className="form-group">
        <label>Search by Email:</label>
        <input
          type="email"
          value={searchEmail}
          onChange={(e) => setSearchEmail(e.target.value)}
          placeholder="Enter email"
        />
        <button onClick={handleSearch} disabled={loading}>
          {loading ? 'Searching...' : 'Find User'}
        </button>
      </div>

      {errorMessage && <div className="error-message">{errorMessage}</div>}
      {successMessage && <div className="success-message">{successMessage}</div>}

      {user && (
        <div className="form-container">
          <h3>Edit User Info</h3>

          <div className="form-group">
            <label>First Name:</label>
            <input
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleInputChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Last Name:</label>
            <input
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleInputChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleInputChange}
              required
            />
          </div>

          <button onClick={handleSubmit} disabled={submitting}>
            {submitting ? 'Updating...' : 'Update User'}
          </button>
        </div>
      )}
    </div>
  );
};

export default UserUpdateForm;
