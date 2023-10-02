import React from "react";
import { createBrowserRouter } from "react-router-dom";

import { Home } from '../vistas/Home/Home';
import { Personal } from '../vistas/Personal/Personal';
import { Login } from '../vistas/Login/Login';    
import { ProtectedRoutes } from "./ProtectedRoutes";
//import { NavBar } from '../componetes/NavBar/NavBar';


export const Rutas = createBrowserRouter([
  { path: "/", element: <Login /> },
  { path:'/login', element:<Login /> },
  { path:'/personal', element:<Personal/> },
  { path: "/", element: <ProtectedRoutes />,
    children:[
      { path:'/home', element:<Home/> },
      { path:'/reportes', element:<h1>Reportes</h1> },
      { path:'/registrar', element:<h1>Registrar</h1> },
    ]
  },
]); 
