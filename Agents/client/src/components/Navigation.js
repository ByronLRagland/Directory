import { Link } from "react-router-dom";

const Navigation = () => {
  return (
    <nav>
      <ul className="navList">
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/agents">Display Agents</Link>
        </li>
        <li>
          <Link to="/agents/add">Add Agent</Link>
        </li>
      </ul>
    </nav>
  );
};

export default Navigation;
