import http from "./httpService";
import config from "../config.json";
export function getDetails(){
    return  http.get(config.apiUrl + "/ontologies/");
}