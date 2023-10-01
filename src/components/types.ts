import {Dispatch} from "react";

export interface ColorPickerProps {
    label: string,
    color: string,
    setColor: Dispatch<string>
}

export interface LableInputProps {
    label: string,
    setLabel: Dispatch<string>
}