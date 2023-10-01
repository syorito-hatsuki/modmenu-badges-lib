import {HexColorInput} from "react-colorful";
import {ColorPickerProps} from "./types.ts";

export default function HexInput(props: ColorPickerProps) {
    return (
        <HexColorInput alpha onChange={newColor => props.setColor(newColor)} className="labelInput"
                       color={props.color}/>
    )
}