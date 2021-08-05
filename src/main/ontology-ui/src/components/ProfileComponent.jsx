import React, { Component } from "react";
import auth from "../services/authService";
import jwt_decode from "jwt-decode";
class ProfileComponent extends Component {
  
  componentDidMount() {
    try {
      this.name = localStorage.getItem("name");
      this.role = localStorage.getItem("role");
      this.email = localStorage.getItem("email");
      
    } catch (ex) {}
  }
  constructor(props) {
    super(props);
    this.state = {
      email:'',
      name:'',
      role:'',
    }
  };
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
                <th>User</th>
                <th>Email</th>
                <th>Name</th>
                <th>Role</th>
            </tr>
            </thead>
              <tbody>
                  <td>{this.state.name}</td>
                  
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}
export default ProfileComponent;
