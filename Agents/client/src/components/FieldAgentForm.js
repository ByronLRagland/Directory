import { useEffect, useState } from "react";
import { useNavigate, Link, useParams } from "react-router-dom";
import AddAgent from "./AddAgent";
import UpdateAgent from "./UpdateAgent";

const FIELD_AGENT_DEFAULT = {
  firstName: "",
  middleName: "",
  lastName: "",
  dob: "",
  heightInInches: "",
};

function FieldAgentForm() {
  const endpoint = "http://localhost:8080/api/agent";
  const [agent, setAgent] = useState(FIELD_AGENT_DEFAULT);
  const [editAgentId, setEditAgentId] = useState(0);
  const [errors, setErrors] = useState([]);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      setEditAgentId(id);
      fetch(`${endpoint}/${id}`)
        .then((res) => res.json())
        .then((data) => setAgent(data));
    }
  }, []);

  const handleChange = (event) => {
    const newAgent = { ...agent };
    newAgent[event.target.name] = event.target.value;
    setAgent(newAgent);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (editAgentId === 0) {
      AddAgent(agent, endpoint, navigate, setErrors, resetState);
    } else {
      UpdateAgent(
        agent,
        editAgentId,
        endpoint,
        navigate,
        setErrors,
        resetState
      );
    }
  };

  const resetState = () => {
    setAgent(FIELD_AGENT_DEFAULT);
    setEditAgentId(0);
    setErrors([]);
  };

  return (
    <>
      <h2>{editAgentId > 0 ? "Update Field Agent" : "Add Field Agent"}</h2>

      {errors.length > 0 && (
        <div>
          <h3>The following errors occured: </h3>
          <ul>
            {errors.map((error) => {
              return <li>{error}</li>;
            })}
          </ul>
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="firstName">First Name:</label>
          <input
            id="firstName"
            placeholder="John"
            type="text"
            name="firstName"
            className="form-control"
            value={agent.firstName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="middleName">Middle Initial:</label>
          <input
            id="middleName"
            placeholder="A"
            type="text"
            name="middleName"
            className="form-control"
            value={agent.middleName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last Name:</label>
          <input
            id="lastName"
            placeholder="Doe"
            type="text"
            name="lastName"
            className="form-control"
            value={agent.lastName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="dob">Date-of-Birth:</label>
          <input
            id="dob"
            placeholder="YYYY-MM-DD"
            type="text"
            name="dob"
            className="form-control"
            value={agent.dob}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="heightInInches">Height In Inches:</label>
          <input
            id="heightInInches"
            placeholder="50"
            type="text"
            name="heightInInches"
            className="form-control"
            value={agent.heightInInches}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mt-4">
          <button className="btn btn-success mr-4" type="submit">
            {editAgentId > 0 ? "Update Field Agent" : "Add Field Agent"}
          </button>
          <Link className="btn btn-warning mb-0" to="/agents">
            Cancel
          </Link>
        </div>
      </form>
    </>
  );
}

export default FieldAgentForm;
