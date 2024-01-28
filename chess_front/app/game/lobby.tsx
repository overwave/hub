'use client'

import '../component/board/board.module.css'
import {clsx} from 'clsx';
import styles from "@/app/play/styles.module.css";
import {
    ArrowLeft,
    ArrowUpRightSquare,
    Cpu,
    Plus,
    SlashCircle,
    Square,
    SquareFill,
    SquareHalf
} from "react-bootstrap-icons";
import Link from "next/link";
import {OpenSessionDto, useWaitingLobby} from "@/app/game/api";
import {useCollapse} from 'react-collapsed'
import {ReactNode, useState} from "react";
import Radio from "@/app/component/radio/radio";


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

function OpenSession(props: { openSession: OpenSessionDto, opened: boolean, clickCallback: (_: number) => void }) {
    const {getCollapseProps, getToggleProps} = useCollapse({isExpanded: props.opened});
    return (
        <button key={props.openSession.id} type="button"
                {...getToggleProps({onClick: () => props.clickCallback(props.openSession.id)})}
                className={clsx(
                    "btn btn-light text-start fs-5",
                    styles.sessionRequest,
                )}>
            {getSideIcon(props.openSession.opponentSide)}
            {props.openSession.opponent.bot &&
                <span className="badge text-bg-secondary me-1 align-text-top">Бот</span>}
            <span>{props.openSession.opponent.name}</span>
            <Link href={"chess/users/" + props.openSession.opponent.login}>
                <ArrowUpRightSquare className={styles.externalLink}></ArrowUpRightSquare>
            </Link>

            <div {...getCollapseProps()}>
                <div className="pt-3">Выберите цвет фигур:</div>
            </div>
            {/*<ArrowRight className={clsx(styles.arrow)}></ArrowRight>*/}
        </button>);
}

function PersonArmsUp() {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-person-arms-up" viewBox="0 0 16 16">
            <path d="M8 3a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3"/>
            <path d="m5.93 6.704-.846 8.451a.768.768 0 0 0 1.523.203l.81-4.865a.59.59 0 0 1 1.165 0l.81 4.865a.768.768 0 0 0 1.523-.203l-.845-8.451A1.5 1.5 0 0 1 10.5 5.5L13 2.284a.796.796 0 0 0-1.239-.998L9.634 3.84a.7.7 0 0 1-.33.235c-.23.074-.665.176-1.304.176-.64 0-1.074-.102-1.305-.176a.7.7 0 0 1-.329-.235L4.239 1.286a.796.796 0 0 0-1.24.998l2.5 3.216c.317.316.475.758.43 1.204Z"/>
        </svg>
    );
}

export default function Lobby() {
    const {lobby} = useWaitingLobby();
    const loading = lobby?.openSessions === undefined;
    const empty = lobby?.openSessions?.length === 0;
    const [selectedSession, setSelectedSession] = useState<number | undefined>(undefined);
    const [lobbyWizardOpened, setLobbyWizardOpened] = useState<boolean>(false);
    const {getCollapseProps} = useCollapse({isExpanded: lobbyWizardOpened});

    const [selectedSide, setSelectedSide] = useState<string>("any");
    const [selectedOpponent, setSelectedOpponent] = useState<string >("bot");

    return (
        <div className={clsx("card h-100", styles.lobbyCard)}>
            <header className="card-header py-3 h4 text-center">
                <span className="h4 pe-2">Лобби ожидания</span>
                <button type="button"
                        className={clsx(styles.lobbyButton, 'btn rounded btn-success align-bottom d-none')}>
                    <Plus className="fs-4"></Plus>
                </button>
            </header>
            <main
                className={clsx(
                    styles.cardMain,
                    "card-body",
                    empty && "d-flex align-items-center justify-content-center",
                )}
                onClick={(event) => {
                    if (event.target === event.currentTarget) {
                        setSelectedSession(undefined);
                    }
                }}>
                <div className={clsx((empty || loading) && "d-none", styles.lobbyList, "d-grid gap-2")}>
                    {lobby?.openSessions?.map(openSession =>
                        <OpenSession
                            key={openSession.id}
                            openSession={openSession}
                            opened={selectedSession === openSession.id}
                            clickCallback={(id) => setSelectedSession(selectedSession === openSession.id ? undefined : id)}>
                        </OpenSession>)
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
                <div {...getCollapseProps()} className="fs-5">
                    <div className="mb-2">Выберите цвет фигур:</div>
                    <Radio name="side" callback={setSelectedSide} default="any" elements={
                        [
                            ["any", <span><SquareHalf></SquareHalf> Любой</span>],
                            ["white", <span><Square></Square> Белые</span>],
                            ["black", <span><SquareFill></SquareFill> Чёрные</span>],
                        ]
                    }></Radio>

                    <div className="mt-3 mb-2">Выберите противника:</div>

                    <Radio name="opponent" callback={setSelectedOpponent} default="bot" elements={
                        [
                            ["bot", <span><Cpu className=""></Cpu> Бот</span>],
                            ["human", <span><PersonArmsUp></PersonArmsUp> Человек</span>],
                        ]
                    }></Radio>

                    <div className="mt-4"></div>
                </div>
                <div className="btn-group w-100" role="group" aria-label="Кнопки создания лобби">
                    {lobbyWizardOpened &&
                        <button onClick={() => setLobbyWizardOpened(false)}
                                type="button" className={clsx("btn btn-outline-danger")}>
                            <ArrowLeft aria-hidden="true"/>
                        </button>
                    }
                    <button
                        onClick={() => {
                            if (lobbyWizardOpened) {
                                alert("Бой за " + selectedSide + " против " + selectedOpponent);
                            } else {
                                setLobbyWizardOpened(true);
                            }
                        }}
                        type="button"
                        className={
                            clsx(
                                "btn btn-primary w-100",
                                lobbyWizardOpened && styles.createLobbyButton,
                                lobbyWizardOpened ? 'btn-success' : 'btn-primary',
                                lobbyWizardOpened && !(selectedSide && selectedOpponent) && 'disabled',
                            )}>
                        Создать
                    </button>
                </div>
            </footer>
        </div>
    );
}