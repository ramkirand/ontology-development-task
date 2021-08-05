import React from "react";
import { Link, NavLink } from "react-router-dom";
const Navigation = ({ currentUser }) => {
  return (
    <nav className="navbar navbar-expand-md navbar-light bg-info">
      <Link className="navbar-brand" to="/">
        Ontology Tool
      </Link>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNavAltMarkup"
        aria-controls="navbarNavAltMarkup"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon" />
      </button>
      <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div className="navbar-nav">
          {!currentUser && (
            <React.Fragment>
              <li class="nav-item">
                <NavLink clasName="nav-link" to="/login">
                  Login
                </NavLink>
              </li>
              <li class="nav-item">
                <NavLink clasName="nav-link" to="/register">
                  Register
                </NavLink>
              </li>
            </React.Fragment>
          )}
          {currentUser && (
            <React.Fragment>
              <li class="nav-item">
                <NavLink clasName="nav-link" to="/view-ontology">
                  {currentUser
                    .split(":")[1]
                    .split(",")[0]
                    .replace(/^"(.*)"$/, "$1")}
                </NavLink>
              </li>
              <li class="nav-item">
                <NavLink clasName="nav-link" to="/logout">
                  Logout
                </NavLink>
              </li>
            </React.Fragment>
          )}
        </div>
      </div>
    </nav>
  );
};
export default Navigation;
