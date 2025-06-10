import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/KeycloakContext';
import NavBar from './components/NavBar';
import HomePage from './pages/HomePage';
import AdminPage from './pages/AdminPage';
import Unauthorized from './pages/UnauthorizedPage';
import ProtectedRoute from './routes/ProtectedRoute';
import NotFoundPage from './pages/NotFoundPage';
import NewUserForm from './components/NewUserForm';
import ParentManagement from './components/ParentManagement';
import './parent.css';

function App() {
  return (
    <AuthProvider>
      <NavBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/admin" element={
          <ProtectedRoute roles={['admin']}>
            <AdminPage />
          </ProtectedRoute>
        } />
        <Route path="/register-user" element={
          <ProtectedRoute roles={['admin']}>
            <NewUserForm />
          </ProtectedRoute>
        } />
        <Route path="/parent" element={
          <ProtectedRoute roles={['admin']}>
            <ParentManagement />
          </ProtectedRoute>
        } />
        <Route path="/unauthorized" element={<Unauthorized />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </AuthProvider>
  );
}

export default App;
