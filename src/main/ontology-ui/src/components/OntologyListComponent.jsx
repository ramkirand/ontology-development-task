import React, { Component } from "react";
// import { useState, useEffect } from "react";
import OntologyService from "../services/OntologyService";

class OntologyListComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ontologies: [],
      errResp: "",
    };
  }
  componentDidMount() {
    OntologyService.getOntologies()
      .then((resp) => {
        if (resp.request.status !== 200) {
          console.log("=========== >>>>>>>>>>>>>>>>>>>>" + resp.request.status);
          throw Error("getOntologies exception");
        }
        this.setState({ ontologies: resp.data });
      })
      .catch((err) => {
        console.log("catch getOntologies exception: " + err.response);
        this.setState({ errResp: err });
      });
    }
  render() {
    return (
      <div>
        <div className="flex text-danger">
          <p className="m-auto">{this.state.errResp}</p>
        </div>
        <h4 className="text-center">List Of Ontologies</h4>
        <div className="row">
          <table className="table-stripped   table-bordered table-sm">
            <thead class="thead-dark">
              <tr className="bg-info">
                <th>OntologyId</th>
                <th>Title</th>
                <th>Description</th>
                <th>DefinitionProperties</th>
                <th>SynonymProperties</th>
              </tr>
            </thead>

            <tbody>
              {this.state.ontologies.map((ontology) => (
                <tr key={ ontology.id}>
                  <td>{ ontology.ontologyId }</td>
                  <td>{ ontology.title}</td>
                  <td>{ ontology.description}</td>
                  <td>{ ontology.definitionProperties}</td>
                  <td>{ ontology.synonymProperties}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}
export default OntologyListComponent;
