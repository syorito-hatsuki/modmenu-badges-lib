import {CSSProperties, useState} from 'react'
import "./App.css"
import ColorPicker from "./components/ColorPicker.tsx";
import LabelInput from "./components/LabelInput.tsx";
import SyntaxHighlighter from "react-syntax-highlighter/dist/cjs/prism";
import {materialDark} from "react-syntax-highlighter/dist/cjs/styles/prism";
import toRGBA from "./colorUtil.ts";

export default function App() {
    const [outlineColor, setOutlineColor] = useState("#4CAF50FF")
    const [backgroundColor, setBackgroundColor] = useState("#2E7D32FF")
    const [labelColor, setLabelColor] = useState("#e3e3e3")
    const [label, setLabel] = useState("Custom Badge")

    const colorSpaceForPreview: CSSProperties = {
        outlineColor: outlineColor,
        backgroundColor: backgroundColor,
        color: labelColor
    }

    return (
        <div className="container">
            <button className="preview" style={colorSpaceForPreview}>{label}</button>
            <span style={{paddingBottom: 16}}/>
            <div style={{display: "flex", flexDirection: "row"}}>
                <div style={{display: "flex", flexDirection: "column"}}>
                    <LabelInput label={label} setLabel={setLabel}/>
                    <ColorPicker label="Outline color: " color={outlineColor} setColor={setOutlineColor}/>
                    <ColorPicker label="Background color: " color={backgroundColor} setColor={setBackgroundColor}/>
                    <ColorPicker label="Label color: " color={labelColor} setColor={setLabelColor}/>
                </div>
                <span style={{paddingRight: 16}}/>
                <SyntaxHighlighter language="json" style={materialDark}
                                   customStyle={{margin: 0, padding: 8, fontSize: 12}}>
                    {JSON.stringify({
                        "custom": {
                            "mcb": [
                                {
                                    "name": label,
                                    "fillColor": toRGBA(backgroundColor.replace(/^#/, '')),
                                    "outlineColor": toRGBA(outlineColor.replace(/^#/, '')),
                                }
                            ]
                        }
                    }, undefined, 2)}
                </SyntaxHighlighter>
            </div>
        </div>
    )
}

