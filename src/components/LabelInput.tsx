import {LableInputProps} from "./types.ts";
import {CSSProperties} from "react";

export default function LabelInput(props: LableInputProps) {

    const inputStyle: CSSProperties = {
        backgroundColor: "#000000",
        borderWidth: 2,
        borderStyle: "solid",
        outlineStyle: "none",
        fontFamily: "Minecraftia",
        fontSize: 16,
        borderColor: "#FFFFFF",
        color: "#FFFFFF",
        padding: 8,
        marginBottom: 8
    }

    return <input style={inputStyle} type="text" value={props.label}
                  onChange={(event) => props.setLabel(event.target.value)}/>

}