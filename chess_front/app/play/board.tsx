'use client'

import {Prompt} from 'next/font/google'
import {ReactNode} from "react";
import './board.css'

const font = Prompt({subsets: ['latin'], weight: ['500']})

const withinBoard = (position: number): boolean => position > 0 && position < 9;

function getCellType(column: number, row: number): string {
    const classes: string[] = [];
    if (column === 1 && withinBoard(row)) {
        classes.push('board_border-left');
    } else if (column === 8 && withinBoard(row)) {
        classes.push('board_border-right');
    }
    if (row === 1 && withinBoard(column)) {
        classes.push('board_border-top');
    } else if (row === 8 && withinBoard(column)) {
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

function Cells() {
    const board: ReactNode[] = [];
    for (let row = 0; row <= 9; row++) {
        for (let column = 0; column <= 9; column++) {
            const address = String.fromCharCode(96 + column) + row;
            const cellType = getCellType(column, row);
            board.push(
                <div key={address}
                     className={cellType + ' cell ' + font.className}>
                    {getCellText(column, row)}
                </div>
            );
        }
    }
    return board;
}

export default function Board() {
    return (
        <main className="d-grid w-100">
            <div className="span-10"></div>
            <Cells></Cells>
        </main>
    );
}