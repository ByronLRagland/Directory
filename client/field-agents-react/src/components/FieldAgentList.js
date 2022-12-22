import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import DeleteAgent from "./DeleteAgent";

function FieldAgentList() {
  const endpoint = "http://localhost:8080/api/agent";
  const [agents, setAgents] = useState([]);
  useEffect(() => {
    getFieldAgents();
  }, []);

  const getFieldAgents = () => {
    fetch(endpoint)
      .then((res) => {
        if (res.status === 200) {
          return res.json();
        } else {
          return Promise.reject(`Unexpected status code: ${res.status}`);
        }
      })
      .then((data) => {
        setAgents(data);
      })
      .catch(console.error);
  };

  //   const handleDeleteAgent = (agentId) => {
  //     const agent = agents.find((agent) => agent.agentId === agentId);
  //     if (
  //       window.confirm(
  //         `Are you sure you want to delete ${agent.firstName} ${agent.middleName} ${agent.lastName}?`
  //       )
  //     ) {
  //       const init = {
  //         method: "DELETE",
  //       };

  //       fetch(`${endpoint}/${agentId}`, init)
  //         .then((response) => {
  //           if (response.status === 204) {
  //             getFieldAgents();
  //           } else {
  //             return Promise.reject(`Unexpected status code: ${response.status}`);
  //           }
  //         })
  //         .catch(console.log);
  //     }
  //   };

  return (
    <>
      <h2>Agents</h2>
      <Link className="btn btn-primary" to="/agents/add">
        Add Agent
      </Link>
      <table>
        <thead>
          <tr id="columns">
            <th scope="col">First Name</th>
            <th scope="col">M.I.</th>
            <th scope="col">Last Name</th>
            <th scope="col">Date-of-Birth</th>
            <th scope="col">Height (inches)</th>
            <th scope="col">&nbsp;</th>
          </tr>
        </thead>
        <tbody id="tableRows">
          {agents.map((agent) => (
            <tr key={agent.agentId}>
              <td>{agent.firstName}</td>
              <td>{agent.middleName}</td>
              <td>{agent.lastName}</td>
              <td>{agent.dob}</td>
              <td>{agent.heightInInches}</td>
              <td className="buttonContainer">
                <Link
                  className="btn btn-primary"
                  to={`/agents/edit/${agent.agentId}`}
                >
                  Edit
                </Link>
                <button
                  className="btn btn-danger"
                  onClick={() =>
                    DeleteAgent(agent.agentId, agents, endpoint, getFieldAgents)
                  }
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default FieldAgentList;
