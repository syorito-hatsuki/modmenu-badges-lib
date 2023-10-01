import {LableInputProps} from "./types.ts";
import "./Components.css"

export default function LabelInput(props: LableInputProps) {
    return <input className="labelInput" type="text" value={props.label}
                  onChange={(event) => props.setLabel(event.target.value)}/>
}