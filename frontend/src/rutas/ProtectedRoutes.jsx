import react from "react";
import { Outlet, Navigate } from "react-router-dom";
import { useAuth } from "../providerContext/AuthProvider";


export const ProtectedRoutes = () => {
    const auth = useAuth();
    return auth.isAuthenticated ? <Outlet /> : <Navigate to='/login' />
}