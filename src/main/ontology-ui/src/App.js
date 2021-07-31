import React, { Component } from "react";
//import 'react-toastify/dist/reactToastify.css';
import { BrowserRouter as Router, Route } from "react-router-dom";
import OntologyListComponent from "./components/OntologyListComponent";
import ViewOntologyComponent from "./components/ViewOntologyComponent";
import HeaderComponent from "./components/HeaderComponent";
import LoginFormComponent from "./components/LoginFormComponent";
import auth from './services/authService';
import Logout from "./components/Logout";
import RegisterForm from "./components/RegisterForm";
import Navbar from "./components/NavBar/Navigation";
import "./App.css";
class App extends Component {
  state = {};
  componentDidMount() {
   const user = auth.getCurrentUser();
    this.setState({user});
  }

  render() {
    return (
      <Router>
      <React.Fragment>
        <Navbar/>
        
        {/* <HeaderComponent /> */}  
          {/* <ToastContainer /> */}
         {/* <NavbarPage user={this.state.user}/> */}
          <main className="container">
            <switch>
              <Route path="/register" component={RegisterForm}></Route>
              <Route path="/login" component={LoginFormComponent}></Route>
              <Route path="/logout" component={Logout}></Route>
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
