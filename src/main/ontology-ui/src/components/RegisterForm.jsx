import React, { Component } from "react";
import Joi from "joi-browser";
import Form from "./common/Form";
import Input from "./common/Input";
import * as userService from "../services/userService";
import auth from "../services/authService";
class RegisterForm extends Form {
  state = {
    data: { username: "", password: "", name: "" },
    errors: {},
    errResp: "",
  };

  schema = {
    username: Joi.string().required().email(),
    password: Joi.string().required(),
    name: Joi.string().required(),
  };
  doSubmit = () => {
    const { data } = this.state;
    console.log(
      "email:" +
        data.username +
        ",Pasword: " +
        data.password +
        ", Name:" +
        data.name
    );
    const resp = userService
      .register(data.username, data.password, data.name)
      .then((res) => {
        console.log(" From backend res >>>>>>:" + JSON.stringify(resp));
        //await login(data.username, data.password);
        //window.location = "/";
      })
      .catch((err) => {
        if (err.response !== null) {
          console.log("err.response:" + err);
        }
        console.log("Error Message:" + JSON.stringify(err));
        console.log("register Clicked: " + this.state.errResp);
      });
  };
  render() {
    return (
      <div>
        <div className="col-md-4  offset-md-2">
          <h2>Register</h2>
          <div className="flex  text-danger">
            <p className="m-auto ">{this.state.errResp}</p>
          </div>
          <form className="form-control-md" onSubmit={this.handleSubmit}>
            {this.renderInput("username", "Email","username")}
            {this.renderInput("password", "Password", "password")}
            {this.renderInput("name", "Name", "name")}
            {this.renderButton("Register")}
          </form>
        </div>
      </div>
    );
  }
}
export default RegisterForm;
