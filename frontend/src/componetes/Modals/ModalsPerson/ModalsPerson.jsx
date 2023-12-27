import React from 'react';
import Modal from 'react-bootstrap/Modal';
import './ModalsPerson.css'

export const ModalPerson = ({ person, show, onHide, onChange }) => {
    
    if (!person) {
        return null;
    }
    /** 
     * id
     * item
     * nombre completo
     * ci
     * fecha nacimiento
     * fecha de creacion 
     * fecha de validacion o aceptacion
     * telefono
     * vpn numero corte de 4 digitos -> numero corporativos
     * numero de whatsapp
     * email
     * grupo de trabajo
    */

    return (
        <Modal show={show} onHide={onHide} centered>
            <Modal.Header closeButton className='mheader'>
                <Modal.Title>Datos de la Persona</Modal.Title>
            </Modal.Header>
            <Modal.Body className='mBody'>
                <p>Item: {person.item}</p>
                <p>Nombre: {person.nombre+ " " + person.apellidos}</p>
                <p>Cédula de Identidad: {person.celula_identidad}</p>
                <p>Fecha de Nacimiento: {person.fecha_nacimiento}</p>
                <p>Fecha de Creación: {person.created_at}</p>
                <p>Fecha de Validacion: {person.update_at}</p>
                <p>Numero corporativos: {"agregar numero corporativo"}</p>    
                <p>Teléfono: {person.telefono}</p>
                <p>Email: {person.email}</p>
                <p>Grupo de Trabajo: {"sin grupo de trabajo"}</p>
            </Modal.Body>
            <Modal.Footer className='mFooter'>
                {person.status?" ": ( <button className = "stylesButoonModal" variant="secondary" onClick={onChange}>
                    Validar
                </button>) }
            </Modal.Footer>
        </Modal>
    );
};