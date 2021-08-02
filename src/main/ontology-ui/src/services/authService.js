import * as http from "http";
import { apiUrl } from "../config.json";
import { authUrl } from "../config.json";
import jwt_decode from "jwt-decode";
import axios from "axios";

const apiEndPoint = authUrl + "/authenticate";
const tokenKey = "token";
export async function login(user) {
  const { data: jwt } = await axios.post(apiEndPoint, {
    username: user.username,
    password: user.password,
  });
  localStorage.setItem(tokenKey, JSON.stringify(jwt.token));
}
export function loginWithJwt(jwt) {
  localStorage.setItem(tokenKey, jwt);
}

export function logout() {
  localStorage.removeItem(tokenKey);
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
