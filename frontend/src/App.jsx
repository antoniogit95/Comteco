import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {  RouterProvider } from 'react-router-dom';
import { Rutas } from './rutas/Rutas';
import { AuthProvider } from './providerContext/AuthProvider';
import { Footer } from "../src/componetes/Footer/Footer";

function App() {

  return (
      <AuthProvider>
        <RouterProvider router={Rutas} />
        
      </AuthProvider>
  );
}
export default App;
