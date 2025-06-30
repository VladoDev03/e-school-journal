import { Link } from 'react-router-dom';
import { useAuth } from '../context/KeycloakContext';

export default function NavBar() {
    const { keycloak } = useAuth();
    const roles = keycloak.tokenParsed?.realm_access?.roles || [];

    const isLoggedIn = keycloak.authenticated;

    return (
        <nav style={{ padding: 10, borderBottom: '1px solid gray' }}>
            {roles.includes('admin') && (
                <>
                    <Link to="/admin">Admin</Link>
                    {" "}
                    {" "}
                    <Link to="/register-user">Register User</Link>
                    {" "}
                    {" "}
                    <Link to="/parent">Parent Management</Link>
                    {" "}
                    {" "}
                    <Link to="/director">Director Management</Link>
                    {" "}
                    {" "}
                    <Link to="/school">School Management</Link>
                </>
            )}{" "}
            {isLoggedIn && (
                <button onClick={() => keycloak.logout()} style={{ marginLeft: 10 }}>
                    Logout
                </button>
            )}
        </nav>
    );
}
