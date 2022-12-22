import FieldAgentList from "./components/FieldAgentList";
import FieldAgentForm from "./components/FieldAgentForm";
import Home from "./components/Home";
import Navigation from "./components/Navigation";
import NotFound from "./components/NotFound";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

function App() {
  return (
    <Router>
      <h1>Welcome To Field Agents</h1>
      <Navigation />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/agents" element={<FieldAgentList />} />
        <Route path="/agents/add" element={<FieldAgentForm />} />
        <Route path="/agents/edit/:id" element={<FieldAgentForm />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
