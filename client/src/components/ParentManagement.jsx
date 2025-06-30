import React, { useState, useEffect, useCallback } from 'react';
import '../parent.css';
import {
  getAllParents,
  createParent,
  updateParent,
  deleteParentById,
} from '../services/parentService';

const ParentManagement = () => {
  const [parents, setParents] = useState([]);
  const [students, setStudents] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editingParent, setEditingParent] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');
  const [loading, setLoading] = useState(false);
  const [submitting, setSubmitting] = useState(false);

  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    username: '',
    password: '',
    studentEmail: ''
  });

  useEffect(() => {
    loadParents();
  }, []);

  const loadParents = async () => {
    setLoading(true);
    try {
      const data = await getAllParents();
      setParents(data);
    } catch (error) {
      console.error('Error loading parents:', error);
      setErrorMessage('Failed to load parents.');
    } finally {
      setLoading(false);
    }
  };

  const resetForm = () => {
    setFormData({
      firstName: '',
      lastName: '',
      email: '',
      username: '',
      password: '',
      studentEmail: ''
    });
    setEditingParent(null);
    setShowForm(false);
    setErrorMessage('');
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const buildRequestBody = () => {
    return {
      createUserDto: {
        credentials: { type: 'password', value: formData.password },
        username: formData.username,
        firstName: formData.firstName,
        lastName: formData.lastName,
        email: formData.email
      },
      studentEmail: formData.studentEmail
    };
  };

  const handleSubmit = async () => {
    if (!formData.firstName || !formData.lastName || !formData.studentEmail || !formData.username || !formData.email || !formData.password) {
      setErrorMessage('Please fill in all required fields!');
      return;
    }

    setSubmitting(true);
    setErrorMessage('');

    try {
      const body = buildRequestBody();
      if (editingParent) {
        await updateParent(editingParent.id, body);
      } else {
        await createParent(body);
      }
      await loadParents();
      resetForm();
    } catch (error) {
      console.error('Error saving parent:', error);
      setErrorMessage('Failed to save parent.');
    } finally {
      setSubmitting(false);
    }
  };

  const handleEdit = (parent) => {
    setFormData({
      firstName: parent.firstName,
      lastName: parent.lastName,
      email: parent.email,
      username: parent.username,
      password: '',
      studentEmail: parent.studentEmail || ''
    });
    setEditingParent(parent);
    setShowForm(true);
    setErrorMessage('');
  };

  const handleDelete = async (parentId) => {
    if (window.confirm('Are you sure you want to delete this parent?')) {
      try {
        await deleteParentById(parentId);
        await loadParents();
      } catch (error) {
        console.error('Error deleting parent:', error);
        setErrorMessage('Failed to delete parent.');
      }
    }
  };

  const getStudentNames = useCallback((childrenIds) => {
    return childrenIds
      .map((id) => students.find((student) => student.id === id)?.name)
      .filter(Boolean)
      .join(', ');
  }, [students]);

  return (
    <div className="parent-management-container">
      <div className="main-content">
        <div className="header">
          <h1>Parent Management</h1>
          <button onClick={() => setShowForm(true)} className="add-btn">
            Add Parent
          </button>
        </div>

        {showForm && (
          <div className="modal-overlay">
            <div className="modal-content">
              <div className="modal-header">
                <h2 className="modal-title">
                  {editingParent ? 'Edit Parent' : 'Add Parent'}
                </h2>
                <button onClick={resetForm} className="close-btn" aria-label="Close form">
                  &#x2715;
                </button>
              </div>

              {errorMessage && <div className="error-message">{errorMessage}</div>}

              <div className="form-container">
                <div className="form-group">
                  <label className="form-label">First name</label>
                  <input
                    type="text"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="First name"
                    required
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Last name</label>
                  <input
                    type="text"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="Last name"
                    required
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Username</label>
                  <input
                    type="text"
                    name="username"
                    value={formData.username}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="Username"
                    required
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Email</label>
                  <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="Email"
                    required
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Password</label>
                  <input
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="Password"
                    required={!editingParent}
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Student Email</label>
                  <input
                    type="email"
                    name="studentEmail"
                    value={formData.studentEmail}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="Student Email"
                    required
                  />
                </div>

                <div className="form-actions">
                  <button
                    onClick={handleSubmit}
                    className="save-btn"
                    disabled={submitting}
                  >
                    {submitting ? 'Saving...' : editingParent ? 'Save changes' : 'Add parent'}
                  </button>
                  <button onClick={resetForm} className="cancel-btn">
                    &#x2715; Cancel
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}

        {errorMessage && !showForm && <div className="error-message">{errorMessage}</div>}

        <div className="table-container">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <table className="parent-table">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Students</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {parents.map((parent) => (
                  <tr key={parent.id}>
                    <td>{parent.firstName} {parent.lastName}</td>
                    <td>{parent.email}</td>
                    <td>{getStudentNames(parent.childrenIds)}</td>
                    <td>
                      <button
                        onClick={() => handleEdit(parent)}
                        className="edit-btn"
                        title="Edit"
                      >
                        ✎
                      </button>
                      <button
                        onClick={() => handleDelete(parent.id)}
                        className="delete-btn"
                        title="Delete"
                      >
                        🗑
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
};

export default ParentManagement;
