import axios from 'axios';

const ONTOLOGY_BASE_URL = "http://localhost:8080/ols/api/ontologies/";

class OntologyService{
    
    getOntologyMetaById(ontologyId){
        console.log(ONTOLOGY_BASE_URL + ontologyId);
        return axios.get(ONTOLOGY_BASE_URL + ontologyId);
    }

    getOntologies(){
        console.log(ONTOLOGY_BASE_URL);
        return axios.get(ONTOLOGY_BASE_URL);
    }
}

export default new OntologyService()