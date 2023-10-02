import './App.css'
import {  RouterProvider } from 'react-router-dom';
import { Rutas } from './rutas/Rutas';
import { AuthProvider } from './providerContext/AuthProvider';

function App() {

  return (
      <AuthProvider>
        <RouterProvider router={Rutas} />
      </AuthProvider>
  );
}
export default App;
