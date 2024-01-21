'use client'

import {Prompt} from 'next/font/google'
import {ReactNode} from 'react'
import {BoardDto, FigureDto, useBoard} from './api'
import './board.css'
import Image from "next/image";

const font = Prompt({subsets: ['latin'], weight: ['500']})

const withinBoard = (position: number): boolean => position > 0 && position < 9;

function getCellType(column: number, row: number): string {
    const classes: string[] = [];
    if (column === 1 && withinBoard(row)) {
        classes.push('board_border-left');
    } else if (column === 8 && withinBoard(row)) {
        classes.push('board_border-right');
    }
    if (row === 8 && withinBoard(column)) {
        classes.push('board_border-top');
    } else if (row === 1 && withinBoard(column)) {
        classes.push('board_border-bottom');
    }
    if (withinBoard(column) && withinBoard(row)) {
        classes.push((column + row) % 2 ? 'black' : 'white');
    }
    if (!withinBoard(column) && withinBoard(row) || withinBoard(column) && !withinBoard(row)) {
        classes.push('board_border-label');
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

function Figure(props: { figure?: FigureDto }) {
    if (props.figure === undefined) {
        return null;
    }
    const type = props.figure.type.toLowerCase();
    const color = props.figure.color.toLowerCase();
    return <Image src={`/chess/figure/${type}_${color}.svg`} alt="Chess piece" fill className="p-1"/>;
}

function Cells(props: { board?: BoardDto}) {
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
                     className={cellType + ' cell position-relative ' + font.className}>
                    {getCellText(column, row)}
                    {withinBoard(column) && withinBoard(row) && <Figure figure={board.get(address)?.figure}/>}
                </div>
            );
        }
    }
    return boardNodes;
}

export default function Board(props: { board?: BoardDto, boardSupplier?: () => BoardDto | undefined }) {
    const board = (props.boardSupplier || (() => props.board))();
    return (
        <main className="d-grid mx-5">
            {!board &&
                <div className="loading-placeholder">
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                </div>
            }
            <Cells board={board}></Cells>
        </main>
    );
}