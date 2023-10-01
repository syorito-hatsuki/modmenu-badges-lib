import {CSSProperties, useState} from 'react'
import "./App.css"
import ColorPicker from "./components/ColorPicker.tsx";
import LabelInput from "./components/LabelInput.tsx";
import SyntaxHighlighter from "react-syntax-highlighter/dist/cjs/prism";
import {materialDark} from "react-syntax-highlighter/dist/cjs/styles/prism";
import toRGBA from "./colorUtil.ts";
import TabButton from "./components/TabButton.tsx";
import HexInput from "./components/HexInput.tsx";

export default function App() {
    const [outlineColor, setOutlineColor] = useState("#4CAF50FF")
    const [backgroundColor, setBackgroundColor] = useState("#2E7D32FF")
    const [labelColor, setLabelColor] = useState("#E3E3E3FF")
    const [label, setLabel] = useState("Custom Badge")

    const [tab, setTab] = useState(0)

    const colorSpaceForPreview: CSSProperties = {
        outlineColor: outlineColor,
        backgroundColor: backgroundColor,
        color: labelColor
    }

    return (
        <div className="container">
            <button className="preview" style={colorSpaceForPreview}>{label}</button>
            <span style={{paddingBottom: 16}}/>
            <div style={{
                display: "flex",
                alignItems: "center", flexDirection: "row"
            }}>
                <div>
                    <div style={{
                        display: "flex",
                        flexDirection: "column",
                        boxShadow: "inset 0px 0px 16px 4px rgba(0,0,0,1)",
                        paddingTop: 16,
                        paddingRight: 16,
                        paddingLeft: 16,
                        paddingBottom: 8
                    }}>
                        {tab === 0 && <>
                            <LabelInput label={label} setLabel={setLabel}/>
                            <ColorPicker label="Outline color: " color={outlineColor} setColor={setOutlineColor}/>
                            <ColorPicker label="Background color: " color={backgroundColor}
                                         setColor={setBackgroundColor}/>
                            <ColorPicker label="Label color: " color={labelColor} setColor={setLabelColor}/>
                        </>}
                        {tab === 1 && <>
                            <LabelInput label={label} setLabel={setLabel}/>
                            <HexInput label="Outline color: " color={outlineColor} setColor={setOutlineColor}/>
                            <HexInput label="Background color: " color={backgroundColor}
                                      setColor={setBackgroundColor}/>
                            <HexInput label="Label color: " color={labelColor} setColor={setLabelColor}/>
                        </>}
                    </div>
                    <div style={{display: "flex", flexDirection: "row", paddingTop: 8}}>
                        <TabButton label="Color Picker" currentTab={tab} tabIndex={0} onClick={() => setTab(0)}/>
                        <span style={{paddingRight: 8}}/>
                        <TabButton label="HEX Input" currentTab={tab} tabIndex={1} onClick={() => setTab(1)}/>
                    </div>
                </div>
                <span style={{paddingRight: 16}}/>
                <SyntaxHighlighter language="json" style={materialDark}
                                   customStyle={{margin: 0, padding: 8, fontSize: 12}}>
                    {JSON.stringify({
                        "custom": {
                            "mcb": [
                                {
                                    "name": label,
                                    "labelColor": toRGBA(labelColor.replace(/^#/, '')),
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

