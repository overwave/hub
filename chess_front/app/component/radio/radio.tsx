import {ReactNode, useState} from "react";
import {clsx} from "clsx";
import styles from "@/app/play/styles.module.css";

export type RadioElement = [string, ReactNode];

export default function Radio(props: { elements: RadioElement[], default?: string, callback: (value: string) => void, name: string }) {
    const [selectedValue, setSelectedValue] = useState<string | undefined>(props.default);

    return <div className="btn-group" role="group">
        {
            props.elements.flatMap((element) => {
                const [value, node] = element;
                return [
                    <input type="radio"
                           className="btn-check"
                           name={props.name}
                           id={props.name + value}
                           key={'input_' + props.name + value}
                           autoComplete="off" value={value}
                           onChange={e => {
                               setSelectedValue(e.target.value);
                               props.callback(e.target.value);
                           }}
                           checked={selectedValue === value}/>,
                    <label
                        className={clsx("btn btn-outline-secondary", styles.selector)}
                        key={'label_' + props.name + value}
                        htmlFor={props.name + value}>{node}</label>,
                ]
            })
        }
    </div>
}