import React from "react";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import OntologyListComponent from "./components/OntologyListComponent";
import ViewOntologyComponent from "./components/ViewOntologyComponent";
import HeaderComponent from "./components/HeaderComponent";
import HomeOntologyComponent from "./components/HomeOntologyComponent";
function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />
        <div className="container">
          <switch>
            <Route
              path="/home-ontology"
              component={HomeOntologyComponent}
            ></Route>
            <Route
              path="/view-ontology"
              component={ViewOntologyComponent}
            ></Route>
            <Route
              path="/list-ontologies"
              component={OntologyListComponent}
            ></Route>
          </switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
