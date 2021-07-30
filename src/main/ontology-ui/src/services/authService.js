import * as http from "http";
import {apiUrl} from "../config.json";
import {authUrl} from "../config.json";
import jwtDecode from "jwt-decode";
import axios from "axios";

const apiEndPoint = authUrl + "/authenticate";
const tokenKey = "token";
export async function login(username, password) {
  const { data: jwt } = axios.post(apiEndPoint, { username, password });
  localStorage.setItem(tokenKey, jwt);
}

export function loginWithJwt(jwt) {
  localStorage.setItem(tokenKey, jwt);
}

export function logout() {
  localStorage.removeItem(tokenKey);
}

export function getCurrentUser() {
  //TBD revisit later
  try {
    const jwt = localStorage.getItem(tokenKey);
    return jwtDecode(jwt);
    // console.log(user);
  } catch (ex) {
    return null;
  }
}
export default {
  login,
  logout,
  loginWithJwt,
  getCurrentUser,
};
