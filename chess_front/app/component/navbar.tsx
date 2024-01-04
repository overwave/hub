'use client'


import Link from "next/link";
import {useState} from "react";
import {UserDto, useUser} from "@/app/game/api";
import {BoxArrowUpRight} from 'react-bootstrap-icons';

export default function Navbar() {
    const [user, setUser] = useState<UserDto | undefined>(undefined);
    // useUser().then((userDto) => setUser(userDto))

    return (
        <nav className="navbar navbar-expand-sm bg-light">
            <div className="container-fluid container-xxl">
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <a className="navbar-brand ms-2 me-4" href="/">overwave
                        <sub className="fw-light text-muted">chess</sub>
                    </a>
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item mx-1">
                            <a className="nav-link active" aria-current="page" href="/chess/play">Играть</a>
                        </li>
                        <li className="nav-item mx-1">
                            <a className="nav-link active" href="/chess/rating">Рейтинг</a>
                        </li>
                        <li className="nav-item mx-1">
                            <a className="nav-link active" href="/chess/history">История</a>
                        </li>
                        <li className="nav-item mx-1">
                            <a className="nav-link active" href="/chess/settings">Настройки</a>
                        </li>
                    </ul>
                    <form className="d-flex" role="search">
                        <input className="form-control me-2" type="search" placeholder="Никнейм" aria-label="Никнейм"/>
                        <button className="btn btn-outline-success" type="submit">Искать</button>
                    </form>
                </div>
            </div>
        </nav>
    );
}