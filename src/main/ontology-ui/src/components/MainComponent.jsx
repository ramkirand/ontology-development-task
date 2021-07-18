import React, { Component } from "react";

class MainOntologyComponent extends Component {
  constructor(props) {
    super(props);
    // this.state = {
    // }

    // this.changeOntologyIdHandler = this.changeOntologyIdHandler.bind(this);
    // this.viewOntology = this.viewOntology.bind(this);
  }

  render() {
    return (
      <div>
        <div className="card col-md-8  offset-md-2">
          {/* <h5 className = "text-center">Ontology Details</h5> */}
          <div className="card-body">
            <div className="row">
              <label>
                <strong>OntologyId:</strong>
                {this.props.ontologyId}
              </label>
            </div>

            <div className="row">
              <label>
                <strong>Title:</strong>
                {this.state.ontology.title}{" "}
              </label>
            </div>
            <div className="row">
              <label>
                <strong> Description:</strong>
                {this.state.ontology.description}{" "}
              </label>
            </div>
            <div className="row">
              <label>
                <strong>List Of Definition Properties:</strong>
                {this.state.ontology.definitionProperties}
              </label>
            </div>

            <div className="row">
              <label>
                <strong>List of Synonym Properties:</strong>
                {this.state.ontology.synonymProperties}{" "}
              </label>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default MainOntologyComponent;
