import { useState } from "react";
import { getUserByEmail, getNotAssignedRoles, addTeacherRole } from "../services/userService";
import "../role.css";
import { useNavigate } from "react-router-dom";

const RoleManagement = () => {
  const [email, setEmail] = useState("");
  const [user, setUser] = useState(null);
  const [roles, setRoles] = useState([]);
  const [selectedRole, setSelectedRole] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const navigate = useNavigate();

  const handleSearch = async () => {
    setErrorMessage("");
    setSuccessMessage("");
    setUser(null);
    setRoles([]);
    setSelectedRole("");

    try {
      const userData = await getUserByEmail(email);
      setUser(userData);
      const possibleRoles = await getNotAssignedRoles(userData.id);
      setRoles(possibleRoles);
    } catch (error) {
      setErrorMessage("User not found or error fetching roles.");
    }
  };

  const handleAssignRole = async () => {
    setErrorMessage("");
    setSuccessMessage("");

    if (!user || !selectedRole) {
      setErrorMessage("Please select a role before assigning.");
      return;
    }

    try {
      if (selectedRole === "teacher") {
        await addTeacherRole(user.id);
        navigate(`/assign-qualification/${user.id}`);
      } else {
        setErrorMessage("Currently only 'teacher' role is supported.");
      }
    } catch {
      setErrorMessage("Failed to assign role.");
    }
  };

  return (
    <div className="school-management-container">
      <div className="school-form-container">
        <h2>Role Management</h2>

        {errorMessage && <div className="error-message">{errorMessage}</div>}
        {successMessage && <div className="success-message">{successMessage}</div>}

        <div className="form-group">
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <button type="button" onClick={handleSearch} className="submit-btn">
            Search User
          </button>
        </div>

        {user && roles.length > 0 && (
          <div className="form-group">
            <label>Select Role:</label>
            <select
              value={selectedRole}
              onChange={(e) => setSelectedRole(e.target.value)}
            >
              <option value="">-- Select a role --</option>
              {roles.map((role) => (
                <option key={role.id} value={role.name}>
                  {role.name}
                </option>
              ))}
            </select>
            <button
              type="button"
              className="submit-btn"
              onClick={handleAssignRole}
              disabled={!selectedRole}
              style={{ marginTop: "10px" }}
            >
              Assign Role
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default RoleManagement;
