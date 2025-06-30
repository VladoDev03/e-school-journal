import { useState } from 'react';
import { createSchool } from "../services/schoolService";
import '../school.css';

const SchoolManagement = () => {
    const [formData, setFormData] = useState({
        name: '',
        address: ''
    });
    const [submitting, setSubmitting] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!formData.name || !formData.address) {
            setErrorMessage('Please fill in all fields.');
            return;
        }

        setSubmitting(true);
        setErrorMessage('');
        setSuccessMessage('');

        try {
            const result = await createSchool(formData);
            setSuccessMessage('School created successfully!');
            setFormData({ name: '', address: '' });
        } catch (error) {
            console.error('Error creating school:', error);
            setErrorMessage('Failed to create school.');
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div className="school-management-container">
            <div className="school-form-container">
                <h2>Create School</h2>

                {errorMessage && <div className="error-message">{errorMessage}</div>}
                {successMessage && <div className="success-message">{successMessage}</div>}

                <form onSubmit={handleSubmit} className="school-form">
                    <div className="form-group">
                        <label>School Name</label>
                        <input
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label>Address</label>
                        <input
                            type="text"
                            name="address"
                            value={formData.address}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <button type="submit" disabled={submitting} className="submit-btn">
                        {submitting ? 'Creating...' : 'Create School'}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default SchoolManagement;
