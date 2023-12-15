import {clsx} from "clsx";
import styles from "./chat.module.css";
import {BoxArrowUpRight, Send} from "react-bootstrap-icons";
import {useState} from "react";

export default function Chat() {
    const [messageInput, setMessageInput] = useState("");

    return (
        <div className={styles.chatWrapper}>
            <header>Чат с <a href="/chess/user/lizunya">lizunya <BoxArrowUpRight size="0.75rem"></BoxArrowUpRight></a>
            </header>
            <main className="">
                <div className={styles.messageContainer}>
                    <div className={styles.messageContainer2}>
                        {/*<div className="flex-grow-1"></div>*/}
                        <div className={styles.message}>Привет</div>
                        <div className={clsx(styles.message, "align-self-end")}>Привет!</div>
                        <div className={styles.message}>Как дела?</div>
                        <div className={clsx(styles.message, "align-self-end")}>Хорошо, а у тебя?</div>
                        <div className={styles.message}>Как дела?</div>
                        <div className={clsx(styles.message, "align-self-end")}>Хорошо, а у тебя?</div>
                        {/*<div className={styles.message}>Как дела?</div>*/}
                        {/*<div className={clsx(styles.message, "align-self-end")}>Хорошо, а у тебя?</div>*/}
                    </div>
                </div>
            </main>
            <div className={clsx(styles.chatInputGroup, "input-group")}>
                <input type="text" className="flex-grow-1 form-control rounded-0" value={messageInput}
                       placeholder="Наберите сообщение..."
                       onChange={(e) => setMessageInput(e.target.value)}></input>
                <button className="btn btn-secondary border-0 rounded-0" type="button" id="button-addon2"><Send></Send>
                </button>
            </div>
        </div>
    );
}