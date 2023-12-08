import { Modal } from 'react-bootstrap';
import './ModalUserMenu.css';
import { FaUserCircle } from "react-icons/fa"

export const ModalsUserMenu = ({ onVerUsuario, onEditarUsuario, onChangeUsuario, onCerrarSesion, show, onHide }) => {
  return (
    <Modal show={show} onHide={onHide} centered>
      <Modal.Header closeButton className='modalHeader'>
        <Modal.Title>Opciones de Usuario</Modal.Title>
      </Modal.Header>
      <Modal.Body className='modalBody'>
        <div className='stylesContetMenuUser'>
          <div className='stylesContentImgMenuUser'>
            <FaUserCircle className="styleIconUserMenu"/>
          </div>
          <div className='styleContentButoon'>
            <div className='stylesLinkMenuUser' typeof='button' onClick={onVerUsuario}>
              <p>Ver Usuario</p>
            </div>
          </div>
          <div className='styleContentButoon'>
            <div className='stylesLinkMenuUser' typeof='button' onClick={onEditarUsuario}>
              <p>Editar Usuario</p>
            </div>
          </div>
          <div className='styleContentButoon'>
            <div className='stylesLinkMenuUser' typeof='button' onClick={onChangeUsuario}>
              <p>Cambiar Usuario</p>
            </div>
          </div>
          <button className='stylesButoon' onClick={onCerrarSesion} block>
            Cerrar Sesión
          </button>
        </div>
      </Modal.Body>
    </Modal>  
  );
};