import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getSubjects } from "../services/subjectService";
import { addQualification } from "../services/teacherService";
import "../school.css";

const AssignQualification = () => {
    const { userId } = useParams();
    const [subjects, setSubjects] = useState([]);
    const [selectedSubject, setSelectedSubject] = useState("");
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        const loadSubjects = async () => {
            try {
                const subs = await getSubjects();
                setSubjects(subs);
            } catch {
                setMessage("Failed to load subjects.");
            }
        };

        loadSubjects();
    }, []);

    const handleSubmit = async () => {
        if (!selectedSubject) {
            setMessage("Please select a subject.");
            return;
        }

        try {
            const data = {
                teacherId: userId,
                subjectIds: [selectedSubject]
            };

            await addQualification(data);
            setMessage("Qualification successfully assigned!");
            setTimeout(() => {
                navigate("/");
            }, 2000);
        } catch {
            setMessage("Failed to assign qualification.");
        }
    };

    return (
        <div className="school-management-container">
            <div className="school-form-container">
                <h2>Assign Qualification</h2>
                {message && <div className="info-message">{message}</div>}

                <div className="form-group">
                    <label>Select Subject:</label>
                    <select
                        value={selectedSubject}
                        onChange={(e) => setSelectedSubject(e.target.value)}
                    >
                        <option value="">-- Select a subject --</option>
                        {subjects.map((subject) => (
                            <option key={subject.id} value={subject.id}>
                                {subject.name}
                            </option>
                        ))}
                    </select>
                </div>

                <button className="submit-btn" onClick={handleSubmit}>
                    Assign Qualification
                </button>
            </div>
        </div>
    );
};

export default AssignQualification;
