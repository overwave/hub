'use client'


import Link from "next/link";
import {resetUser, useUser} from "@/app/game/api";
import {clsx} from "clsx";
import {getHost, setLoggedIn} from "@/app/utils";
import {useRouter} from "next/navigation";
import {useSWRConfig} from "swr";

export default function Navbar() {
    const {user, isLoading: userLoading} = useUser();
    const { mutate } = useSWRConfig();
    const router = useRouter();

    const logout = async () => {
        return fetch(getHost() + "/chess/api/user/logout", {
            method: "POST",
            credentials: 'include',
        }).then(() => {
            setLoggedIn(false);
            return resetUser(mutate);
        })
            .then(() => router.push("/"));
    }

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
                    <form className="d-flex me-5" role="search">
                        <input className="form-control me-1" type="search" placeholder="Никнейм" aria-label="Никнейм"/>
                        <button className="btn btn-outline-success" type="submit">Искать</button>
                    </form>

                    {user ?
                        [
                            <Link key="2" href={`/user/${user.login}`} className="pe-3">{user.login}</Link>,
                            <button key="1" type="button" className="btn btn-outline-primary" onClick={logout}>Выйти</button>
                        ] :
                        <Link href="/login" type="button"
                              className={clsx("btn btn-primary", userLoading && "disabled")}>Войти</Link>
                    }
                </div>
            </div>
        </nav>
    );
}