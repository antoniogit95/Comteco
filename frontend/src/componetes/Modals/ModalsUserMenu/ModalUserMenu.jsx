import { Modal } from 'react-bootstrap';
import './ModalUserMenu.css';

export const ModalsUserMenu = ({ onVerUsuario, onEditarUsuario, onChangeUsuario, onCerrarSesion, show, onHide }) => {
  return (
    <Modal show={show} onHide={onHide} centerd>
      <Modal.Header closeButton>
        <Modal.Title>Opciones de Usuario</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <button variant="outline-primary" onClick={onVerUsuario} block>
          Ver Usuario
        </button>
        <button variant="outline-success" onClick={onEditarUsuario} block>
          Editar Usuario
        </button>
        <button variant="outline-info" onClick={onChangeUsuario} block>
          Cambiar de Usuario
        </button>
        <button variant="outline-danger" onClick={onCerrarSesion} block>
          Cerrar Sesión
        </button>
      </Modal.Body>
    </Modal>
  );
};