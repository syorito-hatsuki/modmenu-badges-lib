import {ColorPickerProps} from "./types.ts";
import {CSSProperties, useEffect, useRef, useState} from "react";
import {HexAlphaColorPicker} from "react-colorful";

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


    const labelStyle: CSSProperties = {
        color: "#ffffff",
        fontFamily: "Minecraftia",
        whiteSpace: "pre"
    }

    const inputStyle: CSSProperties = {
        borderStyle: "solid",
        borderColor: "black",
        outlineStyle: "solid",
        outlineColor: "white",
        background: props.color,
        height: 20,
        width: 40
    }

    return (
        <div style={{display: "flex", paddingBottom: 8, alignItems: "baseline"}}>
            <label style={labelStyle}>{props.label}</label>
            <div style={{
                position: "relative"
            }}>
                <button style={inputStyle} value={props.color} onClick={() => setOpen(true)}/>
                {isOpen && <div ref={containerRef}>
                    <HexAlphaColorPicker style={{
                        position: "absolute",
                        top: "-100%",
                        left: "100%",
                        padding: 5,
                        zIndex: 9999,
                        marginLeft: 8
                    }} color={props.color} onChange={newColor => {
                        let color = newColor
                        if (newColor.length === 7) color = newColor + "ff";
                        props.setColor(color);
                    }}/>
                </div>}
            </div>
        </div>
    )
}