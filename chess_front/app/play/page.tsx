'use client'

import {useState} from 'react'
import {ClipboardData, ClockHistory, Gear, Joystick} from 'react-bootstrap-icons';
import './style.css'
import Link from "next/link";
import Board from "./board";


export default function ChessMain() {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false)

    return (
        <div className="h-100 d-flex flex-column">
            <header
                className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 border-bottom ps-3 pe-3">
                <a href="/" className="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                    {/*<Icon8CircleFill size={48} aria-hidden="true"/>*/}
                </a>

                {/*<ul className="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">*/}
                {/*    <li><a href="#" className="nav-link px-2 link-secondary">Home</a></li>*/}
                {/*    <li><a href="#" className="nav-link px-2 link-dark">Features</a></li>*/}
                {/*    <li><a href="#" className="nav-link px-2 link-dark">Pricing</a></li>*/}
                {/*    <li><a href="#" className="nav-link px-2 link-dark">FAQs</a></li>*/}
                {/*    <li><a href="#" className="nav-link px-2 link-dark">About</a></li>*/}
                {/*</ul>*/}

                <span>overwave.dev</span>

                <div className="col-md-3 text-end">
                    <Link href="https://overwave.dev" type="button"
                          className="btn btn-outline-primary me-2">Login</Link>
                    {/*<button type="button" className="btn btn-primary">Sign-up</button>*/}
                </div>
            </header>

            <div className="content-wrapper">
                <nav className="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark">
                    <ul className="nav nav-pills flex-column mb-auto">
                        <li className="nav-item">
                            {/*<a href="#" className="nav-link active" aria-current="page">*/}
                            <Link href="play" className="nav-link text-white pb-3 pt-3">
                                <Joystick size={24} className="me-3 align-text-bottom"/>
                                <span className="fs-4">Играть!</span>
                            </Link>
                        </li>
                        <hr/>
                        <li className="nav-item">
                            <Link href="rating" className="nav-link text-white pb-3 pt-3">
                                <ClipboardData size={24} className="me-3 align-text-bottom"/>
                                <span className="fs-5">Рейтинг</span>
                            </Link>
                        </li>
                        <li>
                            <Link href="history" className="nav-link text-white pb-3 pt-3">
                                <ClockHistory size={24} className="me-3 align-text-bottom"/>
                                <span className="fs-5">История</span>
                            </Link>
                        </li>
                        <li>
                            <Link href="settings" className="nav-link text-white pb-3 pt-3">
                                <Gear size={24} className="me-3 align-text-bottom"/>
                                <span className="fs-5">Настройки</span>
                            </Link>
                        </li>
                    </ul>
                    <hr className=""/>
                    <div className="dropdown">
                        <a href="#"
                           className="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
                           id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://github.com/overwave.png" alt="" width="32" height="32"
                                 className="rounded-circle me-2"/>
                            <strong>overwave</strong>
                        </a>
                        <ul className="dropdown-menu dropdown-menu-dark text-small shadow"
                            aria-labelledby="dropdownUser1">
                            <li><a className="dropdown-item" href="#">New project...</a></li>
                            <li><a className="dropdown-item" href="#">Settings</a></li>
                            <li><a className="dropdown-item" href="#">Profile</a></li>
                            <li>
                                <hr className="dropdown-divider"/>
                            </li>
                            <li><a className="dropdown-item" href="#">Sign out</a></li>
                        </ul>
                    </div>
                </nav>
                <main className="d-flex flex-row justify-content-center w-100">
                    <aside className="d-block">chat 2</aside>
                    <Board></Board>
                    <aside className="d-block">moves</aside>
                </main>
            </div>
        </div>
    )
}