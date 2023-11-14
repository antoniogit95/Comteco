import React from 'react';
import Modal from 'react-bootstrap/Modal';
import './ModalsPerson.css'

export const ModalPerson = ({ person, show, onHide, onChange }) => {
    
    if (!person) {
        return null;
    }

    return (
        <Modal show={show} onHide={onHide} centered>
            <Modal.Header closeButton>
                <Modal.Title>Datos de la Persona</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>ID de la persona: {person.id_person}</p>
                <p>Cédula de Identidad: {person.celula_identidad}</p>
                <p>Nombre: {person.nombre}</p>
                <p>Apellidos: {person.apellidos}</p>
                <p>Item: {person.item}</p>
                <p>Fecha de Nacimiento: {person.fecha_nacimiento}</p>
                <p>Email: {person.email}</p>
                <p>Teléfono: {person.telefono}</p>
                <p>Validado: {person.status? "Validado": "No validado"}</p>
                <p>Fecha de Creación: {person.created_at}</p>
            </Modal.Body>
            <Modal.Footer>
                {person.status?" ": ( <button className = "stylesButoonModal" variant="secondary" onClick={onChange}>
                    Validar
                </button>) }
            </Modal.Footer>
        </Modal>
    );
};