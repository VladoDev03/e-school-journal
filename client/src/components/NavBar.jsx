import { Link } from 'react-router-dom';
import { useAuth } from '../context/KeycloakContext';
import '../navbar.css';

export default function NavBar() {
    const { keycloak } = useAuth();
    const roles = keycloak.tokenParsed?.realm_access?.roles || [];

    const isLoggedIn = keycloak.authenticated;

    return (
        <nav className="navbar">
            <div className="navbar-links">
                {roles.includes('admin') && (
                    <>
                        <Link to="/parent">Parent Management</Link>
                        <Link to="/director">Director Management</Link>
                        <Link to="/roles">Roles Management</Link>
                        <Link to="/update-user">Update User</Link>
                        <Link to="/school">School Management</Link>
                    </>
                )}
            </div>
            {isLoggedIn && (
                <button onClick={() => keycloak.logout()}>
                    Logout
                </button>
            )}
        </nav>
    );
}
