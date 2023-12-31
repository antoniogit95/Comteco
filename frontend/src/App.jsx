import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {  RouterProvider } from 'react-router-dom';
import { Rutas } from './rutas/Rutas';
import { AuthProvider } from './providerContext/AuthProvider';
import React from 'react';

function App() {

  return (
      <AuthProvider>
        <RouterProvider router={Rutas} />
        
      </AuthProvider>
  );
}
export default App;
