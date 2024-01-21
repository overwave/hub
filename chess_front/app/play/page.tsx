'use client'

import {useState} from 'react'
import styles from './styles.module.css'
import Navbar from "@/app/component/navbar";
import {X} from 'react-bootstrap-icons';

import {clsx} from 'clsx';
import Link from "next/link";
import Lobby from "@/app/game/lobby";
import Board from "@/app/component/board/board";
import {useDemoMatch} from "@/app/game/api";

export default function Page() {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

    return (
        <div className="h-100">
            <Navbar></Navbar>
            <div className="container-lg mt-5">
                <div className="row">
                    <div className="col-sm-4">
                        <Lobby></Lobby>
                    </div>
                    <div className={clsx(styles.demoGame, "col col-sm-8 border")}>
                        <div className="d-flex justify-content-center">
                            <Board boardSupplier={useDemoMatch} size="min(75vh, 60vw)"></Board>
                        </div>
                        <footer>
                            <div>
                                <div className="pt-3 h4 row">
                                    <div className="col p-0">
                                        <div className="text-end">
                                            <Link href="chess/users/hephaestus">
                                                <span className="badge text-bg-secondary me-1 fs-6 align-bottom">Бот</span>
                                                <span>Гефест</span>
                                            </Link>
                                        </div>
                                    </div>
                                    <div className={clsx(styles.versus, "col-auto")}><X></X></div>
                                    <div className="col p-0">
                                        <div className="text-start">
                                            <Link href="chess/users/ares">
                                                <span className="badge text-bg-secondary me-1 fs-6 align-bottom">Бот</span>
                                                <span>Арес</span>
                                            </Link>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </footer>
                    </div>
                </div>
            </div>
        </div>
    );
}