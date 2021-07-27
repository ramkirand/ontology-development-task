import React, { Component } from "react";
import Joi from "joi-browser";
import Form from "./common/Form";
import Input from "./common/Input";
import { login } from "../services/authService";
import authService from "../services/authService";
class LoginFormComponent extends Form {
  state = {
    data: { username: "", password: "" },
    errors: {},
  };

  schema = {
    username: Joi.string().required().email(),
    password: Joi.string().required().min(5),
  };

  doSubmit = async () => {
    
      const { data } = this.state;
      console.log("Calling back end");
      authService.login(data.username, data.password)
      .then((res) => {
        console.log(" From backend res >>>>>>:" + res);
        //await login(data.username, data.password);
        window.location="/view-ontology";
      }).catch ((ex)=> {
     
     // console.log("<<<<<<<<<<<<LoginFormComponent do submit  In exception  >>>>>>>>>>>>>>>")
      if (ex.response && ex.response.status === 400) {
        console.log("In do Submi >>>>>>>>>>>>>>>>>");
        const errors = { ...this.state.errors };
        
        errors.username = ex.response.data;
        this.setState({ errors });
      }
     
    });
  }
  render() {
    return (
      <div>
        <div className="col-md-4  offset-md-2">
          <h2>Login</h2>
          <form className="form-control-md" onSubmit={this.handleSubmit}>
            {this.renderInput("username", "Email")}
            {this.renderInput("password", "Password", "password")}
            {this.renderButton("Login")}
          </form>
        </div>
      </div>
    );
  }
}
export default LoginFormComponent;
