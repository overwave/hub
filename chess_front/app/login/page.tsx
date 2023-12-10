'use client'

import './style.css'
import {FormEvent, useState} from "react";
import {clsx} from 'clsx';
import {getHost} from "@/app/utils";
import {useRouter} from 'next/navigation';

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

        fetch(getHost() + "/chess/api/user/login", {
            method: "POST",
            body: formData,
            credentials: 'include',
        })
            .then<AuthenticateResponse>((response) => {
                if (!response.ok) {
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
        <div className="form-container">
            <main className="border border-success rounded-3">
                <form onSubmit={handleInput}>
                    <h1 className="h1 mb-3">♟︎</h1>
                    <div className="prompt-label">
                        <div
                            className={clsx('prompt-text', stage == 'Login' ? 'opacity-100' : 'opacity-0')}>
                            Введите логин
                        </div>
                        <div className={clsx('prompt-text', stage == 'Password' ? 'opacity-100' : 'opacity-0')}>
                            Введите пароль
                        </div>
                        <div className={clsx('prompt-text', stage == 'Registration' ? 'opacity-100' : 'opacity-0')}>
                            Придумайте пароль
                        </div>
                    </div>

                    <div className="form-floating mb-4">
                        <input type={stage == 'Password' ? "password" : "text"} id="input" placeholder="overwave"
                               className={'form-control' + (getError() ? ' is-invalid' : '')}
                               value={input}
                               onChange={(e) => {
                                   setInput(e.target.value);
                                   setSubStage("Idle");
                               }}/>
                        <label htmlFor="input">Логин</label>
                        <div className="invalid-feedback">{getError()}</div>
                    </div>

                    <button
                        disabled={subStage == "Loading"}
                        className="w-100 btn btn-lg btn-success"
                        type="submit">
                        {subStage != "Loading" ? "Далее" :
                            <div className="spinner-border" role="status">
                                <span className="visually-hidden">Загрузка...</span>
                            </div>
                        }
                    </button>
                </form>
            </main>
            <footer className="fixed-bottom">
                <a href="https://unsplash.com/photos/brown-tree-with-white-flowers-during-daytime-Ml8WeLdCnRU"
                   className="text-secondary">Unsplash</a>
            </footer>
        </div>
    );
}