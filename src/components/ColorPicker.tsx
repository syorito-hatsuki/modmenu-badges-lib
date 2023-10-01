import {ColorPickerProps} from "./types.ts";
import {useEffect, useRef, useState} from "react";
import {HexAlphaColorPicker} from "react-colorful";
import "./Components.css"

export default function ColorPicker(props: ColorPickerProps) {

    const [isOpen, setOpen] = useState(false)

    const containerRef = useRef<HTMLDivElement | null>(null);

    useEffect(() => {
        const handleOutsideClick = (event: MouseEvent) => {
            if (containerRef.current && !containerRef.current.contains(event.target as Node)) setOpen(false);
        };

        document.addEventListener('mousedown', handleOutsideClick);

        return () => document.removeEventListener('mousedown', handleOutsideClick);
    }, [])

    return (
        <div className="colorPickerContainer">
            <label className="colorPickerLabelStyle">{props.label}</label>
            <div style={{position: "relative"}}>
                <button className="colorPickerInputStyle" style={{background: props.color}}
                        value={props.color} onClick={() => setOpen(true)}/>
                {isOpen && <div ref={containerRef}>
                    <HexAlphaColorPicker className="colorPicker" color={props.color} onChange={newColor => {
                        const color = newColor.length === 7 ? newColor + "ff" : newColor;
                        props.setColor(color);
                    }}/>
                </div>}
            </div>
        </div>
    )
}