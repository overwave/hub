'use client'

import './board.css'
import {clsx} from 'clsx';
import styles from "@/app/play/styles.module.css";
import {ArrowRight, ArrowUpRightSquare, Plus, SlashCircle, Square, SquareFill, SquareHalf} from "react-bootstrap-icons";
import Link from "next/link";
import {useWaitingLobby} from "@/app/game/api";
import {ReactNode} from "react";


function getSideIcon(side: "WHITE" | "BLACK" | "ANY"): ReactNode {
    switch (side) {
        case "WHITE":
            return <Square className={styles.sideIcon}></Square>;
        case "BLACK":
            return <SquareFill className={styles.sideIcon}></SquareFill>;
        case "ANY":
            return <SquareHalf className={styles.sideIcon}></SquareHalf>;
    }
}

export default function Lobby() {
    const {lobby} = useWaitingLobby();
    const loading = lobby?.sessionRequests === undefined;
    const empty = lobby?.sessionRequests?.length === 0;
    return (
        <div className={clsx("card h-100", styles.lobbyCard)}>
            <header className="card-header py-3 h4 text-center">
                <span className="h4 pe-2">Лобби ожидания</span>
                <button type="button"
                        className={clsx(styles.lobbyButton, 'btn rounded btn-success align-bottom d-none')}>
                    <Plus className="fs-4"></Plus>
                </button>
            </header>
            <main className={clsx("card-body", empty && "d-flex align-items-center justify-content-center")}>
                <div className={clsx((empty || loading) && "d-none", "d-grid gap-2")}>
                    {lobby?.sessionRequests?.map(request =>
                        <button key={request.id} type="button"
                                className={clsx("btn btn-light text-start fs-5", styles.sessionRequest)}>
                            {getSideIcon(request.opponentSide)}
                            {request.opponent.bot &&
                                <span className="badge text-bg-secondary me-1 align-text-top">Бот</span>}
                            <span>{request.opponent.name}</span>
                            <Link href={"chess/users/" + request.opponent.login}>
                                <ArrowUpRightSquare className={styles.externalLink}></ArrowUpRightSquare>
                            </Link>
                            <ArrowRight className={clsx(styles.arrow)}></ArrowRight>
                        </button>)
                    }
                </div>
                <div className={clsx(loading && "d-block", styles.placeholder)}>
                    <div className={clsx("card-text placeholder-glow", styles.placeholderItem)}>
                        <span className="placeholder col-12"></span>
                    </div>
                    <div className={clsx("card-text placeholder-glow", styles.placeholderItem)}>
                        <span className="placeholder col-12"></span>
                    </div>
                    <div className={clsx("card-text placeholder-glow", styles.placeholderItem)}>
                        <span className="placeholder col-12"></span>
                    </div>
                </div>
                <div className={clsx(empty && "d-block", styles.placeholder)}>
                    <div className="d-flex flex-column">
                        <div className="align-self-center pb-3">
                            <SlashCircle className="display-4"></SlashCircle>
                        </div>
                        <div className="w-75 align-self-center text-center">Сейчас нет открытых сессий!</div>
                    </div>
                </div>
            </main>
            <footer className="card-footer">
                <a href="#" className="btn btn-success">Создать</a>
            </footer>
        </div>
    );
}