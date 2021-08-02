import React, { Component } from "react";
import Joi from "joi-browser";
import Form from "./common/Form";
//import Input from "./common/Input";
import * as userService from "../services/userService";
import auth from "../services/authService";
class RegisterForm extends Form {
  state = {
    data: { username: "", password: "", name: "" },
    errors: {},
    showMe: false,
    response: "",
  };

  schema = {
    username: Joi.string().required().email(),
    password: Joi.string().required(),
    name: Joi.string().required(),
  };
  doSubmit = (e) => {
    e.preventDefault();
    const { data } = this.state;
    const response = userService
      .register(this.state.data)
      .then((res) => {
        console.log("In then");
        console.log(" From backend res >>>>>>:" + JSON.stringify(res));
        window.location = "/login";
      })
      .catch((err) => {
        console.log("In catch");
        this.state.showMe = true;
        if (err.response !== null) {
          this.state.response = JSON.stringify(err.message);
        }
        console.log("register Clicked:" + this.state.response);
        alert(this.state.response);
        window.location = "/";
      });
  };
  render() {
    return (
      <div>
        <div className="col-md-4  offset-md-2">
          <h2>Register</h2>
          <form className="form-control-md" onSubmit={this.doSubmit}>
            {this.renderInput("username", "Email", "username")}
            {this.renderInput("password", "Password", "password")}
            {this.renderInput("name", "Name", "name")}
            {this.renderButton("Register")}
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
export default RegisterForm;
