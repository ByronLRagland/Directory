const AddAgent = (agent, endpoint, navigate, setErrors, resetState) => {
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(agent),
  };
  fetch(endpoint, init)
    .then((response) => {
      if (response.status === 201 || response.status === 400) {
        return response.json();
      } else {
        return Promise.reject(`Unexpected status code: ${response.status}`);
      }
    })
    .then((data) => {
      if (data.agentId) {
        navigate("/agents");
        resetState();
      } else {
        setErrors(data);
      }
    })
    .catch((error) => console.log(error));
};

export default AddAgent;
