import {useState} from 'react'
import "./App.css"
import ColorPicker from "./components/ColorPicker.tsx";
import LabelInput from "./components/LabelInput.tsx";
import SyntaxHighlighter from "react-syntax-highlighter/dist/cjs/prism";
import {materialDark} from "react-syntax-highlighter/dist/cjs/styles/prism";

export default function App() {
    const [outlineColor, setOutlineColor] = useState("#4CAF50FF")
    const [backgroundColor, setBackgroundColor] = useState("#2E7D32FF")
    const [labelColor, setLabelColor] = useState("#e3e3e3")
    const [label, setLabel] = useState("Custom Badge")

    const jsonPreview = {
        "custom": {
            "mcb": [
                {
                    "name": label,
                    "fillColor": toRGBA(backgroundColor.replace(/^#/, '')),
                    "outlineColor": toRGBA(outlineColor.replace(/^#/, '')),
                }
            ]
        }
    }

    return (
        <div className="container">
            <button id="preview" style={{
                padding: 16,
                borderWidth: 16,
                borderColor: outlineColor,
                borderStyle: "solid",
                backgroundColor: backgroundColor,
                color: labelColor,
                fontSize: 56,
                fontFamily: "Minecraftia",
            }}>{label}</button>
            <span style={{paddingBottom: 16}}/>
            <div style={{
                display: "flex",
                flexDirection: "row"
            }}>
                <div style={{
                    display: "flex",
                    flexDirection: "column"
                }}>
                    <LabelInput label={label} setLabel={setLabel}/>
                    <ColorPicker label="Outline color: " color={outlineColor} setColor={setOutlineColor}/>
                    <ColorPicker label="Background color: " color={backgroundColor} setColor={setBackgroundColor}/>
                    <ColorPicker label="Label color: " color={labelColor} setColor={setLabelColor}/>
                </div>
                <span style={{paddingRight: 16}}/>
                <SyntaxHighlighter language="json" style={materialDark}
                                   customStyle={{margin: 0, padding: 8, fontSize: 12}}>
                    {JSON.stringify(jsonPreview, undefined, 2)}
                </SyntaxHighlighter>
            </div>
        </div>
    )
}

function toRGBA(nm: string): number {
    const r = parseInt(nm.substring(0, 2), 16);
    const g = parseInt(nm.substring(2, 4), 16);
    const b = parseInt(nm.substring(4, 6), 16);
    const a = parseInt(nm.substring(6, 8), 16);
    return (a << 24) + (r << 16) + (g << 8) + (b << 0)
}