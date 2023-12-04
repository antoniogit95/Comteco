import React, { useState } from 'react'
import './DarkMode.css'
import ReactSwitch from 'react-switch'
import { ToggleButton } from 'react-bootstrap'
import context from 'react-bootstrap/esm/AccordionContext'

export const DarkMode = () => {
    const{contextTheme, setContextTheme} = useThemeContext()
    const[checked, setChecked] = useState(false)
    const handleSwitch = (nextChecked) => {
        setContextTheme( (state) => (state == 'Lightt' ? 'Dark':'Light'))
        setChecked(nextChecked)
        console.log(nextChecked)
    }
    return(
        <header classname="DarkModePrueba" id={contextTheme}>
        <ReactSwitch
        onChange={handleSwitch}
        checked= {checked}
        onColor="#86d3ff"
        onHandleColor="#2693e6"
        handleDiameter={30}
        uncheckedIcon={false}
        checkedIcon={false}
        boxShadow="0px 1px 5px rgba(0, 0, 0, 0.6)"
        activeBoxShadow="0px 0px 1px 10px rgba(0, 0, 0, 0.2)"
        height={20}
        width={48}
        className="react-switch"
        id="material-switch"
        />
        </header>
    )
}
