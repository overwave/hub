'use client'

import {useState} from 'react'
import styles from './styles.module.css'
import Board from "./board";
import Navbar from "@/app/component/navbar";
import Chat from "@/app/component/chat";


export default function Page() {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false)

    return (
        <div className="h-100 d-flex flex-column">
            <Navbar></Navbar>
            <div className={styles.contentWrapper}>
                <main className="d-flex flex-row justify-content-center w-100">
                    <aside className="d-block h-100 my-auto">
                        <div className="d-block border border-primary border-2 h-25 my-5">
                            overwave vs lizunya
                        </div>

                        <Chat></Chat>
                    </aside>
                    <Board></Board>
                    <aside className="d-block border border-primary border-2 h-50 my-auto">moves</aside>
                </main>
            </div>
        </div>
    )
}