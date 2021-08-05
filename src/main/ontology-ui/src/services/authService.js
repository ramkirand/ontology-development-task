import * as http from "http";
import { apiUrl } from "../config.json";
import { authUrl } from "../config.json";
import jwt_decode from "jwt-decode";
import axios from "axios";

const apiEndPoint = authUrl + "/authenticate";
const tokenKey = "token";
const userEmail = "email";
const userName="name";
const role="role";
export async function login(user) {
  const { data: jwt } = await axios.post(apiEndPoint, {
    username: user.username,
    password: user.password,
  });
  localStorage.setItem(role, JSON.stringify(jwt.role));
  localStorage.setItem(userEmail, JSON.stringify(jwt.username));
  localStorage.setItem(userName, JSON.stringify(jwt.name));
  localStorage.setItem(tokenKey, JSON.stringify(jwt.token));
}
export function loginWithJwt(jwt) {
  localStorage.setItem(tokenKey, jwt);
  localStorage.setItem(userEmail, jwt);
  localStorage.setItem(userName, jwt);
  localStorage.setItem(role, jwt);
}

export function logout() {
  localStorage.removeItem(tokenKey);
  localStorage.removeItem(userEmail);
  localStorage.removeItem(userName);
  localStorage.removeItem(role);
  window.location = "/";
}

export function getCurrentUser() {
  try {
    const jwt = localStorage.getItem(tokenKey);
    console.log("JWT decode:" + JSON.stringify(jwt));
    return jwt;
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
