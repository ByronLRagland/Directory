const DeleteAgent = (agentId, agents, endpoint, getFieldAgents) => {
  const agent = agents.find((agent) => agent.agentId === agentId);
  if (
    window.confirm(
      `Are you sure you want to delete ${agent.firstName} ${agent.middleName} ${agent.lastName}?`
    )
  ) {
    const init = {
      method: "DELETE",
    };

    fetch(`${endpoint}/${agentId}`, init)
      .then((response) => {
        if (response.status === 204) {
          getFieldAgents();
        } else {
          return Promise.reject(`Unexpected status code: ${response.status}`);
        }
      })
      .catch(console.log);
  }
};

export default DeleteAgent;
