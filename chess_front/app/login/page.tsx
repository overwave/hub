'use client'

import styles from './styles.module.css'
import {FormEvent, useState} from "react";
import {clsx} from 'clsx';
import {getHost} from "@/app/utils";
import {useRouter} from 'next/navigation';
import {ArrowLeft} from 'react-bootstrap-icons';

type Stage = "Login" | "Registration" | "Password";
type LoginStage = "Idle" | "Loading" | "Next" | "ServerError" | "EmptyLoginError";
type RegistrationStage = "Idle" | "Loading" | "ServerError" | "EmptyPasswordError";
type PasswordStage = "Idle" | "Loading" | "ServerError" | "WrongPasswordError" | "EmptyPasswordError";

type CheckUserResponse = { exists: boolean }
type AuthenticateResponse = { result: "SUCCESS" | "FAILED" }

export default function Page() {
    const [login, setLogin] = useState("");
    const [input, setInput] = useState("");

    const [stage, setStage] = useState<Stage>("Login");
    const [subStage, setSubStage] = useState<LoginStage | RegistrationStage | PasswordStage>("Idle");
    const router = useRouter();

    const handleLoginInput = (login: string) => {
        if (!login) {
            setSubStage("EmptyLoginError");
            return;
        }
        setSubStage("Loading");
        fetch(getHost() + "/chess/api/user/check?login=" + login)
            .then<CheckUserResponse>((response) => {
                if (!response.ok) {
                    throw new Error("failed to check the login");
                }
                return response.json();
            })
            .then(({exists}) => {
                setLogin(login);
                setSubStage("Idle");
                exists ? setStage("Password") : setStage("Registration");
                setInput("");
            })
            .catch(() => setSubStage("ServerError"));
    }

    const handleRegistrationInput = (password: string) => {
        if (!password) {
            setSubStage("EmptyPasswordError");
            return;
        }
        setSubStage("Loading");
        fetch(getHost() + "/chess/api/user/register", {
            method: "POST",
            credentials: 'include',
            body: JSON.stringify({login, password}),
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("failed to register");
                }
                const formData = new FormData();
                formData.append('username', login);
                formData.append('password', password);

                return fetch(getHost() + "/chess/api/user/login", {
                    method: "POST",
                    body: formData,
                    credentials: 'include',
                });
            })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("failed to login");
                }
                router.push("/");
            })
            .catch(() => setSubStage("ServerError"));
    }

    const handlePasswordInput = (password: string) => {
        if (!password) {
            setSubStage("EmptyPasswordError");
            return;
        }
        setSubStage("Loading");
        const formData = new FormData();
        formData.append('username', login);
        formData.append('password', password);
        formData.append('remember-me', 'true');

        fetch(getHost() + "/chess/api/user/login", {
            method: "POST",
            body: formData,
            credentials: 'include',
        })
            .then<AuthenticateResponse>((response) => {
                if (!response.ok && response.status != 403) {
                    throw new Error("failed to login");
                }
                return response.json();
            })
            .then(({result}) => {
                if (result == "SUCCESS") {
                    router.push("/");
                } else {
                    setSubStage("WrongPasswordError");
                }
            })
            .catch(() => setSubStage("ServerError"));
    }

    const handleInput = async (event: FormEvent) => {
        event.preventDefault();
        switch (stage) {
            case "Login":
                return handleLoginInput(input);
            case "Registration":
                return handleRegistrationInput(input);
            case "Password":
                return handlePasswordInput(input);
        }
    }

    const getError = () => {
        switch (subStage) {
            case "ServerError":
                return "Произошла ошибка, попробуйте позднее"
            case "EmptyLoginError":
                return "Введите логин"
            case "EmptyPasswordError":
                return "Введите пароль"
            case "WrongPasswordError":
                return "Пароль неверный"
            default:
                return undefined;
        }
    }

    return (
        <div className={styles.formContainer}>
            <main className="border border-success rounded-3">
                <form onSubmit={handleInput}>
                    <h1 className="h1 mb-3">♟︎</h1>
                    <div className={styles.promptLabel}>
                        <div
                            className={clsx(styles.promptText, stage == 'Login' ? 'opacity-100' : 'opacity-0')}>
                            Введите логин
                        </div>
                        <div className={clsx(styles.promptText, stage == 'Password' ? 'opacity-100' : 'opacity-0')}>
                            Введите пароль
                        </div>
                        <div className={clsx(styles.promptText, stage == 'Registration' ? 'opacity-100' : 'opacity-0')}>
                            Придумайте пароль
                        </div>
                    </div>

                    <div className="form-floating mb-4">
                        <input type={stage == 'Login' ? "text" : "password"} id="input" placeholder="overwave"
                               className={'form-control' + (getError() ? ' is-invalid' : '')}
                               value={input}
                               onChange={(e) => {
                                   setInput(e.target.value);
                                   setSubStage("Idle");
                               }}/>
                        <label htmlFor="input">{stage == 'Login' ? 'Логин' : 'Пароль'}</label>
                        <div className="invalid-feedback">{getError()}</div>
                    </div>

                    <div className="btn-group w-100" role="group" aria-label="Login form controls">
                        {stage != 'Login' &&
                            <button type="button" className="btn btn-lg btn-outline-success"
                                    onClick={(e) => {
                                        setStage('Login');
                                        setSubStage('Idle');
                                        setInput(login);
                                    }}>
                                <ArrowLeft aria-hidden="true"/>
                            </button>
                        }
                        <button
                            disabled={subStage == "Loading"}
                            className={clsx(styles.mainButton, 'w-100', 'btn', 'btn-lg', 'btn-success')}
                            type="submit">
                            {subStage != "Loading" ? "Далее" :
                                <div className={clsx(styles.spinnerBorder, 'spinner-border')} role="status">
                                    <span className="visually-hidden">Загрузка...</span>
                                </div>
                            }
                        </button>
                    </div>
                </form>
            </main>
            <footer className="fixed-bottom">
                <a href="https://unsplash.com/photos/brown-tree-with-white-flowers-during-daytime-Ml8WeLdCnRU"
                   className="text-secondary">Unsplash</a>
            </footer>
        </div>
    );
}