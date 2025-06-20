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
    phone: '',
    keycloakId: '',
    childrenIds: [],
  });

  useEffect(() => {
    setStudents([
      { id: 1, name: 'Иван Петров' },
      { id: 2, name: 'Мария Георгиева' },
      { id: 3, name: 'Георги Димитров' },
      { id: 4, name: 'Анна Стоянова' },
      { id: 5, name: 'Петър Николов' }
    ]);
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
      phone: '',
      keycloakId: '',
      childrenIds: [],
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

  const handleChildrenChange = (studentId) => {
    setFormData((prev) => ({
      ...prev,
      childrenIds: prev.childrenIds.includes(studentId)
        ? prev.childrenIds.filter((id) => id !== studentId)
        : [...prev.childrenIds, studentId],
    }));
  };

  const handleSubmit = async () => {
    if (!formData.firstName || !formData.lastName || !formData.phone || !formData.keycloakId ) {
      setErrorMessage('Please fill in all required fields and select at least one student!');
      return;
    }

    setSubmitting(true);
    setErrorMessage('');
    try {
      if (editingParent) {
        await updateParent(editingParent.id, formData);
      } else {
        await createParent(formData);
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
      phone: parent.phone,
      keycloakId: parent.keycloakId,
      childrenIds: parent.childrenIds || [],
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
                  <label className="form-label">Phone number</label>
                  <input
                    type="tel"
                    name="phone"
                    value={formData.phone}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="Phone number"
                    required
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Keycloak ID</label>
                  <input
                    type="text"
                    name="keycloakId"
                    value={formData.keycloakId}
                    onChange={handleInputChange}
                    className="form-input"
                    placeholder="keycloak username"
                    required
                  />
                </div>

                <div className="form-group">
                  <label className="form-label">Student</label>
                  <div className="checkbox-container">
                    {students.map((student) => (
                      <label key={student.id} className="checkbox-item">
                        <input
                          type="checkbox"
                          checked={formData.childrenIds.includes(student.id)}
                          onChange={() => handleChildrenChange(student.id)}
                        />
                        {student.name}
                      </label>
                    ))}
                  </div>
                </div>

                <div className="form-actions">
                  <button
                    onClick={handleSubmit}
                    className="save-btn"
                    disabled={submitting}
                  >
                    {submitting ? 'Saving...' : editingParent ? ' Save changes' : ' Add parent'}
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
                  <th>Phone</th>
                  <th>Students</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {parents.map((parent) => (
                  <tr key={parent.id}>
                    <td>{parent.firstName} {parent.lastName}</td>
                    <td>{parent.phone}</td>
                    <td>{getStudentNames(parent.childrenIds)}</td>
                    <td>
                      <button
                        onClick={() => handleEdit(parent)}
                        className="edit-btn"
                        title="Редактирай"
                      >
                        ✎
                      </button>
                      <button
                        onClick={() => handleDelete(parent.id)}
                        className="delete-btn"
                        title="Изтрий"
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
