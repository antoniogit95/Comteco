import React, { useState, useEffect } from 'react';
import './Footer.css'
export const Footer = () => {
  const [currentTime, setCurrentTime] = useState(new Date());

  useEffect(() => {
    // Actualiza la hora cada segundo
    const intervalId = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    // Limpia el intervalo cuando el componente se desmonta
    return () => clearInterval(intervalId);
  }, []); // El segundo argumento vacío significa que este efecto solo se ejecutará una vez al montar el componente

  return (
    <footer className='footer'>
      <p>Hora actual del Sistema: {currentTime.toLocaleTimeString()}</p>
      {/* Puedes agregar más funcionalidades aquí */}
    </footer>
  );
};

