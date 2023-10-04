import React from "react";
import "./Button.css"
import { useNavigate } from "react-router-dom";

export const Button = ({name, link}) => {

    const navigate = useNavigate();

    function handleClick(){
        navigate(link);
    }

    return (<>
        <div className="stylesContentButton">
            <button className="stylesButtonBig" onClick={handleClick}>{name}</button>
        </div>
    </>);
}