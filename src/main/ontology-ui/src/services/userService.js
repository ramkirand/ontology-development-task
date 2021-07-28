import * as http from "http";
import {apiUrl} from "../config.json";
import axios from "axios";
const apiEndPoint = apiUrl + "users";
export async function register(email, password,name) {
  const { data: jwt } = axios.post(apiEndPoint, { email, password,name});
}
