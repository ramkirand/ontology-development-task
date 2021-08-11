import React, { Component } from "react";
import auth from "../services/authService";
import jwt_decode from "jwt-decode";
class ProfileComponent extends Component {
  componentDidMount() {
    try {
      this.name = localStorage.getItem("name").replace(/^"(.*)"$/, "$1");
      this.role = localStorage.getItem("role").replace(/^"(.*)"$/, "$1");
      this.email = localStorage.getItem("email").replace(/^"(.*)"$/, "$1");
    } catch (ex) {}
  }
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      name: "",
      role: "",
    };
  }
  render() {
    const { currentUser } = this.state;
    const { name } = this.state;
    const { email } = this.state;
    const { role } = this.state;

    return (
      <div>
        <h4 className="text-center">User Profile</h4>
        <div className="row">
          <table className="table-stripped   table-bordered table-sm">
            <thead class="thead-dark">
              <tr className="bg-info">
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              <td>{this.name}</td>
              <td>{this.email}</td>
              <td>{this.role}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}
export default ProfileComponent;
