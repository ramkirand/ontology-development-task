import * as http from "http";
import apiUrl from "../config.json";
import jwtDecode from "jwt-decode";
import axios from 'axios';

//const apiEndPoint = JSON.stringify(apiUrl) + "/users";
const apiEndPoint = "http://localhost:8080/ols/api/ontologies/" + "users";
const tokenKey = "token";

export async function login(email, password) {
  console.log("<<<<<<<<<<< auth service login  >>>>>");
  //const { data: jwt } = await http.post(apiEndPoint, { email, password });

  const { data: jwt } = axios.post(apiEndPoint, { email, password });

  console.log("<<<<<<<<<<< auth service   >>>>>  " + jwt);
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
