import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
 url: "http://localhost:8080",
 realm: "e-school-journal",
 clientId: "e-school-journal-react",
});

export default keycloak;
