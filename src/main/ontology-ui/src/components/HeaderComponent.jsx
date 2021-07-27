import React, { Component } from "react";
import { Navbar, Container, Header } from "rsuite";
class HeaderComponent extends Component {
  render() {
    return (
      <div>
        <Container>
          <Header>
            <Navbar appearance="inverse">
              <Navbar.Header>
                <a className="navbar-brand logo">BRAND</a>
                <a className="navbar-brand logo" href="/login">
                  Login
                </a>
                <a className="navbar-brand logo" href="/register">
                  Register
                </a>
              </Navbar.Header>
            </Navbar>
          </Header>
        </Container>
      </div>
    );
  }
}
export default HeaderComponent;
