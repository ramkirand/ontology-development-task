import React, { Component } from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import OntologyListComponent from "./components/OntologyListComponent";
import ViewOntologyComponent from "./components/ViewOntologyComponent";
import LoginFormComponent from "./components/LoginFormComponent";
import Logout from "./components/Logout";
import RegisterForm from "./components/RegisterForm";
import Navigation from "./components/Navigation";
import auth from "./services/authService";
import "./App.css";
class App extends Component {
  state = {};
  componentDidMount() {
   // const {user} = auth.getCurrentUser();
    //this.setState(user);
  }

  render() {
    return (
      <Router>
        <React.Fragment>
          <Navigation/>
          <main className="container">
            <switch>
              <Route path="/register" component={RegisterForm}></Route>
              <Route path="/login" component={LoginFormComponent}></Route>
              <Route path="/logout" component={Logout}></Route>

              <Route path="/navigation" component={Navigation}></Route>
              <Route
                path="/view-ontology"
                component={ViewOntologyComponent}
              ></Route>
              <Route
                path="/list-ontologies"
                component={OntologyListComponent}
              ></Route>
            </switch>
          </main>
        </React.Fragment>
      </Router>
    );
  }
}
export default App;
