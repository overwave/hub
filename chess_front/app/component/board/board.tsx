'use client'

import {Prompt} from 'next/font/google'
import {ReactNode} from 'react'
import {BoardDto, FigureDto} from '../../game/api'
import styles from "./board.module.css";
import Image from "next/image";
import {clsx} from "clsx";

const font = Prompt({subsets: ['latin'], weight: ['500']})

const withinBoard = (position: number): boolean => position > 0 && position < 9;

function getCellType(column: number, row: number): string {
    const classes: string[] = [];
    if (column === 1 && withinBoard(row)) {
        classes.push(styles.borderLeft);
    } else if (column === 8 && withinBoard(row)) {
        classes.push(styles.borderRight);
    }
    if (row === 8 && withinBoard(column)) {
        classes.push(styles.borderTop);
    } else if (row === 1 && withinBoard(column)) {
        classes.push(styles.borderBottom);
    }
    if (withinBoard(column) && withinBoard(row)) {
        classes.push((column + row) % 2 ? styles.black : styles.white);
    }
    if (!withinBoard(column) && withinBoard(row) || withinBoard(column) && !withinBoard(row)) {
        classes.push(styles.borderLabel);
    }
    return classes.join(' ');
}

function getCellText(column: number, row: number): string {
    if (!withinBoard(column) && withinBoard(row)) {
        return '' + row;
    } else if (withinBoard(column) && !withinBoard(row)) {
        return String.fromCharCode(96 + column);
    }
    return '';
}

function Figure(props: { figure: FigureDto | undefined }) {
    if (props.figure === undefined) {
        return null;
    }
    const type = props.figure.type.toLowerCase();
    const color = props.figure.color.toLowerCase();
    return <Image src={`/chess/figure/${type}_${color}.svg`} alt="Chess piece" fill className="p-1"/>;
}

function Cells(props: { board: BoardDto | undefined, reversed: boolean }) {
    if (!props.board) {
        return undefined;
    }
    const board = props.board.board;
    const boardNodes: ReactNode[] = [];
    for (let row = 9; row >= 0; row--) {
        for (let column = 0; column <= 9; column++) {
            const address = String.fromCharCode(96 + column) + row;
            const cellType = getCellType(column, row);
            boardNodes.push(
                <div key={address}
                     className={clsx(cellType, styles.cell, font.className)}>
                    {getCellText(column, row)}
                    {withinBoard(column) && withinBoard(row) && <Figure figure={board.get(address)?.figure}/>}
                </div>
            );
        }
    }
    return boardNodes;
}

export type BoardProps = {
    board?: BoardDto,
    boardSupplier?: () => { board: BoardDto | undefined },
    reversed?: boolean,
    size?: string,
};

export default function Board(props: BoardProps) {
    const defaultBoardSupplier = () => ({board: props.board});
    const board = (props.boardSupplier || defaultBoardSupplier)().board;
    const style = {
        width: props.size || 'min(50vh, 50vw)',
        aspectRatio: 1,
    };
    return (
        <div style={style}>
            <main className={styles.board}>
                <div className={clsx(board && "d-none", styles.loadingPlaceholder)}>
                    <div className={clsx("spinner-border", styles.spinner)} role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                </div>
                <Cells board={board} reversed={!!props.reversed}></Cells>
            </main>
        </div>
    );
}