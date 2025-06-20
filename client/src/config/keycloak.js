import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:8080",
  realm: "e-school-journal",
  clientId: "e-school-journal-react",
});

export const initKeycloak = (onAuthenticatedCallback) => {
  console.warn(
    "initKeycloak is deprecated - initialization now happens in KeycloakContext"
  );
  keycloak
    .init({
      onLoad: "login-required",
      checkLoginIframe: false,
      pkceMethod: "S256",
    })
    .then((authenticated) => {
      if (authenticated) {
        onAuthenticatedCallback();
      } else {
        keycloak.login();
      }
    })
    .catch(console.error);
};

export const getToken = () => {
  return keycloak.token;
};

export default keycloak;
