import React from "react";
import { createBrowserRouter } from "react-router-dom";

import { Home } from '../vistas/Home/Home'; 
import { Personal } from '../vistas/Personal/Personal';
import { Login } from '../vistas/Login/Login';    
import { ProtectedRoutes } from "./ProtectedRoutes";
import { SingAuth } from "../vistas/SingAuth/SingAuth";
import { Report } from "../vistas/Report/Report";
import { Register } from "../vistas/Register/Register";
import { ForgenPassword } from "../vistas/ForgenPassword/ForgenPassword";
import { Equipos } from "../vistas/Equipos/Equipos";
import { DarkMode } from "../vistas/TemaOscuro/DarkMode";
//import { NavBar } from '../componetes/NavBar/NavBar';


export const Rutas = createBrowserRouter([
  { path: "/", element: <Login /> },
  { path:'/login', element:<Login /> },
  { path:'/singauth', element:<SingAuth /> },
  { path:'/forgenpassword', element:<ForgenPassword /> },
  { path:'/darkmode', element:<DarkMode /> },
  { path: "/", element: <ProtectedRoutes />,
    children:[
      { path:'/home', element:<Home/> },
      { path:'/reportes', element:<Report /> },
      { path:'/registrar', element:<Register /> },
      { path:'/personal', element:<Personal/> },
      { path:'/equipos', element:<Equipos/> },
    ]
  },
]); 
