import React, { useState, useEffect } from 'react';
import '../parent.css';
import {
  getAllDirectors,
  createDirector,
} from '../services/directorService';
import { getAllSchools } from '../services/schoolService';

const DirectorManagement = () => {
  const [directors, setDirectors] = useState([]);
  const [schools, setSchools] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editingDirector, setEditingDirector] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');
  const [loading, setLoading] = useState(false);
  const [submitting, setSubmitting] = useState(false);

  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    username: '',
    password: '',
    schoolId: ''
  });

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    setLoading(true);
    try {
      const [directorData, schoolData] = await Promise.all([
        getAllDirectors(),
        getAllSchools()
      ]);
      console.log(directorData)
      setDirectors(directorData);
      setSchools(schoolData);
    } catch (error) {
      console.error('Error loading data:', error);
      setErrorMessage('Failed to load data.');
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
      schoolId: ''
    });
    setEditingDirector(null);
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
      schoolId: formData.schoolId
    };
  };

  const handleSubmit = async () => {
    if (!formData.firstName || !formData.lastName || !formData.schoolId || !formData.username || !formData.email || (!editingDirector && !formData.password)) {
      setErrorMessage('Please fill in all required fields!');
      return;
    }

    setSubmitting(true);
    setErrorMessage('');

    try {
      const body = buildRequestBody();
      console.log(body);
      if (editingDirector) {
        await updateDirector(editingDirector.id, body);
      } else {
        await createDirector(body);
      }
      await loadData();
      resetForm();
    } catch (error) {
      console.error('Error saving director:', error);
      setErrorMessage('Failed to save director.');
    } finally {
      setSubmitting(false);
    }
  };

  const handleEdit = (director) => {
    setFormData({
      firstName: director.firstName,
      lastName: director.lastName,
      email: director.email,
      username: director.username,
      password: '',
      schoolId: director.schoolId || ''
    });
    setEditingDirector(director);
    setShowForm(true);
    setErrorMessage('');
  };

  const handleDelete = async (directorId) => {
    if (window.confirm('Are you sure you want to delete this director?')) {
      try {
        await deleteDirectorById(directorId);
        await loadData();
      } catch (error) {
        console.error('Error deleting director:', error);
        setErrorMessage('Failed to delete director.');
      }
    }
  };

  const getSchoolName = (schoolId) => {
    const school = schools.find((s) => s.id === schoolId);
    return school ? school.name : 'Unknown';
  };

  return (
    <div className="parent-management-container">
      <div className="main-content">
        <div className="header">
          <h1>Director Management</h1>
          <button onClick={() => setShowForm(true)} className="add-btn">
            Add Director
          </button>
        </div>

        {showForm && (
          <div className="modal-overlay">
            <div className="modal-content">
              <div className="modal-header">
                <h2 className="modal-title">
                  {editingDirector ? 'Edit Director' : 'Add Director'}
                </h2>
                <button onClick={resetForm} className="close-btn" aria-label="Close form">
                  &#x2715;
                </button>
              </div>

              {errorMessage && <div className="error-message">{errorMessage}</div>}

              <div className="form-container">
                {['firstName', 'lastName', 'username', 'email'].map((field) => (
                  <div className="form-group" key={field}>
                    <label className="form-label">{field.replace(/^\w/, c => c.toUpperCase())}</label>
                    <input
                      type={field === 'email' ? 'email' : 'text'}
                      name={field}
                      value={formData[field]}
                      onChange={handleInputChange}
                      className="form-input"
                      placeholder={field}
                      required
                    />
                  </div>
                ))}

                <div className="form-group">
                  <label className="form-label">Password</label>
                  <input
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="Password"
                    required={!editingDirector}
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">School</label>
                  <select
                    name="schoolId"
                    value={formData.schoolId}
                    onChange={handleInputChange}
                    className="form-input"
                    required
                  >
                    <option value="">-- Select School --</option>
                    {schools.map((school) => (
                      <option key={school.id} value={school.id}>
                        {school.name}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="form-actions">
                  <button
                    onClick={handleSubmit}
                    className="save-btn"
                    disabled={submitting}
                  >
                    {submitting ? 'Saving...' : editingDirector ? 'Save changes' : 'Add director'}
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
                  <th>Username</th>
                  <th>School</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {directors.map((director) => (
                  <tr key={director.id}>
                    <td>{director.firstName} {director.lastName}</td>
                    <td>{director.email}</td>
                    <td>{director.username}</td>
                    <td>{getSchoolName(director.schoolId)}</td>
                    <td>
                      <button
                        onClick={() => handleEdit(director)}
                        className="edit-btn"
                        title="Edit"
                      >
                        ✎
                      </button>
                      <button
                        onClick={() => handleDelete(director.id)}
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

export default DirectorManagement;
