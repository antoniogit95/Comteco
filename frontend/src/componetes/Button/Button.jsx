import React from "react";

export const Button = (props) => {

    const { nombre }= props;
    console.log(nombre+" -");

    return (<Button>
        {nombre}
    </Button>);
}