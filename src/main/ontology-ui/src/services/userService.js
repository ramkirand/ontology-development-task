import * as http from 'http';
import apiUrl from "../config.json";
const apiEndPoint = apiUrl + "/users";
export function register(user) {
  http.post(apiEndPoint, {
    email: user.username,
    password: user.password,
    name: user.name
  });
}
