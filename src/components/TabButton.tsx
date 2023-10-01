import {MouseEventHandler} from "react";

export default function TabButton(props: {
    label: string,
    currentTab: number,
    tabIndex: number,
    onClick: MouseEventHandler
}) {
    return (
        <button className="tabButton" style={{
            borderColor: props.currentTab === props.tabIndex ? "white" : "black"
        }} onClick={props.onClick}>{props.label}</button>
    )
}