import React, { createContext, useContext, useEffect, useState } from 'react';
import keycloak from '../config/keycloak';

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const [keycloakReady, setKeycloakReady] = useState(false);
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    keycloak.init({ 
      onLoad: 'login-required',
      checkLoginIframe: false,
      pkceMethod: 'S256'
    }).then((auth) => {
      setAuthenticated(auth);
      setKeycloakReady(true);
      
      // Set up token refresh
      setInterval(() => {
        keycloak.updateToken(70).then((refreshed) => {
          if (refreshed) {
            console.log('Token refreshed');
          } else {
            console.log('Token not refreshed, valid for', Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
          }
        }).catch(() => {
          console.log('Failed to refresh token');
        });
      }, 60000); 
    }).catch((error) => {
      console.error('Keycloak initialization failed:', error);
      setKeycloakReady(true); 
    });
  }, []);

  return keycloakReady ? (
    <AuthContext.Provider value={{ keycloak, authenticated }}>
      {children}
    </AuthContext.Provider>
  ) : (
    <div>Loading...</div>
  );
};