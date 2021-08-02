import * as http from "http";
import { apiUrl } from "../config.json";
import { authUrl } from "../config.json";
import axios from "axios";
const apiEndPoint = authUrl + "/signup";

export async function register(user) {
  const { data: jwt } = await axios.post(apiEndPoint, {
    username: user.username,
    password: user.password,
    name: user.name,
  });
  console.log("<<<<<<<<<<  User:" + JSON.stringify(jwt));
}

export default {
  register,
};
