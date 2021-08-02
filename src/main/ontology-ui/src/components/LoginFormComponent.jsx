import React, { Component } from "react";
import Joi from "joi-browser";
import Form from "./common/Form";
import auth from "../services/authService";

class LoginFormComponent extends Form {
  state = {
    data: { username: "", password: "" },
    response: "",
    errors: {},
    showMe: false,
  };

  schema = {
    username: Joi.string().required().email(),
    password: Joi.string().required().min(5),
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const { data } = this.state;
    auth
      .login(this.state.data)
      .then((res) => {
        console.log(JSON.stringify(res));
        window.location = "/view-ontology";
        console.log("In then");
      })
      .catch((err) => {
        console.log("IN catch");
        this.state.showMe = true;
        this.state.response = "Invalid username/password";
        window.location = "/";
      });
  };
  render() {
    return (
      <div>
        <div className="col-md-4  offset-md-2">
          <h2>Login</h2>
          <form className="form-control-md" onSubmit={this.handleSubmit}>
            {this.renderInput("username", "Email", "username")}
            {this.renderInput("password", "Password", "password")}
            {this.renderButton("Login")}
            {this.state.showMe ? (
              <div className="flex  text-danger">
                <p className="m-auto ">{this.state.response}</p>
              </div>
            ) : (
              <div className="flex  text-primary">
                <p className="m-auto ">{this.state.response}</p>
              </div>
            )}
          </form>
        </div>
      </div>
    );
  }
}
export default LoginFormComponent;
