import axios from "axios";
import logger from "./logService";
import { toast } from "react-toastify";
import config from "../config.json";
export function getDetails() {
  return http.get(config.apiUrl + "/ontologies/");
}
