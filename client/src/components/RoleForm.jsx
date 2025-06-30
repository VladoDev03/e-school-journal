import { useState } from "react";
import {
  addTeacherRole
} from "../services/userService";
import QualificationForm from "./QualificationForm";
import "../school.css";

const RoleForm = ({ user, roles, onRoleAssigned }) => {
  const [selectedRole, setSelectedRole] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [showQualificationForm, setShowQualificationForm] = useState(false);

  const handleRoleAssign = async () => {
    if (!selectedRole || !user) return;
    setErrorMessage("");
    setSuccessMessage("");
    try {
      if (selectedRole === "teacher") {
        await addTeacherRole(user.id);
        setSuccessMessage("Teacher role assigned successfully.");
        onRoleAssigned();
      }
    } catch (error) {
      setErrorMessage("Failed to assign role.");
    }
  };

  return (
    <div className="form-group">
      {errorMessage && <div className="error-message">{errorMessage}</div>}
      {successMessage && <div className="success-message">{successMessage}</div>}

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
        onClick={handleRoleAssign}
        className="submit-btn"
      >
        Assign Role
      </button>

      {selectedRole === "teacher" && (
        <button
          type="button"
          className="submit-btn"
          onClick={() => setShowQualificationForm(true)}
        >
          Add Qualification
        </button>
      )}

      {showQualificationForm && <QualificationForm userId={user.id} />}
    </div>
  );
};

export default RoleForm;
