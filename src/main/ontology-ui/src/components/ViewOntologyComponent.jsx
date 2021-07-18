import React, { Component } from "react";
import OntologyService from "../services/OntologyService";
import MainComponent from "../components/MainComponent";

class ViewOntologyComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ontologyId: this.props.match.params.ontologyId,
      showMe: false,
      ontology: {},
      errResp: "",
    };

    this.changeOntologyIdHandler = this.changeOntologyIdHandler.bind(this);
    this.viewOntology = this.viewOntology.bind(this);
  }

  changeOntologyIdHandler = (event) => {
    this.setState({ ontologyId: event.target.value });
    console.log("changeOntologyIdHandler===>" + event.target.value);
  };

  viewOntology = (e) => {
    e.preventDefault();
    console.log("viewOntology ====>" + this.state.ontologyId);
    OntologyService.getOntologyMetaById(this.state.ontologyId)
      .then((res) => {
        this.state.showMe = true;
        this.setState({ ontology: res.data });
        this.state.errshowMe = true;
        console.log("viewOntology  in then ====>");
      })
      .catch((err) => {
        this.setState({
          errResp: JSON.stringify(err.response).substring(10, 129),
        });
        //this.state.errshowMe = true;
        console.log("viewOntology Clicked" + this.state.errResp);
      });
  };

  viewOntologies = (e) => {
    e.preventDefault();

    this.props.history.push("/list-ontologies");
    console.log("viewOntologies Clicked");
  };

  render() {
    return (
      <div>
        <div className="outer-container">
          <div className="row">
            <div className="card col-md-8 offset-md-2">
              <h4 className="text-center">Ontology Meta Data</h4>
              <div className="card-body">
                <form className="form-control-lg" onSubmit={this.handleSubmit}>
                  <div className="form-group move-left">
                    <input
                      placeholder="OntologyId"
                      name="OntologyId"
                      className="form-control"
                      value={this.state.OntologyId}
                      onChange={this.changeOntologyIdHandler}
                    />
                  </div>

                  <button
                    className="btn bg-info btn-sm"
                    onClick={this.viewOntology}
                    style={{ marginLeft: "250px" }}
                  >
                    Search
                  </button>
                  <button
                    className="btn bg-info btn-sm"
                    onClick={this.viewOntologies.bind(this)}
                    style={{ marginLeft: "10px" }}
                  >
                    ViewOntologies
                  </button>
                </form>
              </div>
            </div>
          </div>
          <div className="flex  text-danger">
            <p className="m-auto ">{this.state.errResp}</p>
          </div>
        </div>
        <br></br>
        {this.state.showMe ? (
          <div className="card col-md-8  offset-md-2">
            <h5 className="text-center">Ontology Details</h5>
            <div className="card-body">
              <div className="row">
                <label>
                  <strong>OntologyId:</strong>
                  {this.state.ontology.ontologyId}
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
        ) : null}
      </div>
    );
  }
}
export default ViewOntologyComponent;
