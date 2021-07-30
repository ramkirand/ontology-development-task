import * as http from "http";
import {apiUrl} from "../config.json";
import {authUrl} from "../config.json";
import axios from "axios";
const apiEndPoint = authUrl + "/signup";
export async function register(email, password,name) {
  const { data: jwt } = axios.post(apiEndPoint, { email, password,name});
}

export default {
  register
};
