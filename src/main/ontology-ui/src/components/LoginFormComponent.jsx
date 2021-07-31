import React, { Component } from "react";
import Joi from "joi-browser";
import Form from "./common/Form";
import Input from "./common/Input";
import { login } from "../services/authService";
import authService from "../services/authService";
import flower from "../image/ontology.jpg";
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
    const tokenKey = "token";
    const { data } = this.state;
      authService
      .login(this.state.data)
      .then((res) => {
        console.log("Jwt:" + JSON.stringify(res.data.token));
        localStorage.setItem(tokenKey, JSON.stringify(res.data.token));
        window.location = "/view-ontology";
      })
      .catch((err) => {
        this.state.showMe = true;

        this.setState({
          response: "Invalid username/password",
        });
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
