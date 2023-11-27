'use client'

import {Prompt} from 'next/font/google'
import {ReactNode} from 'react'
import {FigureDto, useBoard} from './api'
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

function mapToPiece(figure?: FigureDto): string {
    if (!figure) {
        return '';
    }
    const offset = figure.color === 'WHITE' ? 9812 : 9818;
    switch (figure.type) {
        case "KING":
            return String.fromCharCode(offset);
        case "QUEEN":
            return String.fromCharCode(offset + 1);
        case "ROOK":
            return String.fromCharCode(offset + 2);
        case "BISHOP":
            return String.fromCharCode(offset + 3);
        case "KNIGHT":
            return String.fromCharCode(offset + 4);
        case "PAWN":
            return String.fromCharCode(offset + 5);
    }
}

function Cells() {
    const {board} = useBoard();
    const boardNodes: ReactNode[] = [];
    for (let row = 0; row <= 9; row++) {
        for (let column = 0; column <= 9; column++) {
            const address = String.fromCharCode(96 + column) + row;
            const cellType = getCellType(column, row);
            boardNodes.push(
                <div key={address}
                     className={cellType + ' cell ' + font.className}>
                    {getCellText(column, row)}
                    {mapToPiece(board!.board.get(address)?.figure)}
                </div>
            );
        }
    }
    return boardNodes;
}

export default function Board() {
    const {error, isLoading} = useBoard();
    return (
        <main className="d-grid w-100">
            {error && <div className="error-message">{'' + error}</div>}
            {isLoading &&
                <div className="loading-placeholder">
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                </div>
            }
            {!error && !isLoading && <div className="span-10"></div>}
            {!error && !isLoading && <Cells></Cells>}
        </main>
    );
}